package com.yoxaron.tennis_scoreboard.model.score;

public class TiebreakGameScore extends GameScore<Integer> {

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    protected State processWonPoint(int playerNumber) {
        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);

        if (getPlayerScore(playerNumber) > 6 &&
            (getPlayerScore(playerNumber) - getOppositePlayerScore(playerNumber)) > 1) {

            if (playerNumber == 0) {
                return State.PLAYER_ONE_WON;
            }
            if (playerNumber == 1) {
                return State.PLAYER_TWO_WON;
            }
        }

        return State.ONGOING;
    }
}
