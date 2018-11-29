package org.luncert.tkgdb;

import java.io.File;
import java.nio.file.Paths;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

public class GraphDB {

    private GraphDatabaseService graphDb;

    private static class GraphDBInner {
        static final GraphDB INSTANCE = new GraphDB();
    }

    private GraphDB() {
        File dbStoreDir = Paths.get(System.getProperty("user.dir"), "db").toFile();
        graphDb = new GraphDatabaseFactory()
            .newEmbeddedDatabaseBuilder(dbStoreDir)
            .setConfig(GraphDatabaseSettings.pagecache_memory, "512M")
            .setConfig(GraphDatabaseSettings.string_block_size, "60")
            .setConfig(GraphDatabaseSettings.array_block_size, "300")
                .setConfig(GraphDatabaseSettings.bolt_ssl_policy, "true").newGraphDatabase();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> graphDb.shutdown()));
    }

    public static GraphDatabaseService getInstance() {
        return GraphDBInner.INSTANCE.graphDb;
    }

}