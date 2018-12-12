package org.luncert.tkglb;

import java.nio.file.Paths;

import org.luncert.configer.Configure;
import org.luncert.configer.ConfigureFactory;
import org.luncert.mullog.Mullog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Mullog mullog() {
        return new Mullog("console"); // Mullog需要优化
    }

    @Bean
    public Configure configure() {
        String configPath = Paths.get(System.getProperty("user.dir"), "config").toString();
        return new ConfigureFactory(configPath)
                    .allowLog(true)
                    .interval(1000)
                    .build();
    }

}