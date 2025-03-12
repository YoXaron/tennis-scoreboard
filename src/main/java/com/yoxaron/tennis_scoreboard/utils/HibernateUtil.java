package com.yoxaron.tennis_scoreboard.utils;

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

            configuration.setProperty("hibernate.connection.url", dotenv.get("DB_URL"));
            configuration.setProperty("hibernate.connection.username", dotenv.get("DB_USER"));
            configuration.setProperty("hibernate.connection.password", dotenv.get("DB_PASSWORD"));
            configuration.setProperty("hibernate.connection.driver_class", dotenv.get("DB_DRIVER"));
            configuration.setProperty("hibernate.dialect", dotenv.get("DB_DIALECT"));
            configuration.setProperty("hibernate.hbm2ddl.auto", "validate");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");

            configuration.addAnnotatedClass(Player.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
