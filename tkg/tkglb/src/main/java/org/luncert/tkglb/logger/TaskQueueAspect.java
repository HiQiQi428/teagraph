package org.luncert.tkglb.logger;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.luncert.mullog.Mullog;
import org.luncert.tkglb.cluster.bean.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TaskQueue日志输出
 */
@Aspect
@Component
public class TaskQueueAspect {

    @Autowired
    private Mullog mullog;

    // deQueue

    /**
     * TaskDispatcher调用deQueue从任务队列获取任务
     */
    @AfterReturning(pointcut = "execution(public * org.luncert.tkglb.cluster.TaskQueue.deQueue())", returning = "task")
    public void afterReturningDeQueue(Task task) {
        mullog.info("TaskDispatcher:", "starting to process " + task);
    }

    // waitForTask

    @Pointcut("execution(public * org.luncert.tkglb.cluster.TaskQueue.waitForTask())")
    public void waitForTask() {}

    @Before("waitForTask()")
    public void beforeWaitForTask() {
        mullog.info("TaskDispatcher:", "waiting for new task");
    }

    @After("waitForTask()")
    public void afterWaitForTask() {
        mullog.info("TaskDispatcher:", "new task arrived");
    }

}