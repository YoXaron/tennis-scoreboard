package com.yoxaron.tennis_scoreboard.model.score;

import org.junit.jupiter.api.Test;

import static com.yoxaron.tennis_scoreboard.model.score.State.ONGOING;
import static com.yoxaron.tennis_scoreboard.model.score.State.PLAYER_ONE_WON;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RegularGameScoreTest {

    @Test
    void fourPointsWinGame() {
        RegularGameScore regularGameScore = new RegularGameScore();

        //40-0
        for (int i = 0; i < 3; i++) {
            assertEquals(ONGOING, regularGameScore.processWonPoint(0));
        }

        assertEquals(PLAYER_ONE_WON, regularGameScore.processWonPoint(0));
    }

}