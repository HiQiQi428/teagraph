package org.luncert.tkglb.logger;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.luncert.mullog.Mullog;
import org.luncert.tkglb.cluster.bean.DBNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DBPoolAspect {

    @Autowired
    private Mullog mullog;

    @AfterReturning(pointcut = "execution(public * org.luncert.tkglb.cluster.DBPool.newDBNode(..))", returning = "dbNode")
    public void newDBNode(DBNode dbNode) {
        mullog.info("new DB node connected: " + dbNode);
    }

    @AfterReturning(pointcut = "execution(public * org.luncert.tkglb.cluster.DBPool.getReadyDBNode(..))", returning = "dbNode")
    public void getReadyDBNode(DBNode dbNode) {
        mullog.info("selected db node: " + dbNode);
    }

    @AfterReturning(pointcut = "execution(public * org.luncert.tkglb.cluster.DBPool.delete(..))", returning = "dbNode")
    public void delete(DBNode dbNode) {
        mullog.info("DB node disconnected: " + dbNode);
    }


}