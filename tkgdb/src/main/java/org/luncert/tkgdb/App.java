package org.luncert.tkgdb;

import java.nio.file.Paths;

import org.luncert.configer.Configure;
import org.luncert.configer.ConfigureFactory;

public final class App {


    public static void main(String[] args) throws InterruptedException {
        String configPath = Paths.get(System.getProperty("user.dir"), "config").toString();
        Configure config = new ConfigureFactory(configPath)
                        .allowLog(false)
                        .build();
        String host = config.getAttribute("server.host", String.class);
        int port = config.getAttribute("server.port", Integer.class);
        TKGWorker worker = new TKGWorker(host, port);
        worker.start();
    }

}