package com.tindev.tindevapi.repository;

import com.tindev.tindevapi.entities.Like;
import com.tindev.tindevapi.entities.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class LikeRepository {
    private static final List<Like> likeList = new ArrayList<>();
    private final AtomicInteger COUNTER = new AtomicInteger();

    public LikeRepository() {
        likeList.add(new Like(COUNTER.getAndIncrement(), 1));
        likeList.add(new Like(COUNTER.getAndIncrement(), 2));
        likeList.add(new Like(COUNTER.getAndIncrement(), 3));
        likeList.add(new Like(COUNTER.getAndIncrement(), 4));
        likeList.add(new Like(COUNTER.getAndIncrement(), 5));
        likeList.add(new Like(COUNTER.getAndIncrement(), 5));
    }


    public List<Like> listLike() {
        return likeList;
    }

//    public boolean like(User user1, User user2) {
//        if (user1.equals(user2)) {
//            System.out.println("Não é possível dar like em você.");
//        } else {
//            user1.getMyLikes().add(user2);
//        }
//        if(user2.getMyLikes().contains(user1) && user1.getMyLikes().contains(user2)) {
//            System.out.println("Podemos ter um casal, pois "+user1.getPersoInfo().getRealName()+" e "+user2.getPersoInfo().getRealName()+" trocaram likes!" +
//                    "\nEstamos analisando um possível match...");
//            try {
//                Thread.sleep(3000);
//                Match match = new Match();
//                match.addMatch(user1, user2);
//                return true;
//            }
//            catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        return false;
//    }

}
