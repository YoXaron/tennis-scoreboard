package com.yoxaron.tennis_scoreboard.service;

import com.yoxaron.tennis_scoreboard.dto.PlayerDto;
import com.yoxaron.tennis_scoreboard.model.domain.OngoingMatch;
import com.yoxaron.tennis_scoreboard.model.domain.PlayerScore;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchScoreCalculationService {

    private static final MatchScoreCalculationService INSTANCE = new MatchScoreCalculationService();

    private static final int POINTS_TO_GAME = 4;
    private static final int GAMES_TO_SET = 6;
    private static final int SETS_TO_MATCH = 2;
    private static final int TIEBREAK_POINTS = 7;
    private static final int TIEBREAK_DIFF = 2;

    public static MatchScoreCalculationService getInstance() {
        return INSTANCE;
    }

    public void updateScore(OngoingMatch match, Long winnerId) {
        PlayerDto playerOne = match.getPlayerOne();
        PlayerScore playerOneScore = match.getPlayerOneScore();
        PlayerScore playerTwoScore = match.getPlayerTwoScore();

        if (playerOne.id().equals(winnerId)) {
            addPoints(playerOneScore, playerTwoScore, match);
        } else {
            addPoints(playerTwoScore, playerOneScore, match);
        }

        checkMatchEnd(match);
    }

    private void addPoints(PlayerScore winnerScore, PlayerScore loserScore, OngoingMatch match) {
        if (isTiebreak(winnerScore, loserScore, match)) {
            processTiebreakPoint(winnerScore, loserScore, match);
        } else {
            processRegularPoint(winnerScore, loserScore, match);
        }
    }

    private void processRegularPoint(PlayerScore winnerScore, PlayerScore loserScore, OngoingMatch match) {
        winnerScore.increasePoints();

        int winnerPoints = winnerScore.getPoints();
        int loserPoints = loserScore.getPoints();

        if (winnerPoints >= POINTS_TO_GAME && winnerPoints - loserPoints >= 2) {
            winnerScore.increaseGames();
            winnerScore.dropPoints();
            loserScore.dropPoints();

            if (isSetFinished(winnerScore, loserScore)) {
                winnerScore.increaseSets();
                winnerScore.dropGames();
                loserScore.dropGames();
            }
        }
    }

    private boolean isSetFinished(PlayerScore winnerScore, PlayerScore loserScore) {
        int winnerGames = winnerScore.getGames();
        int loserGames = loserScore.getGames();

        if (winnerGames >= GAMES_TO_SET && winnerGames - loserGames >= 2) {
            return true;
        }

        return winnerGames == 7 && loserGames == 6;
    }

    private boolean isTiebreak(PlayerScore score1, PlayerScore score2, OngoingMatch match) {
        boolean isTiebreak = score1.getGames() == 6 && score2.getGames() == 6;
        match.setTiebreak(isTiebreak);
        return isTiebreak;
    }

    private void processTiebreakPoint(PlayerScore winnerScore, PlayerScore loserScore, OngoingMatch match) {
        winnerScore.increasePoints();

        int winnerPoints = winnerScore.getPoints();
        int loserPoints = loserScore.getPoints();

        if (winnerPoints >= TIEBREAK_POINTS && winnerPoints - loserPoints >= TIEBREAK_DIFF) {
            winnerScore.increaseGames();
            winnerScore.increaseSets();

            winnerScore.dropPoints();
            loserScore.dropPoints();
            winnerScore.dropGames();
            loserScore.dropGames();
        }
    }

    private void checkMatchEnd(OngoingMatch match) {
        PlayerScore playerOneScore = match.getPlayerOneScore();
        PlayerScore playerTwoScore = match.getPlayerTwoScore();

        if (playerOneScore.getSets() >= SETS_TO_MATCH || playerTwoScore.getSets() >= SETS_TO_MATCH) {
            match.setFinished(true);

            if (playerOneScore.getSets() >= SETS_TO_MATCH) {
                match.setWinner(match.getPlayerOne());
            } else {
                match.setWinner(match.getPlayerTwo());
            }
        }
    }
}
