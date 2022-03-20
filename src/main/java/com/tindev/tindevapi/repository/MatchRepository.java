package com.tindev.tindevapi.repository;

import com.tindev.tindevapi.entities.Like;
import com.tindev.tindevapi.entities.Match;
import com.tindev.tindevapi.entities.User;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MatchRepository {
    private static final List<Match> matchList = new ArrayList<>();
    private final AtomicInteger COUNTER = new AtomicInteger();

//    public MatchRepository() {
//        matchList.add(new Match(COUNTER.incrementAndGet(), 1, 2));
//    }

    @Autowired
    private UserService userService;

    public List<Match> list() {
        return matchList;
    }

    public Match addMatch(Integer matchedUserIdFirst, Integer matchedUserIdSecond) throws Exception {
        if(userService.getUserById(matchedUserIdFirst).getProgLangs().equals(userService.getUserById(matchedUserIdSecond).getProgLangs())){
            Match match = new Match(COUNTER.incrementAndGet(), matchedUserIdFirst, matchedUserIdSecond);
            matchList.add(match);
            return match;
        }
        return null;
    }

    public void deleteMatch(Integer matchId) throws Exception {
        matchList.stream()
                .filter(match -> match.getMatchId().equals(matchId))
                .findFirst()
                .orElseThrow( () -> new RegraDeNegocioException("Match não encontrado"));

        matchList.removeIf(match -> match.getMatchId().equals(matchId));


    }
}
