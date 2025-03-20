package com.yoxaron.tennis_scoreboard.service;

import com.yoxaron.tennis_scoreboard.dto.MatchDto;
import com.yoxaron.tennis_scoreboard.mapper.MatchMapper;
import com.yoxaron.tennis_scoreboard.model.domain.OngoingMatch;
import com.yoxaron.tennis_scoreboard.model.entity.Match;
import com.yoxaron.tennis_scoreboard.repository.MatchRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FinishedMatchesPersistenceService {

    private static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();
    private final MatchRepository matchRepository = MatchRepository.getInstance();

    public static FinishedMatchesPersistenceService getInstance() {
        return INSTANCE;
    }

    public MatchDto save(OngoingMatch ongoingMatch) {
        Match match = MatchMapper.INSTANCE.toMatch(ongoingMatch);
        return MatchMapper.INSTANCE.toMatchDto(matchRepository.save(match));
    }

    public List<MatchDto> find(String name, int page, int pageSize) {
        List<Match> matches = matchRepository.findByNameWithPagination(name, page, pageSize);
        return matches.stream()
                .map(MatchMapper.INSTANCE::toMatchDto)
                .toList();
    }

    public int countMatches(String name) {
        return matchRepository.countMatches(name);
    }
}
