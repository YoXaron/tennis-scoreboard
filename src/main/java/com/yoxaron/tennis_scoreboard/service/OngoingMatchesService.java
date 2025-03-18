package com.yoxaron.tennis_scoreboard.service;

import com.yoxaron.tennis_scoreboard.dto.PlayerDto;
import com.yoxaron.tennis_scoreboard.model.domain.OngoingMatch;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OngoingMatchesService {

    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();
    private final Map<UUID, OngoingMatch> ongoingMatches = new ConcurrentHashMap<>();

    public static OngoingMatchesService getInstance() {
        return INSTANCE;
    }

    public OngoingMatch createOngoingMatch(PlayerDto playerOne, PlayerDto playerTwo) {
        OngoingMatch match = new OngoingMatch(playerOne, playerTwo);
        ongoingMatches.put(match.getUuid(), match);
        return match;
    }

    public OngoingMatch get(UUID uuid) {
        return ongoingMatches.get(uuid);
    }

    public void remove(UUID uuid) {
        ongoingMatches.remove(uuid);
    }
}
