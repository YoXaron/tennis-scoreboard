package com.yoxaron.tennis_scoreboard.model.score;

import java.util.ArrayList;
import java.util.List;

public abstract class Score<T> {

    private final List<T> playerScores = new ArrayList<>();

    protected Score() {
        playerScores.add(getZeroScore());
        playerScores.add(getZeroScore());
    }

    protected abstract T getZeroScore();

    protected abstract State processWonPoint(int playerNumber);

    public T getPlayerScore(int playerNumber) {
        return playerScores.get(playerNumber);
    }

    public T getOppositePlayerScore(int playerNumber) {
        return playerScores.get(playerNumber == 0 ? 1 : 0);
    }

    public void setPlayerScore(int playerNumber, T playerScore) {
        playerScores.set(playerNumber, playerScore);
    }

    public void setOppositePlayerScore(int playerNumber, T playerScore) {
        playerScores.set(playerNumber == 0 ? 1 : 0, playerScore);
    }
}
