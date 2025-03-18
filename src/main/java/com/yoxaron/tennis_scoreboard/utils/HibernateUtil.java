package com.yoxaron.tennis_scoreboard.utils;

import com.yoxaron.tennis_scoreboard.model.entity.Match;
import com.yoxaron.tennis_scoreboard.model.entity.Player;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HibernateUtil {

    @Getter
    private static final SessionFactory sessionFactory;

    static {
        Dotenv dotenv = Dotenv.load();

        try {
            Configuration configuration = new Configuration();

            configuration.setProperty("hibernate.connection.provider_class",
                    "org.hibernate.hikaricp.internal.HikariCPConnectionProvider");

            configuration.setProperty("hibernate.hikari.jdbcUrl", dotenv.get("DB_URL"));
            configuration.setProperty("hibernate.hikari.username", dotenv.get("DB_USER"));
            configuration.setProperty("hibernate.hikari.password", dotenv.get("DB_PASSWORD"));
            configuration.setProperty("hibernate.hikari.driverClassName", dotenv.get("DB_DRIVER"));

            configuration.setProperty("hibernate.hikari.minimumIdle", "5");
            configuration.setProperty("hibernate.hikari.maximumPoolSize", "20");
            configuration.setProperty("hibernate.hikari.connectionTimeout", "30000");
            configuration.setProperty("hibernate.hikari.idleTimeout", "600000");
            configuration.setProperty("hibernate.hikari.maxLifetime", "1800000");

            configuration.setProperty("hibernate.dialect", dotenv.get("DB_DIALECT"));
            configuration.setProperty("hibernate.hbm2ddl.auto", "validate");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");

            configuration.addAnnotatedClass(Player.class);
            configuration.addAnnotatedClass(Match.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
