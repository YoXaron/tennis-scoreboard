package com.yoxaron.tennis_scoreboard;

import com.yoxaron.tennis_scoreboard.repository.PlayerRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.flywaydb.core.Flyway;

public class MainRunner {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        Flyway flyway = Flyway.configure()
                .dataSource(dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"))
                .load();
        flyway.migrate();

//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        try (Session session = sessionFactory.openSession()) {
//
//            List<Player> players = session.createQuery("select p from Player p", Player.class).getResultList();
//            for (Player player : players) {
//                System.out.println(player);
//            }
//        }

        System.out.println("===========================Done===========================\n\n\n");

        PlayerRepository playerRepository = PlayerRepository.getInstance();
        playerRepository.findAll().stream().forEach(System.out::println);

    }
}
