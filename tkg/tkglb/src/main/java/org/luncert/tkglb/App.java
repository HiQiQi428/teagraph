package org.luncert.tkglb;

import org.luncert.tkglb.server.HttpServer;

public final class App {

    public static void main(String[] args) throws Exception {
        HttpServer server = new HttpServer();
        server.run();
    }
    
}
