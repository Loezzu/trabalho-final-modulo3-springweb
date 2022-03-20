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

    public Like darLike(Like user, Integer id) throws Exception {
        Like like = new Like(COUNTER.incrementAndGet(), id, user.getLikedUserId());
        if (like.getUserId().equals(like.getLikedUserId())) {
            throw new RegraDeNegocioException("Não é possível dar like para você mesmo");
        }
        likeList.add(like);
        return like;
    }

}
