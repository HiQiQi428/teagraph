package org.luncert.tkgdb;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public final class App {

    @Parameter(names = "-help", description = "show usage", help = true, order = 0)
    private boolean help;

    public void run(JCommander jc) throws Exception {
        if (help)
            jc.usage();
        else {
            TKGWorker worker = new TKGWorker();
            worker.init();
            worker.run();
        }
    }

    public static void main(String[] args) throws Exception {
        App app = new App();
        JCommander jc = JCommander.newBuilder().addObject(app).build();
        jc.parse(args);
        app.run(jc);
    }
}
