package com.it.edn.rabbitmqproducerdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitmqProducerDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// you can test something right here...
	}

}
