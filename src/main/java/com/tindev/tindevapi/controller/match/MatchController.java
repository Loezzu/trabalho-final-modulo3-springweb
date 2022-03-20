package com.tindev.tindevapi.controller.match;

import com.tindev.tindevapi.dto.match.MatchDTO;
import com.tindev.tindevapi.service.MatchService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
@Api(value = "5 - Match API", produces = MediaType.APPLICATION_JSON_VALUE, tags = {"5 - Match API"}, description = "Match Controls")
public class MatchController {


    @Autowired
    private MatchService matchService;


    @GetMapping
    public List<MatchDTO> list() {
        return matchService.list();
    }

    @PostMapping("/{userId1}/{userId2}")
    public MatchDTO addMatch(@RequestParam("userId1") Integer userId1, @RequestParam("userId2") Integer userId2) throws Exception {
        return matchService.addMatch(userId1, userId2);
    }

    @DeleteMapping("/{matchId}")
    public void deleteMatch(@PathVariable("matchId") Integer matchId) throws Exception {
        matchService.deleteMatch(matchId);
    }

}
