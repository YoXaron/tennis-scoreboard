package com.yoxaron.tennis_scoreboard.model.domain;

import com.yoxaron.tennis_scoreboard.dto.PlayerDto;
import lombok.Data;

import java.util.UUID;

@Data
public class OngoingMatch {

    private UUID uuid;
    private PlayerDto playerOne;
    private PlayerDto playerTwo;
    private PlayerScore playerOneScore;
    private PlayerScore playerTwoScore;
    private boolean isFinished;
    private boolean isTiebreak;
    private PlayerDto winner;

    public OngoingMatch(PlayerDto playerOne, PlayerDto playerTwo) {
        uuid = UUID.randomUUID();
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        playerOneScore = new PlayerScore();
        playerTwoScore = new PlayerScore();
    }
}
