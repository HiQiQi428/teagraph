package org.luncert.tkglb.logger;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.luncert.mullog.Mullog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.handler.codec.http.HttpContentEncoder.Result;

/**
 * TaskPool日志输出
 */
@Aspect
@Component
public class ResultPoolAspect {

    @Autowired
    private Mullog mullog;

    @Before("execution(public * org.luncert.tkglb.cluster.ResultPool.newGroup(..)) && args(gid, taskNum,..)")
    public void beforeNewGroup(int gid, int taskNum) {
        mullog.info("Processor:", "new task group(gid = " + gid + ", taskNum = " + taskNum + ")");
    }

    @Before("execution(public * org.luncert.tkglb.cluster.ResultPool.addTaskResult(..)) && args(result)")
    public void beforeAddTaskResult(Result result) {
        mullog.info("recieved task result: " + result);
    }

}