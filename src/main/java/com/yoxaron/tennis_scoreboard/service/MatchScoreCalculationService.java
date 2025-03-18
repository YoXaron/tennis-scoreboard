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

        // Проверяем, закончился ли матч
        checkMatchEnd(match);
    }

    private void addPoints(PlayerScore winnerScore, PlayerScore loserScore, OngoingMatch match) {
        // Проверяем, идет ли тайбрейк
        if (isTiebreak(winnerScore, loserScore, match)) {
            processTiebreakPoint(winnerScore, loserScore, match);
        } else {
            processRegularPoint(winnerScore, loserScore, match);
        }
    }

    private void processRegularPoint(PlayerScore winnerScore, PlayerScore loserScore, OngoingMatch match) {
        winnerScore.increasePoints();

        // Преобразуем числовое значение очков в теннисный формат
        int winnerPoints = winnerScore.getPoints();
        int loserPoints = loserScore.getPoints();

        // Проверка на завершение гейма
        if (winnerPoints >= POINTS_TO_GAME && winnerPoints - loserPoints >= 2) {
            winnerScore.increaseGames();
            winnerScore.dropPoints();
            loserScore.dropPoints();

            // Проверка на завершение сета
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

        // Обычная победа в сете (6 геймов с разницей в 2 гейма)
        if (winnerGames >= GAMES_TO_SET && winnerGames - loserGames >= 2) {
            return true;
        }

        // Победа в тайбрейке (7-6)
        if (winnerGames == 7 && loserGames == 6) {
            return true;
        }

        return false;
    }

    private boolean isTiebreak(PlayerScore score1, PlayerScore score2, OngoingMatch match) {
        boolean isTiebreak = score1.getGames() == 6 && score2.getGames() == 6;
        match.setTiebreak(isTiebreak);
        return isTiebreak;
    }

    private void processTiebreakPoint(PlayerScore winnerScore, PlayerScore loserScore, OngoingMatch match) {
        winnerScore.increasePoints();

        // В тайбрейке очки считаются по-другому: 1, 2, 3... (не 15, 30, 40)
        int winnerPoints = winnerScore.getPoints();
        int loserPoints = loserScore.getPoints();

        // Для победы в тайбрейке нужно набрать минимум 7 очков с разницей в 2 очка
        if (winnerPoints >= TIEBREAK_POINTS && winnerPoints - loserPoints >= TIEBREAK_DIFF) {
            winnerScore.increaseGames(); // Увеличиваем количество геймов (становится 7-6)
            winnerScore.increaseSets();  // Увеличиваем количество сетов

            // Сбрасываем очки и геймы
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

            // Здесь можно добавить определение победителя, если требуется
            if (playerOneScore.getSets() >= SETS_TO_MATCH) {
                match.setWinner(match.getPlayerOne());
            } else {
                match.setWinner(match.getPlayerTwo());
            }
        }
    }
}
