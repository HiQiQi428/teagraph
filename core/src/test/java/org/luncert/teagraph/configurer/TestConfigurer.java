package org.luncert.teagraph.configurer;

import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestConfigurer {
    
    @Test
    public void test() throws Exception {
        String resPath = Paths.get(System.getProperty("user.dir"), "src/main/resource").toString();
        System.setProperty("TEAGRAPH_HOME", resPath);
        Configurer.loadConfiguration();
    }

}