package com.yoxaron.tennis_scoreboard.service;

import com.yoxaron.tennis_scoreboard.dto.PlayerDto;
import com.yoxaron.tennis_scoreboard.model.OngoingMatch;
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

    public OngoingMatch createOngoingMatch(PlayerDto firstPlayer, PlayerDto secondPlayer) {
        OngoingMatch ongoingMatch = new OngoingMatch(UUID.randomUUID(), firstPlayer, secondPlayer);
        ongoingMatches.put(ongoingMatch.getUuid(), ongoingMatch);
        return ongoingMatch;
    }

    public OngoingMatch getOngoingMatch(UUID uuid) {
        return ongoingMatches.get(uuid);
    }
}
