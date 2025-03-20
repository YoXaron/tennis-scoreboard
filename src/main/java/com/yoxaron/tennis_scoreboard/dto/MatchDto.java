package com.yoxaron.tennis_scoreboard.dto;

public record MatchDto(PlayerDto playerOne, PlayerDto playerTwo, PlayerDto winner) {
}
