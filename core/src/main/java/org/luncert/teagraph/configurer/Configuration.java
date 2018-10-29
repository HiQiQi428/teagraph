package org.luncert.teagraph.configurer;

import java.util.Map;

import lombok.Data;

@Data
public class Configuration {

    public static class AuthPair {
        String username;
        String password;
        
        AuthPair(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String toString() {
            return "(" + username + ", " + password + ")";
        }
    }

    private int cmPort;

    private int nmPort;

    // ip (username, password)
    private Map<String, AuthPair> nodes;

}