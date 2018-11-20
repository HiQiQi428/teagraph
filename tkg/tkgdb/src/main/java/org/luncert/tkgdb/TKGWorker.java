package org.luncert.tkgdb;

import org.luncert.tkgdb.machineInfo.MachineMonitor;
import org.luncert.tkgdb.transaction.Executor;
import org.luncert.tkgdb.transport.MessageServer;

public class TKGWorker {

    private MessageServer server;

    public void init() throws Exception {
        server = new MessageServer();
        server.addHandler("graphDB/execute", Executor.class);
        server.addHandler("machine/info", MachineMonitor.class);
    }

    public void run() {
        server.run();
    }

}