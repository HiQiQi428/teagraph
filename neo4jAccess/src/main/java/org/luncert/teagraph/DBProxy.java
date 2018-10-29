package org.luncert.teagraph;

import java.io.File;
import java.nio.file.Paths;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

public class DBProxy {

    private static GraphDatabaseService graphDb;

    private static class DBProxyInner {
        final static DBProxy INSTANCE = new DBProxy();
    }

    public static DBProxy getInstance() {
        return DBProxyInner.INSTANCE;
    }

    private DBProxy() {
        File dbStoreDir = Paths.get(System.getProperty("user.dir"), "db").toFile();
        graphDb = new GraphDatabaseFactory()
            .newEmbeddedDatabaseBuilder(dbStoreDir)
            .setConfig(GraphDatabaseSettings.pagecache_memory, "512M")
            .setConfig(GraphDatabaseSettings.string_block_size, "60")
            .setConfig(GraphDatabaseSettings.array_block_size, "300")
            .newGraphDatabase();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                graphDb.shutdown();
            }
        });
    }

    public Result handle(String stmt) {
        return graphDb.execute(stmt);
    }

}