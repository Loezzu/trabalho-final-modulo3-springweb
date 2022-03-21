package com.tindev.tindevapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.match.MatchDTO;
import com.tindev.tindevapi.dto.user.UserDTO;
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
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmailService emailService;


    public List<MatchDTO> list() {
        return matchRepository.list()
                .stream()
                .map(this::getMatchDTOWithUsernameAndEmail)
                .collect(Collectors.toList());
    }

    public List<MatchDTO> listMatchesOfTheUser(Integer idUser){
        return matchRepository.listMatchesOfUser(idUser).stream()
                .map(this::getMatchDTOWithUsernameAndEmail).collect(Collectors.toList());
    }

    public MatchDTO addMatch(Integer userid1, Integer userid2) throws Exception {
        Match match = matchRepository.addMatch(userid1, userid2);
        MatchDTO matchDTO = getMatchDTOWithUsernameAndEmail(match);
        if (matchDTO != null) {
            emailService.sendEmailPessoa(matchDTO);
        }

        return matchDTO;

    }

    public void deleteMatch(Integer matchid) throws Exception {
        matchRepository.deleteMatch(matchid);
    }

    private MatchDTO getMatchDTOWithUsernameAndEmail(Match match) {
        try {
            MatchDTO matchDTO = objectMapper.convertValue(match, MatchDTO.class);

            String usernameFirst =  userService.getUsernameById(match.getMatchedUserFirst());
            String emailFirst =  userService.getEmailById(match.getMatchedUserFirst());
            String usernameSecond =  userService.getUsernameById(match.getMatchedUserSecond());
            String emailSecond =  userService.getEmailById(match.getMatchedUserSecond());

            matchDTO.setMatchedUserNameFirst(usernameFirst);
            matchDTO.setMachedUserEmailFirst(emailFirst);
            matchDTO.setMatchedUserNameSecond(usernameSecond);
            matchDTO.setMatchedUserEmailSecond(emailSecond);

            return matchDTO;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }


}
