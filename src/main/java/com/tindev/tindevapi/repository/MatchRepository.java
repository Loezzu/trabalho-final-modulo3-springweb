package com.tindev.tindevapi.repository;

import com.tindev.tindevapi.entities.Like;
import com.tindev.tindevapi.entities.Match;
import com.tindev.tindevapi.entities.User;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class MatchRepository {
    private static final List<Match> matchList = new ArrayList<>();
    private final AtomicInteger COUNTER = new AtomicInteger();

    public MatchRepository() {
        matchList.add(new Match(COUNTER.incrementAndGet(), 1, 2));
    }

    public List<Match> list() {
        return matchList;
    }

    public Match addMatch(Integer matchedUserIdFirst, Integer matchedUserIdSecond) {
        Match match = new Match(COUNTER.incrementAndGet(), matchedUserIdFirst, matchedUserIdSecond);
        matchList.add(match);
        return match;
    }

    public void deleteMatch(Integer matchId) throws Exception {
        matchList.stream()
                .filter(match -> match.getMatchId().equals(matchId))
                .findFirst()
                .orElseThrow( () -> new RegraDeNegocioException("Match não encontrado"));

        matchList.removeIf(match -> match.getMatchId().equals(matchId));


    }


//    public void addMatch(User user1, User user2) {
//        if (user1.getProgLangs().equals(user2.getProgLangs())) {
//            user1.getMyMatches().add(user2);
//            user2.getMyMatches().add(user1);
//            System.out.println("DEU MATCH POIS A LINGUAGEM DE PROGRAMAÇÃO É COMPATÍVEL.");
//        } else {
//            System.out.println("INFELIZMENTE NÃO DEU MATCH POIS A LINGUAGEM DE PROGRAMAÇÃO É INCOMPATÍVEL.");
//        }
//    }

}
