package com.yoxaron.tennis_scoreboard;

import com.yoxaron.tennis_scoreboard.dto.PlayerDto;
import com.yoxaron.tennis_scoreboard.mapper.MatchMapper;
import com.yoxaron.tennis_scoreboard.mapper.PlayerMapper;
import com.yoxaron.tennis_scoreboard.model.domain.OngoingMatch;
import com.yoxaron.tennis_scoreboard.model.entity.Match;
import com.yoxaron.tennis_scoreboard.model.entity.Player;
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

        System.out.println("===========================Done===========================\n\n\n");

        PlayerDto playerDto = new PlayerDto(1L, "Dima");
        Player player = PlayerMapper.INSTANCE.toEntity(playerDto);
        System.out.println(player);

        System.out.println("===========================Done===========================\n\n\n");

        OngoingMatch ongoingMatch = new OngoingMatch(new PlayerDto(1L, "Dima"), new PlayerDto(2L, "Kek"));
        Match match = MatchMapper.INSTANCE.toMatch(ongoingMatch);
        System.out.println(match);

    }
}
