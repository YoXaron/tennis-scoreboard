package com.yoxaron.tennis_scoreboard.mapper;

import com.yoxaron.tennis_scoreboard.dto.MatchDto;
import com.yoxaron.tennis_scoreboard.model.domain.OngoingMatch;
import com.yoxaron.tennis_scoreboard.model.entity.Match;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PlayerMapper.class)
public interface MatchMapper {

    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    MatchDto toMatchDto(Match match);

    Match toMatch(MatchDto matchDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "playerOne", target = "playerOne")
    @Mapping(source = "playerTwo", target = "playerTwo")
    @Mapping(source = "winner", target = "winner")
    Match toMatch(OngoingMatch ongoingMatch);
}
