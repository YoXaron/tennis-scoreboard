package com.yoxaron.tennis_scoreboard.model.score;

import org.junit.jupiter.api.Test;

import static com.yoxaron.tennis_scoreboard.model.score.State.ONGOING;
import static com.yoxaron.tennis_scoreboard.model.score.State.PLAYER_ONE_WON;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SetScoreTest {

    @Test
    void TwentyFourPointsWinSet() {
        SetScore setScore = new SetScore();

        for (int i = 0; i < 23; i++) {
            assertEquals(ONGOING, setScore.processWonPoint(0));
        }

        assertEquals(PLAYER_ONE_WON, setScore.processWonPoint(0));
    }

}