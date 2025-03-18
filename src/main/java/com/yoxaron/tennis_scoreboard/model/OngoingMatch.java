package com.yoxaron.tennis_scoreboard.model;

import com.yoxaron.tennis_scoreboard.dto.PlayerDto;
import com.yoxaron.tennis_scoreboard.model.score.MatchScore;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
public class OngoingMatch {

    private final UUID uuid;
    private final PlayerDto player1;
    private final PlayerDto player2;
    private final MatchScore matchScore = new MatchScore();
    private PlayerDto winner;
}
