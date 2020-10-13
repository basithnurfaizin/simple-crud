package com.nurfaizin.backend;

import com.nurfaizin.backend.entity.Shopping;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleAppApplication.class, args);
		Shopping shopping = new Shopping();
	}

}
