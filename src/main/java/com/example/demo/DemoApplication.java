package com.example.demo;//main sınıf

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // spring boot ile ayaklanacak bir uygulama anlamına geliyor, @ işlemine anotasyon denir

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
