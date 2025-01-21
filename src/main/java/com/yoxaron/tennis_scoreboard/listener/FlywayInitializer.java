package com.yoxaron.tennis_scoreboard.listener;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

@WebListener
public class FlywayInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Dotenv dotenv = Dotenv.load();
            Flyway flyway = Flyway.configure()
                    .dataSource(dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"))
                    .load();
            flyway.migrate();
        } catch (Exception e) {
            throw new FlywayException("Failed to initialize Flyway", e);
        }
    }
}
