package com.tindev.tindevapi.repository;

import com.tindev.tindevapi.entities.User;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.service.PersoInfoService;
import com.tindev.tindevapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LikeRepository {

    @Autowired
    private UserService userService;

    @Autowired
    private PersoInfoService persoInfoService;

    @Autowired
    private MatchRepository matchRepository;

    public boolean like(User user1, User user2) throws RegraDeNegocioException {
        if (user1.equals(user2)) {
            System.out.println("Não é possível dar like em você.");
        } else {
            user1.getMyLikes().add(user2);
        }
        if(user2.getMyLikes().contains(user1) && user1.getMyLikes().contains(user2)) {
            System.out.println("Podemos ter um casal, pois " +persoInfoService.getPersoInfoById(user1.getPersoInfoId()).getRealName()+
                    " e "+persoInfoService.getPersoInfoById(user2.getPersoInfoId()).getRealName()+" trocaram likes!" +
                    "\nEstamos analisando um possível match...");
            try {
                Thread.sleep(3000);
                matchRepository.addMatch(user1, user2);
                return true;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<User> listLikesByUser(User user){
        return user.getMyLikes();
    }


}
