package org.luncert.teagraph.server.nm;

import lombok.Data;

@Data
public class NMMessage {

    public static enum Command {
        Register, Logoff
    }

    private Command cmd;
    
}