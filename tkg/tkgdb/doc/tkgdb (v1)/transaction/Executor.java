package org.luncert.tkgdb.transaction;

import org.luncert.tkgdb.transport.Message;
import org.luncert.tkgdb.transport.MessageHandler;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;

public class Executor extends MessageHandler {

    private Result execute(String query) {
        GraphDatabaseService graphDb = GraphDB.getInstance();
        Result result = null;
        Transaction tx = graphDb.beginTx();
        try {
            result = graphDb.execute(query);
            tx.success();
        } catch (Exception e) {
            tx.failure();
        } finally {
            tx.close();
        }
        return result;
    }

    public void handle(Message msg) {
        String query = msg.content();
        Result result = execute(query);
        reply(new Message("graphDB/result", result.resultAsString()));
    }

}