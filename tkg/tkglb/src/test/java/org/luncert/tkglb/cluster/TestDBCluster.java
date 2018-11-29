package org.luncert.tkglb.cluster;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestDBCluster {

    @Test
    public void test() throws Exception {
        DBCluster cluster = DBCluster.getInstance();
        cluster.execute("MATCH (n) RETURN n", (result) -> {
            System.out.println(result);
            synchronized(this) {
                this.notify();
            }
        });
        synchronized(this) {
            this.wait();
        }
    }

}