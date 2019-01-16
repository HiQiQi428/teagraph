package org.luncert.tkglb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动Spring并运行Core
 */
@SpringBootApplication
public class App implements CommandLineRunner {

	@Autowired
	private Core core;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		core.run();
	}

}