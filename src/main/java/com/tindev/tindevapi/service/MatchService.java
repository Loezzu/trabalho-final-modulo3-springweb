package com.tindev.tindevapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.match.MatchDTO;
import com.tindev.tindevapi.entities.Match;
import com.tindev.tindevapi.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {


    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ObjectMapper objectMapper;


    public List<MatchDTO> list() {
        return matchRepository.list()
                .stream()
                .map(match -> objectMapper.convertValue(match, MatchDTO.class))
                .collect(Collectors.toList());
    }


    public MatchDTO addMatch(Integer userid1, Integer userid2) {

        Match match = matchRepository.addMatch(userid1, userid2);
        return objectMapper.convertValue(match, MatchDTO.class);


    }

    public void deleteMatch(Integer matchid) throws Exception {
        matchRepository.deleteMatch(matchid);
    }
}
