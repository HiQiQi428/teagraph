package org.luncert.teagraph;

import org.luncert.teagraph.configurer.Configuration;
import org.luncert.teagraph.configurer.Configurer;
import org.luncert.teagraph.server.cm.ClientManager;
import org.luncert.teagraph.server.nm.NodeManager;

public class Bootstrap {

    public static void main(String[] argv) throws Exception {
        Configuration config = Configurer.loadConfiguration();
        NodeManager nm = new NodeManager(config.getNmPort());
        ClientManager cm = new ClientManager(config.getCmPort());
    }
    
}