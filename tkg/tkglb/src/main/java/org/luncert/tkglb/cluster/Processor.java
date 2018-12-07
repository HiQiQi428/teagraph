package org.luncert.tkglb.cluster;

import java.util.List;
import java.util.function.Consumer;

import org.luncert.tkglb.cypher.CPiece;
import org.luncert.tkglb.cypher.CypherAnalyser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 处理客户端请求
 */
@Component
public class Processor {

    @Autowired
    private TaskQueue taskQueue;

    @Autowired
    private ResultPool resultPool;

    /**
     * 异步操作,仅添加新任务到任务队列
     * 
     * @param query
     * @return 执行id,和query绑定.通过id可以从ResultPool中获取执行结果
     * @throws Exception cypher解析异常
     */
    public void execute(String query, Consumer<String> callback) throws Exception {
        List<CPiece> pieces = CypherAnalyser.analyse(query);
        int gid = taskQueue.enQueue(pieces);
        resultPool.newGroup(gid, pieces.size(), callback);
    }

}