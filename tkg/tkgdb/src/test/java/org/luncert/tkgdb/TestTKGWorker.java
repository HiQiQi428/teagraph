package org.luncert.tkgdb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestTKGWorker {

    @Test
    public void test() throws InterruptedException {
        TKGWorker worker = new TKGWorker();
        worker.start();
    }
    
}