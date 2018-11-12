package org.luncert.teagraph.cypher;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestCypherAnalyser {

    @Test
    public void test() throws Exception {
        List<CPiece> list = CypherAnalyser.analyse("MATCH (n:Pig) WHERE n.name = 'uhk' SET n.name = 'Tom' RETURN n UNION All MATCH (n:Pig{name:'Tom'}) RETURN n WITH n.schoolName as schoolName WHERE schoolName STARTSWITH 'UESTC' RETURN schoolName");
        System.out.println(list);
    }

}