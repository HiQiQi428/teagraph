package org.luncert.tkglb;

import org.luncert.mullog.Mullog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public Mullog mullog() {
        return new Mullog("console"); // Mullog需要优化
    }

}