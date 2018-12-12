package org.luncert.tkgdb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;

@RunWith(JUnit4.class)
public class TestGraphDB {

    private final GraphDatabaseService graphDB = GraphDB.getInstance();

    private Result execute(String query) {
        Result result = null;
        Transaction tx = graphDB.beginTx();
        try {
            result = graphDB.execute(query);
            tx.success();
        } catch (Exception e) {
            tx.failure();
        } finally {
            tx.close();
        }
        return result;
    }

    @Test
    public void test() {
        Result result = execute("MATCH (n) RETURN n");
        System.out.println(result.columns());
        // result.accept(visitor);
    }
    
}