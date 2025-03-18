package com.yoxaron.tennis_scoreboard.model.score;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yoxaron.tennis_scoreboard.model.score.State.ONGOING;

public class MatchScore extends Score<Integer> {

    private static final int SET_TO_WIN = 2;
    private final Map<Integer, List<Integer>> gameResultsInSet;
    @Getter
    private SetScore currentSet;

    public MatchScore() {
        currentSet = new SetScore();
        gameResultsInSet = new HashMap<>();
    }

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    protected State processWonPoint(int playerNumber) {
        var state = currentSet.processWonPoint(playerNumber);

        if (state != ONGOING) {
            return setWon(playerNumber);
        }

        return state;
    }

    private State setWon(int playerNumber) {
        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);
        List<Integer> gameScore = new ArrayList<>();
        gameScore.add(currentSet.getPlayerScore(0));
        gameScore.add(currentSet.getPlayerScore(1));

        gameResultsInSet.put((getPlayerScore(0) + getPlayerScore(1)), gameScore);

        if (getPlayerScore(playerNumber) == SET_TO_WIN) {
            if (playerNumber == 0) {
                return State.PLAYER_ONE_WON;
            }
            if (playerNumber == 1) {
                return State.PLAYER_TWO_WON;
            }
        }

        this.currentSet = new SetScore();
        return State.ONGOING;
    }

    public Integer getGameResultsInSet(int setNumber, int playerNumber) {
        try {
            return gameResultsInSet.get(setNumber).get(playerNumber);
        } catch (NullPointerException e) {
            return -1;
        }
    }

    public String getCurrentGameScore(int playerNumber) {
        return getCurrentSet().getCurrentGame().getPlayerScore(playerNumber) instanceof RegularGamePoints
                ? ((RegularGamePoints) getCurrentSet().getCurrentGame().getPlayerScore(playerNumber)).getDisplayValue()
                : getCurrentSet().getCurrentGame().getPlayerScore(playerNumber).toString();
    }
}
