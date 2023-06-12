package de.haw.hamburg.stisys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"de.haw.hamburg.stisys.api", "de.haw.hamburg.stisys.core"})



public class StisysApplication {

	public static void main(String[] args) {
		SpringApplication.run(StisysApplication.class, args);
	}

}
