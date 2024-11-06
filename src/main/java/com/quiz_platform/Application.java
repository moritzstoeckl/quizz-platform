package com.quiz_platform;

import com.quiz_platform.config.EnvConfig;
import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import org.slf4j.Logger;

@SpringBootApplication
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        // Load environment variables from .env file
        Dotenv dotenv = Dotenv.load();

        for (Map.Entry<String, String> storingDbEnvPair : EnvConfig.databaseStoring.entrySet()) {
            String envValue = dotenv.get(storingDbEnvPair.getValue());

            if (envValue == null) {
                logger.warn("Missing .env: " + storingDbEnvPair.getValue());
                continue;
            }

            System.setProperty(storingDbEnvPair.getKey(), envValue);
        }

        for (Map.Entry<String, String> authDbEnvPair : EnvConfig.authStoring.entrySet()) {
            String envValue = dotenv.get(authDbEnvPair.getValue());

            if (envValue == null) {
                logger.warn("Missing .env: " + authDbEnvPair.getValue());
                continue;
            }

            System.setProperty(authDbEnvPair.getKey(), envValue);
        }
        System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));


        SpringApplication.run(Application.class, args);
    }

}
