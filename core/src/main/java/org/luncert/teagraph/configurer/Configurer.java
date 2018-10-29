package org.luncert.teagraph.configurer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Configurer {

    private Configurer() {}

    public static Configuration loadConfiguration() throws Exception {
        File cfgFile = Paths.get(System.getProperty("TEAGRAPH_HOME"), "conf", "config.xml").toFile();
        InputStream is = new FileInputStream(cfgFile);
        XmlParserHandler handler = new XmlParserHandler(new RouteTree(ConfigHandler.class));
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(is, handler);
        return ConfigHandler.getConfiguration();
    }

}