package org.luncert.tkglb.cluster;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import org.luncert.tkglb.cluster.bean.Result;
import org.springframework.stereotype.Component;

/**
 * 任务池,用于存储任务执行结果,并在合并后返回给客户端
 */
@Component
public class ResultPool {

    /**
     * <li>任务组,因为添加task和添加result的操作都各自发生在单个线程中,不需要同步</li>
     * <li>添加task发生在调度线程中,添加result发生在netty工作线程中,netty有多个工作线程</li>
     */
    private static class Group {

        private int taskSize;
        private List<Result> results = new ArrayList<>();
        private Consumer<String> callback;

        Group(int taskSize, Consumer<String> callback) {
            this.taskSize = taskSize;
            this.callback = callback;
        }

        void addTaskResult(Result result) {
            synchronized(results) {
                results.add(result);
            }
        }

        boolean executeFinished() {
            return taskSize == results.size();
        }

        /**
         * 合并执行结果,还没实现
         * @return
         */
        String reduce() {
            // 根据taskId对执行结果排序
            results.sort(new Comparator<Result>() {
                public int compare(Result r1, Result r2) {
                    return r1.getTaskId() > r2.getTaskId() ? 1 : 
                            (r1.getTaskId() < r2.getTaskId() ? -1 : 0);
                }
            });
            StringBuilder builder = new StringBuilder();
            for (int i = 0, limit = results.size(); i < limit; i++) {
                builder.append(results.get(i).getContent());
            }
            return builder.toString();
        }

    }

    private Map<Integer, Group> groups = new ConcurrentHashMap<>();

    /**
     * 创建新的任务组,当任务组所有的任务都执行结束后调用callback,并自动删除任务组
     * @param gid
     * @param callback
     */
    public void newGroup(int gid, int taskSize, Consumer<String> callback) {
        Group g = new Group(taskSize, callback);
        groups.put(gid, g);
    }

    private Group getGroup(int gid) {
        Group g = groups.get(gid);
        if (g == null)
            throw new NoSuchElementException("Invalid group ID: " + gid);
        return g;
    }

    /**
     * 添加执行结果到任务组
     * @param result
     */
    public void addTaskResult(Result result) {
        int gid = result.getGroupId();
        Group g = getGroup(gid);
        if (g != null) {
            g.addTaskResult(result);
            if (g.executeFinished()) {
                g.callback.accept(g.reduce());
                groups.remove(gid);
            }
        }
        // 如果g为null且result为写任务结果则是正常的.
        // 当写任务为任务组最后一个时,由于写任务会被所有节点执行,而只要有一个执行结果返回便会删除任务组(执行完了)
    }
}