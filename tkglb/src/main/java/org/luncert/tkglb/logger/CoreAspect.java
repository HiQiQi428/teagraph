package org.luncert.tkglb.logger;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.luncert.mullog.Mullog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Core日志输出
 */
@Aspect
@Component
public class CoreAspect {

    @Autowired
    private Mullog mullog;

    @Before("execution(public * org.luncert.tkglb.Core.run())")
    public void beforeRun() {
        mullog.info("Core:", "TKGLB started");
    }
    
}