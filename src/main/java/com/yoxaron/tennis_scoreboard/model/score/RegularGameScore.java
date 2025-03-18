package com.yoxaron.tennis_scoreboard.model.score;

import static com.yoxaron.tennis_scoreboard.model.score.RegularGamePoints.*;
import static com.yoxaron.tennis_scoreboard.model.score.State.*;

public class RegularGameScore extends GameScore<RegularGamePoints> {

    @Override
    protected RegularGamePoints getZeroScore() {
        return ZERO;
    }

    @Override
    protected State processWonPoint(int playerNumber) {
        var playerScore = getPlayerScore(playerNumber);
        var oppositePlayerScore = getOppositePlayerScore(playerNumber);

        if (playerScore.ordinal() <= THIRTY.ordinal()) {
            setPlayerScore(playerNumber, playerScore.next());
        } else if (playerScore == FORTY) {
            if (oppositePlayerScore == FORTY) {
                setPlayerScore(playerNumber, ADVANTAGE);
            } else if (oppositePlayerScore == ADVANTAGE) {
                setOppositePlayerScore(playerNumber, FORTY);
            } else {
                return playerNumber == 0 ? PLAYER_ONE_WON : PLAYER_TWO_WON;
            }
        } else if (playerScore == ADVANTAGE) {
            return playerNumber == 0 ? PLAYER_ONE_WON : PLAYER_TWO_WON;
        } else {
            throw new IllegalStateException("Cannot call processWonPoint() on ADVANTAGE");
        }

        return ONGOING;
    }
}
