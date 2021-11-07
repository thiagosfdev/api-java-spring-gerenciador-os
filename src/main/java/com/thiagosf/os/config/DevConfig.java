package com.thiagosf.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.thiagosf.os.service.DBService;

@Configuration
@Profile(value = "dev")
public class DevConfig {

	@Autowired
	private DBService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String ddl;

	@Bean
	public boolean instanciaDB() {
		System.out.println("@Bean instaciaDB() invocado.....");
		System.out.println("Recuperando valor da chave spring.jpa.hibernate.ddl-auto=" + this.ddl);

		if (this.ddl.equals("create")) {
			this.dbService.instanciaDB();
		}

		return false;
	}

}
