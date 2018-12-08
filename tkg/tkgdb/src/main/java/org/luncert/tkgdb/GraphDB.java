package org.luncert.tkgdb;

import java.io.File;
import java.nio.file.Paths;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;

public class GraphDB {

    private static GraphDatabaseService graphDb;

    private static void init() {
        File dbStoreDir = Paths.get(System.getProperty("user.dir"), "db").toFile();
        graphDb = new GraphDatabaseFactory()
            .newEmbeddedDatabaseBuilder(dbStoreDir)
            .setConfig(GraphDatabaseSettings.pagecache_memory, "512M")
            .setConfig(GraphDatabaseSettings.string_block_size, "60")
            .setConfig(GraphDatabaseSettings.array_block_size, "300")
            .setConfig( GraphDatabaseSettings.allow_upgrade, "true")
            .setConfig(GraphDatabaseSettings.bolt_ssl_policy, "true").newGraphDatabase();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> graphDb.shutdown()));
    }

    public static GraphDatabaseService getInstance() {
        if (graphDb == null) {
            synchronized(GraphDB.class) {
                if (graphDb == null)
                    init();
            }
        }
        return graphDb;
    }

}