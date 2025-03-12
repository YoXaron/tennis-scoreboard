package com.yoxaron.tennis_scoreboard;

import com.yoxaron.tennis_scoreboard.model.entity.Player;
import com.yoxaron.tennis_scoreboard.utils.HibernateUtil;
import io.github.cdimascio.dotenv.Dotenv;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MainRunner {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        Flyway flyway = Flyway.configure()
                .dataSource(dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"))
                .load();
        flyway.migrate();

        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
             Session session = sessionFactory.openSession()) {

            List<Player> players = session.createQuery("select p from Player p", Player.class).getResultList();
            for (Player player : players) {
                System.out.println(player);
            }
        }
    }
}
