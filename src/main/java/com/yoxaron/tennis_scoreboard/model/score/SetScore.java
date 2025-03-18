package com.yoxaron.tennis_scoreboard.model.score;

import lombok.Getter;

import static com.yoxaron.tennis_scoreboard.model.score.State.ONGOING;

public class SetScore extends Score<Integer> {

    @Getter
    private GameScore<?> currentGame;

    public SetScore() {
        currentGame = new RegularGameScore();
    }

    @Override
    protected Integer getZeroScore() {
        return 0;
    }

    @Override
    protected State processWonPoint(int playerNumber) {
        State gameState = currentGame.processWonPoint(playerNumber);

//        if (gameState == PLAYER_ONE_WON) {
//            return gameWon(0);
//        } else if (gameState == PLAYER_TWO_WON) {
//            return gameWon(1);
//        }

        if (gameState != ONGOING) {
            return gameWon(playerNumber);
        }

        return ONGOING;
    }

    private State gameWon(int playerNumber) {

        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);
        this.currentGame = new RegularGameScore();

        if (getPlayerScore(playerNumber) == 6 || getPlayerScore(playerNumber) == 7) {

            // won with ADVANTAGE in 2 games
            if (getPlayerScore(playerNumber) - getOppositePlayerScore(playerNumber) > 1) {
                if (playerNumber == 0) {
                    return State.PLAYER_ONE_WON;
                }
                if (playerNumber == 1) {
                    return State.PLAYER_TWO_WON;
                }
            }

            // play tie-brake
            if (getPlayerScore(playerNumber) == 6 && getOppositePlayerScore(playerNumber) == 6) {
                this.currentGame = new TiebreakGameScore();
                return State.ONGOING;
            }

            // won after tie-brake
            if (getPlayerScore(playerNumber) == 7 && getOppositePlayerScore(playerNumber) == 6) {
                this.currentGame = new RegularGameScore();
                if (playerNumber == 0) {
                    return State.PLAYER_ONE_WON;
                }
                if (playerNumber == 1) {
                    return State.PLAYER_TWO_WON;
                }
            }
        }

        return State.ONGOING;

//        setPlayerScore(playerNumber, getPlayerScore(playerNumber) + 1);
//
//        this.currentGame = new RegularGameScore();
//
//        if (getPlayerScore(playerNumber) == 6) {
//            //в данном случае сет заканчивается при счете 6 геймов
//
//            //TODO 2 games advantage and tiebreak logic
//            //если счет по геймам 6-5 то играется гейм до 7-5 и игрок побеждает
//            //либо счет 6-6 и играется тай брейк
//            if (playerNumber == 0) {
//                return PLAYER_ONE_WON;
//            } else {
//                return PLAYER_TWO_WON;
//            }
//        } else {
//            return ONGOING;
//        }
    }
}
