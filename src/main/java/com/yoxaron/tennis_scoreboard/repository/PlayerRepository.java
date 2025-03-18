package com.yoxaron.tennis_scoreboard.repository;

import com.yoxaron.tennis_scoreboard.model.entity.Player;
import org.hibernate.Session;

import java.util.Optional;

public class PlayerRepository extends CrudRepository<Long, Player> {

    private final static PlayerRepository INSTANCE = new PlayerRepository();

    private PlayerRepository() {
        super(Player.class);
    }

    public static PlayerRepository getInstance() {
        return INSTANCE;
    }

    public Optional<Player> findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Player where name = :name", Player.class)
                    .setParameter("name", name)
                    .uniqueResultOptional();
        }
    }
}
