package org.luncert.tkgdb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.neo4j.graphdb.Result;

@RunWith(JUnit4.class)
public class TestGraphDB {

    private final GraphDB graphDB = GraphDB.getInstance();

    @Test
    public void test() {
        Result result = graphDB.execute("MATCH (n) RETURN n");
        System.out.println(result.columns());
        // result.accept(visitor);
    }
    
}