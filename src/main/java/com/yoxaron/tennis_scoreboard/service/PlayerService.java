package com.yoxaron.tennis_scoreboard.service;

import com.yoxaron.tennis_scoreboard.dto.PlayerDto;
import com.yoxaron.tennis_scoreboard.mapper.PlayerMapper;
import com.yoxaron.tennis_scoreboard.model.entity.Player;
import com.yoxaron.tennis_scoreboard.repository.PlayerRepository;
import jakarta.persistence.PersistenceException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerService {

    private static final PlayerService INSTANCE = new PlayerService();
    private final PlayerRepository playerRepository = PlayerRepository.getInstance();

    public static PlayerService getInstance() {
        return INSTANCE;
    }

    public PlayerDto saveOrFindByName(String name) {
        try {
            var playerToSave = Player.builder().name(name).build();
            return PlayerMapper.INSTANCE.toDto(playerRepository.save(playerToSave));
        } catch (PersistenceException e) {
            Optional<Player> maybePlayer = playerRepository.findByName(name);
            if (maybePlayer.isPresent()) {
                return PlayerMapper.INSTANCE.toDto(maybePlayer.get());
            }
            throw e;
        }
    }
}
