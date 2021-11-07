package com.thiagosf.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.thiagosf.os.service.DBService;

@Configuration
@Profile(value = "test")
public class TestConfig {

	@Autowired
	private DBService dbService;

	@Bean
	public void instanciaDB() {
		System.out.println("@Bean instaciaDB() invocado.....");
		this.dbService.instanciaDB();
	}

}
