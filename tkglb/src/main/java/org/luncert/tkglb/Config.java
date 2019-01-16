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
        // mullog是我自己写的日志工具，有不合理的地方。。。需要优化
        return new Mullog("console");
    }

    @Bean
    public Configure configure() {
        // configer也是我写的配置管理工具，能用。。。
        String configPath = Paths.get(System.getProperty("user.dir"), "config").toString();
        return new ConfigureFactory(configPath)
                    .allowLog(true)
                    .interval(1000)
                    .build();
    }

}