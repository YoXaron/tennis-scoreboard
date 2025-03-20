package com.yoxaron.tennis_scoreboard.service;

import com.yoxaron.tennis_scoreboard.dto.PlayerDto;
import com.yoxaron.tennis_scoreboard.model.domain.OngoingMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchScoreCalculationServiceTest {

    private static final Long PLAYER_ONE_ID = 1L;
    private static final Long PLAYER_TWO_ID = 2L;
    private MatchScoreCalculationService scoreService;
    private OngoingMatch match;
    private PlayerDto playerOne;
    private PlayerDto playerTwo;

    @BeforeEach
    void setUp() {
        scoreService = MatchScoreCalculationService.getInstance();
        playerOne = new PlayerDto(PLAYER_ONE_ID, "Игрок 1");
        playerTwo = new PlayerDto(PLAYER_TWO_ID, "Игрок 2");
        match = new OngoingMatch(playerOne, playerTwo);
    }

    @Test
    void testNewMatchInitialState() {
        assertEquals(0, match.getPlayerOneScore().getPoints());
        assertEquals(0, match.getPlayerOneScore().getGames());
        assertEquals(0, match.getPlayerOneScore().getSets());
        assertEquals(0, match.getPlayerTwoScore().getPoints());
        assertEquals(0, match.getPlayerTwoScore().getGames());
        assertEquals(0, match.getPlayerTwoScore().getSets());
        assertFalse(match.isFinished());
        assertFalse(match.isTiebreak());
        assertNull(match.getWinner());
    }

    @Test
    void testSinglePointAdded() {
        scoreService.updateScore(match, PLAYER_ONE_ID);
        assertEquals(1, match.getPlayerOneScore().getPoints());
        assertEquals(0, match.getPlayerOneScore().getGames());
        assertEquals(0, match.getPlayerOneScore().getSets());
        assertEquals(0, match.getPlayerTwoScore().getPoints());
    }

    @Test
    void testWinGame() {
        // Выигрываем 4 очка подряд для победы в гейме
        for (int i = 0; i < 4; i++) {
            scoreService.updateScore(match, PLAYER_ONE_ID);
        }

        assertEquals(0, match.getPlayerOneScore().getPoints()); // очки сбрасываются
        assertEquals(1, match.getPlayerOneScore().getGames()); // увеличивается счет по геймам
        assertEquals(0, match.getPlayerOneScore().getSets());
        assertEquals(0, match.getPlayerTwoScore().getPoints());
        assertEquals(0, match.getPlayerTwoScore().getGames());
    }

    @Test
    void testAdvantage() {
        // 3 очка для обоих игроков (40:40)
        for (int i = 0; i < 3; i++) {
            scoreService.updateScore(match, PLAYER_ONE_ID);
            scoreService.updateScore(match, PLAYER_TWO_ID);
        }

        // Преимущество первому игроку
        scoreService.updateScore(match, PLAYER_ONE_ID);
        assertEquals(4, match.getPlayerOneScore().getPoints());
        assertEquals(3, match.getPlayerTwoScore().getPoints());
        assertEquals(0, match.getPlayerOneScore().getGames());

        // Равенство снова
        scoreService.updateScore(match, PLAYER_TWO_ID);
        assertEquals(4, match.getPlayerOneScore().getPoints());
        assertEquals(4, match.getPlayerTwoScore().getPoints());

        // Два очка подряд для победы после равенства
        scoreService.updateScore(match, PLAYER_ONE_ID);
        scoreService.updateScore(match, PLAYER_ONE_ID);

        assertEquals(0, match.getPlayerOneScore().getPoints());
        assertEquals(1, match.getPlayerOneScore().getGames());
        assertEquals(0, match.getPlayerTwoScore().getPoints());
    }

    @Test
    void testWinSet() {
        // Выигрываем 6 геймов для победы в сете
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                scoreService.updateScore(match, PLAYER_ONE_ID);
            }
        }

        assertEquals(0, match.getPlayerOneScore().getPoints());
        assertEquals(0, match.getPlayerOneScore().getGames()); // гейм сбрасывается
        assertEquals(1, match.getPlayerOneScore().getSets()); // сет увеличивается
        assertEquals(0, match.getPlayerTwoScore().getSets());
    }

    @Test
    void testSetRequiresTwoGameLead() {
        // Игрок 1: 5 геймов
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                scoreService.updateScore(match, PLAYER_ONE_ID);
            }
        }

        // Игрок 2: 5 геймов
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                scoreService.updateScore(match, PLAYER_TWO_ID);
            }
        }

        // Еще один гейм игроку 1 (6:5)
        for (int j = 0; j < 4; j++) {
            scoreService.updateScore(match, PLAYER_ONE_ID);
        }

        assertEquals(6, match.getPlayerOneScore().getGames());
        assertEquals(5, match.getPlayerTwoScore().getGames());
        assertEquals(0, match.getPlayerOneScore().getSets()); // сет еще не выигран (нужен перевес в 2 гейма)

        // Еще один гейм игроку 1 (7:5) - теперь сет выигран
        for (int j = 0; j < 4; j++) {
            scoreService.updateScore(match, PLAYER_ONE_ID);
        }

        assertEquals(0, match.getPlayerOneScore().getGames());
        assertEquals(0, match.getPlayerTwoScore().getGames());
        assertEquals(1, match.getPlayerOneScore().getSets());
    }

    @Test
    void testTiebreak() {
        // Игрок 1: 5 геймов
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                scoreService.updateScore(match, PLAYER_ONE_ID);
            }
        }

        // Игрок 2: 5 геймов
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                scoreService.updateScore(match, PLAYER_TWO_ID);
            }
        }

        // Игрок 1: еще 1 гейм (6:5)
        for (int j = 0; j < 4; j++) {
            scoreService.updateScore(match, PLAYER_ONE_ID);
        }

        // Игрок 2: еще 1 гейм (6:6) - должен начаться тай-брейк
        for (int j = 0; j < 4; j++) {
            scoreService.updateScore(match, PLAYER_TWO_ID);
        }

        assertTrue(match.isTiebreak());
        assertEquals(6, match.getPlayerOneScore().getGames());
        assertEquals(6, match.getPlayerTwoScore().getGames());


        // Выигрываем 7 очков в тай-брейке (с разницей в 2 очка)
        for (int i = 0; i < 7; i++) {
            scoreService.updateScore(match, PLAYER_ONE_ID);
        }

        // После тай-брейка счет должен быть сброшен и увеличен счет сетов
        assertEquals(0, match.getPlayerOneScore().getPoints());
        assertEquals(0, match.getPlayerOneScore().getGames());
        assertEquals(1, match.getPlayerOneScore().getSets());
    }

    @Test
    void testTiebreakRequiresTwoPointLead() {
        // Игрок 1: 5 геймов
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                scoreService.updateScore(match, PLAYER_ONE_ID);
            }
        }

        // Игрок 2: 5 геймов
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                scoreService.updateScore(match, PLAYER_TWO_ID);
            }
        }

        // Игрок 1: еще 1 гейм (6:5)
        for (int j = 0; j < 4; j++) {
            scoreService.updateScore(match, PLAYER_ONE_ID);
        }

        // Игрок 2: еще 1 гейм (6:6) - должен начаться тай-брейк
        for (int j = 0; j < 4; j++) {
            scoreService.updateScore(match, PLAYER_TWO_ID);
        }

        assertTrue(match.isTiebreak());

        // Игрок 1: 6 очков в тай-брейке
        for (int i = 0; i < 6; i++) {
            scoreService.updateScore(match, PLAYER_ONE_ID);
        }

        // Игрок 2: 6 очков в тай-брейке (6:6)
        for (int i = 0; i < 6; i++) {
            scoreService.updateScore(match, PLAYER_TWO_ID);
        }

        assertEquals(6, match.getPlayerOneScore().getPoints());
        assertEquals(6, match.getPlayerTwoScore().getPoints());

        // Еще один балл игроку 1 (7:6)
        scoreService.updateScore(match, PLAYER_ONE_ID);
        assertEquals(7, match.getPlayerOneScore().getPoints());
        assertEquals(6, match.getPlayerTwoScore().getPoints());

        // Еще один балл игроку 1 (8:6) - он выигрывает тай-брейк
        scoreService.updateScore(match, PLAYER_ONE_ID);

        assertEquals(0, match.getPlayerOneScore().getPoints());
        assertEquals(0, match.getPlayerOneScore().getGames());
        assertEquals(1, match.getPlayerOneScore().getSets());
    }

    @Test
    void testMatchFinish() {
        // Выигрываем 2 сета для победы в матче
        for (int set = 0; set < 2; set++) {
            for (int game = 0; game < 6; game++) {
                for (int point = 0; point < 4; point++) {
                    scoreService.updateScore(match, PLAYER_ONE_ID);
                }
            }
        }

        assertTrue(match.isFinished());
        assertEquals(match.getPlayerOne(), match.getWinner());
    }

    @Test
    void testPlayerTwoWins() {
        // Игрок 2 выигрывает 2 сета
        for (int set = 0; set < 2; set++) {
            for (int game = 0; game < 6; game++) {
                for (int point = 0; point < 4; point++) {
                    scoreService.updateScore(match, PLAYER_TWO_ID);
                }
            }
        }

        assertTrue(match.isFinished());
        assertEquals(match.getPlayerTwo(), match.getWinner());
    }

}