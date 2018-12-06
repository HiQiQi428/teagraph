package org.luncert.tkglb.logger;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.luncert.mullog.Mullog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProcessorAspect {

    @Autowired
    private Mullog mullog;

    @Before("execution(public * org.luncert.tkglb.cluster.Processor.execute(..)) && args(query,..)")
    public void beforeExecute(String query) {
        mullog.info("Processor processing: " + query);
    }

}