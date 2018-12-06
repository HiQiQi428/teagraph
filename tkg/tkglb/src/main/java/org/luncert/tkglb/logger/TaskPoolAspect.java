package org.luncert.tkglb.logger;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.luncert.mullog.Mullog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.netty.handler.codec.http.HttpContentEncoder.Result;

@Aspect
@Component
public class TaskPoolAspect {

    @Autowired
    private Mullog mullog;

    @Before("execution(public * org.luncert.tkglb.cluster.TaskPool.newGroup(..) && args(gid)")
    public void beforeNewGroup(int gid) {
        mullog.info("creating new task group(gid = " + gid + ")");
    }

    @Before("execution(public * org.luncert.tkglb.cluster.TaskPool.addWaitingTask(..)")
    public void beforeAddWaitingTask() {

    }

    @Before("execution(public * org.luncert.tkglb.cluster.addTaskResult(..) && args(result)")
    public void beforeAddTaskResult(Result result) {
        System.out.println("recieved task result: " + result);
    }

}