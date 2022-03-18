package com.tindev.tindevapi.repository;

import com.tindev.tindevapi.entities.User;
import com.tindev.tindevapi.enums.Gender;
import com.tindev.tindevapi.enums.Pref;
import com.tindev.tindevapi.enums.ProgLangs;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.service.AddressService;
import com.tindev.tindevapi.service.PersoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepository {

    @Autowired
    private AddressService addressService;

    @Autowired
    private PersoInfoService persoInfoService;

    private static final List<User> listUsers = new ArrayList<>();
    private final AtomicInteger COUNTER = new AtomicInteger();


    public UserRepository(){
        listUsers.add(new User(COUNTER.incrementAndGet(), 1, 1, "joao", "joao123", ProgLangs.JAVA, Gender.MALE, Pref.BOTH));
        listUsers.add(new User(COUNTER.incrementAndGet(), 2, 2, "pedro", "pedro123", ProgLangs.JAVA, Gender.MALE, Pref.BOTH));
        listUsers.add(new User(COUNTER.incrementAndGet(), 3, 3, "lucas", "lucas123", ProgLangs.JAVA, Gender.MALE, Pref.BOTH));
        listUsers.add(new User(COUNTER.incrementAndGet(), 4, 4, "gui", "gui123", ProgLangs.JAVA, Gender.MALE, Pref.BOTH));
        listUsers.add(new User(COUNTER.incrementAndGet(), 5, 5, "rafa", "rafa123", ProgLangs.JAVA, Gender.MALE, Pref.BOTH));
    }

    public User create(User user){
        user.setUserId(COUNTER.incrementAndGet());
        listUsers.add(user);
        return user;
    }

    public List<User> list(){
        return listUsers;
    }

    public User update(Integer id, User newUser) throws Exception {
        User updatedUser = listUsers.stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("User not found"));
        updatedUser.setPersoInfoId(newUser.getPersoInfoId());
        updatedUser.setAddressId(newUser.getAddressId());
        updatedUser.setUsername(newUser.getUsername());
        updatedUser.setPassword(newUser.getPassword());
        updatedUser.setProgLangs(newUser.getProgLangs());
        updatedUser.setGender(newUser.getGender());
        updatedUser.setPref(newUser.getPref());
        return updatedUser;
    }

    public void delete(Integer id) throws Exception {
        User userToDelete = listUsers.stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("User not found"));
        listUsers.remove(userToDelete);
    }

    public User getUserById(Integer id) throws Exception {
        return listUsers.stream()
                .filter(user -> user.getUserId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("User not found"));
    }

    public void getAddressById(Integer id) throws Exception {
        addressService.listAddress().stream()
                .filter(address -> address.getIdAddress().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Address not found"));
    }

    public void getPersoInfoById(Integer id) throws Exception {
        persoInfoService.list().stream()
                .filter(persoInfo -> persoInfo.getIdPersoInfo().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Personal information not found"));
    }
}