package com.quiz_platform;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// Load environment variables from .env file
		Dotenv dotenv = Dotenv.load();

		// Set environment variables as system properties
		System.setProperty("storingDb.rootPassword", dotenv.get("STORING_DB_ROOT_PASSWORD"));
		System.setProperty("storingDb.user", dotenv.get("STORING_DB_USER"));
		System.setProperty("storingDb.password", dotenv.get("STORING_DB_PASSWORD"));
		System.setProperty("storingDb.name", dotenv.get("STORING_DB_NAME"));
		System.setProperty("storingDb.port", dotenv.get("STORING_DB_PORT"));
		System.setProperty("storingDb.domain", dotenv.get("STORING_DB_DOMAIN"));

		System.setProperty("authDb.rootPassword", dotenv.get("AUTH_DB_ROOT_PASSWORD"));
		System.setProperty("authDb.user", dotenv.get("AUTH_DB_USER"));
		System.setProperty("authDb.password", dotenv.get("AUTH_DB_PASSWORD"));
		System.setProperty("authDb.name", dotenv.get("AUTH_DB_NAME"));
		System.setProperty("authDb.port", dotenv.get("AUTH_DB_PORT"));
		System.setProperty("authDb.domain", dotenv.get("AUTH_DB_DOMAIN"));
		SpringApplication.run(Application.class, args);
	}

}
