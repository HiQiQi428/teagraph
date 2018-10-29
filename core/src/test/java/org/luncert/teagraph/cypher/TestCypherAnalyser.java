package org.luncert.teagraph.cypher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestCypherAnalyser {

    @Test
    public void test() {
        CypherAnalyser.analyse(" MATCH (n:Pig) WHERE n.name = 'uhk' RETURN n ");
    }

}