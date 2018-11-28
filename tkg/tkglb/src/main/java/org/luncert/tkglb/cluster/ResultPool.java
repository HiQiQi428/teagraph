package org.luncert.tkglb.cluster;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ResultPool {

    private static class Group {

        private List<Task> tasks = new LinkedList<>();
        private TaskResult[] results;
        private int size; // size of results
        private Consumer<String> callback;

        Group(Consumer<String> callback) {
            this.callback = callback;
        }

        void addWaitingTask(Task task) {
            tasks.add(task);
        }

        void init() {
            results = new TaskResult[tasks.size()];
        }

        void addTaskResult(TaskResult result) {
            results[result.getTaskId()] = result;
            size++;
            if (size == results.length) {
                callback.accept(reduce());
            }
        }

        /**
         * 合并执行结果,还没实现
         * @return
         */
        String reduce() {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < results.length; i++) {
                builder.append(results[i]);
            }
            return builder.toString();
        }

    }

    private int preGid = -1;
    private Map<Integer, Group> groups = new HashMap<>();

    public void newGroup(int gid, Consumer<String> callback) {
        Group g = new Group(callback);
        groups.put(gid, g);
    }

    public void addWaitingTask(Task task) {
        int gid = task.getGroupId();
        Group g = groups.get(gid);
        if (gid != preGid) {
            groups.get(preGid).init();
            preGid = gid;
        }
        g.addWaitingTask(task);
    }

    public void addTaskResult(TaskResult result) {
        int gid = result.getGroupId();
        Group g = groups.get(gid);
        if (g == null)
            throw new RuntimeException("Invalid group ID: " + gid);
        g.addTaskResult(result);
    }

}