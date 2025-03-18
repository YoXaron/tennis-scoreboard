package com.yoxaron.tennis_scoreboard.mapper;

import com.yoxaron.tennis_scoreboard.dto.PlayerDto;
import com.yoxaron.tennis_scoreboard.model.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    PlayerDto toDto(Player player);

    Player toEntity(PlayerDto playerDto);
}
