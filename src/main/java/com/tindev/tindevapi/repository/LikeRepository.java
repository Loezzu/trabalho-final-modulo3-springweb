package com.tindev.tindevapi.repository;

import com.tindev.tindevapi.dto.user.UserDTO;
import com.tindev.tindevapi.entities.Like;
import com.tindev.tindevapi.entities.User;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.service.PersoInfoService;
import com.tindev.tindevapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunMisc;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class LikeRepository {


    @Autowired
    private PersoInfoService persoInfoService;

    @Autowired
    private MatchRepository matchRepository;

    private static final List<Like> likeList = new ArrayList<>();
    private final AtomicInteger COUNTER = new AtomicInteger();

    public LikeRepository() {
        likeList.add(new Like(COUNTER.incrementAndGet(), 1, 2));
        likeList.add(new Like(COUNTER.incrementAndGet(), 1, 3));
    }

    public List<Like> list() {
        return likeList;
    }

    public List<Like> listLikesByUserId(UserDTO user) {

        return likeList.stream()
                .filter(like -> like.getUserId().equals(user.getUserId()))
                .collect(Collectors.toList());
    }

    public Like darLike(Like user, Like userLiked) throws RegraDeNegocioException {
//        if (user.getUserId().equals(userLiked.getUserId())) {
//            throw new RegraDeNegocioException("Não é possível dar like para você mesmo");
//        }
        Like like = new Like(COUNTER.incrementAndGet(), user.getUserId(), userLiked.getLikedUserId());
        likeList.add(like);
        return like;

    }

    public Like getUserId(Integer id) {
        return likeList.stream()
                .filter(like -> like.getUserId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Like getUserIdLiked(Integer id) {
        return likeList.stream()
                .filter(like -> like.getLikedUserId().equals(id))
                .findFirst()
                .orElse(null);
    }


//    public User like(User user1, User user2) throws RegraDeNegocioException {
//        if (user1.equals(user2)) {
//            System.out.println("Não é possível dar like em você.");
//        } else {
//            user1.getMyLikes().add(user2);
//        }
//        if(user2.getMyLikes().contains(user1) && user1.getMyLikes().contains(user2)) {
//            System.out.println("Podemos ter um casal, pois " +persoInfoService.getPersoInfoById(user1.getPersoInfoId()).getRealName()+
//                    " e "+persoInfoService.getPersoInfoById(user2.getPersoInfoId()).getRealName()+" trocaram likes!" +
//                    "\nEstamos analisando um possível match...");
//            try {
//                Thread.sleep(3000);
//                matchRepository.addMatch(user1, user2);
//            }
//            catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        return darLike(user1, user2) ? user1 : null;
//    }

//    public List<User> listLikesByUser(User user){
//        return user.getMyLikes();
//    }


}
