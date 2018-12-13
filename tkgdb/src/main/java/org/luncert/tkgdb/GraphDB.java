package org.luncert.tkgdb;

import java.io.File;
import java.nio.file.Paths;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

import io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue.Consumer;

public class GraphDB {

    private GraphDatabaseService graphDB;

    private static GraphDB INSTANCE;

    private GraphDB() {
        File dbStoreDir = Paths.get(System.getProperty("user.dir"), "db").toFile();
        graphDB = new GraphDatabaseFactory()
            .newEmbeddedDatabaseBuilder(dbStoreDir)
            .setConfig(GraphDatabaseSettings.pagecache_memory, "512M")
            .setConfig(GraphDatabaseSettings.string_block_size, "60")
            .setConfig(GraphDatabaseSettings.array_block_size, "300")
            .setConfig( GraphDatabaseSettings.allow_upgrade, "true")
            .setConfig(GraphDatabaseSettings.bolt_ssl_policy, "true")
            .newGraphDatabase();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> graphDB.shutdown()));
    }

    public static GraphDB getInstance() {
        if (INSTANCE == null) {
            synchronized(GraphDB.class) {
                if (INSTANCE == null)
                    INSTANCE = new GraphDB();
            }
        }
        return INSTANCE;
    }

    public void execute(String query, Consumer<Result> callback) {
        new Thread(() -> {
            Result result = execute(query);
            callback.accept(result);
        }).start();
    }

    public Result execute(String query) {
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

}