package org.luncert.tkglb.logger;

import java.util.List;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.luncert.mullog.Mullog;
import org.luncert.tkglb.cluster.bean.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TaskQueueAspect {

    @Autowired
    private Mullog mullog;

    private int taskNum;
    
    // enQueue

    @Pointcut("execution(public * org.luncert.tkglb.cluster.TaskQueue.enQueue(..))")
    public void enQueue(){}

    @Before("enQueue() && args(pieces)")
    public void beforeEnQueue(List<?> pieces) {
        taskNum = pieces.size();
    }

    @AfterReturning(pointcut = "enQueue()", returning = "gid")
    public void afterReturningEnQueue(int gid) {
        mullog.info("new task group(gid = " + gid + ", taskNum = " + taskNum);
    }

    // deQueue

    @AfterReturning(pointcut = "execution(public * org.luncert.tkglb.cluster.TaskQueue.deQueue())", returning = "task")
    public void afterReturningDeQueue(Task task) {
        mullog.info("starting to process task: " + task);
    }

    // waitForTask

    @Pointcut("execution(public * org.luncert.tkglb.cluster.TaskQueue.waitForTask())")
    public void waitForTask() {}

    @Before("waitForTask()")
    public void beforeWaitForTask() {
        mullog.info("waiting for new task");
    }

    @After("waitForTask()")
    public void afterWaitForTask() {
        mullog.info("new task arrived");
    }

}