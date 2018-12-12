package org.luncert.tkglb.cypher;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestCypherAnalyser {

    @Test
    public void test() throws Exception {
        List<CPiece> pieces = CypherAnalyser.analyse("MATCH (n) RETURN n");
        System.out.println(pieces);
    }

}