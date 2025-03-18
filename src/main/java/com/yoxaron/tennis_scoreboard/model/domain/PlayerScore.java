package com.yoxaron.tennis_scoreboard.model.domain;

import lombok.Data;

@Data
public class PlayerScore {
    private int points;
    private int games;
    private int sets;

    public void increasePoints() {
        points++;
    }

    public void increaseGames() {
        games++;
    }

    public void increaseSets() {
        sets++;
    }

    public void dropPoints() {
        points = 0;
    }

    public void dropGames() {
        games = 0;
    }
}
