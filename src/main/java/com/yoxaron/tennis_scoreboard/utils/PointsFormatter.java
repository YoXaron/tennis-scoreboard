package com.yoxaron.tennis_scoreboard.utils;

import com.yoxaron.tennis_scoreboard.model.domain.PlayerScore;

public class PointsFormatter {

    public static String format(PlayerScore firstPlayer, PlayerScore secondPlayer) {
        int pointsFirstPlayer = firstPlayer.getPoints();
        int pointsOpponent = secondPlayer.getPoints();

        if (firstPlayer.getGames() == 6 && secondPlayer.getGames() == 6) {
            return "" + pointsFirstPlayer;
        }

        if (pointsFirstPlayer >= 3 && pointsOpponent >= 3) {
            if (pointsFirstPlayer == pointsOpponent) {
                return "40";
            } else if (pointsFirstPlayer > pointsOpponent) {
                return "AD";
            } else {
                return "40";
            }
        } else {
            return switch (pointsFirstPlayer) {
                case 0 -> "0";
                case 1 -> "15";
                case 2 -> "30";
                case 3 -> "40";
                default -> "error";
            };
        }
    }
}
