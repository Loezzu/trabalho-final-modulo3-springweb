package com.tindev.tindevapi.repository;

import com.tindev.tindevapi.entities.PersoInfo;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PersoInfoRepository {
    private static List<PersoInfo> persoInfoList = new ArrayList<>();
    private AtomicInteger COUNTER = new AtomicInteger();

    public PersoInfoRepository() {
        persoInfoList.add(new PersoInfo(COUNTER.incrementAndGet(), "João da Silva", 20, "joaozinho@mail.com"));
        persoInfoList.add(new PersoInfo(COUNTER.incrementAndGet(), "Pedro da Silva", 20, "pedrinho@mail.com"));
        persoInfoList.add(new PersoInfo(COUNTER.incrementAndGet(), "Lucas da Silva", 20, "luquinhas@mail.com"));
        persoInfoList.add(new PersoInfo(COUNTER.incrementAndGet(), "Guilherme da Silva", 20, "gui@mail.com"));
        persoInfoList.add(new PersoInfo(COUNTER.incrementAndGet(), "Rafael da Silva", 20, "rafa@mail.com"));
    }

   public PersoInfo create(PersoInfo persoInfo) {
        persoInfo.setIdPersoInfo(COUNTER.incrementAndGet());
        persoInfoList.add(persoInfo);
        return persoInfo;
   }

   public List<PersoInfo> list() {
       return persoInfoList;
   }

   public PersoInfo update(Integer id, PersoInfo persoInfoUpdate) throws Exception {
        PersoInfo persoInfoUpdated = persoInfoList.stream()
                .filter(persoInfo -> persoInfo.getIdPersoInfo().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("PersoInfo not found"));
        persoInfoUpdated.setRealName(persoInfoUpdate.getRealName());
        persoInfoUpdated.setAge(persoInfoUpdate.getAge());
        persoInfoUpdated.setEmail(persoInfoUpdate.getEmail());
        return persoInfoUpdated;
   }

   public void delete(Integer id) throws Exception {
        PersoInfo persoInfoDeleted = persoInfoList.stream()
                .filter(persoInfo -> persoInfo.getIdPersoInfo().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("PersoInfo not found"));
        persoInfoList.remove(persoInfoDeleted);
   }

    public boolean getByEmail(String email) {
        return persoInfoList.stream()
                .anyMatch(persoInfo -> persoInfo.getEmail().equals(email));
    }

    public PersoInfo getPersoInfoById(Integer id) throws RegraDeNegocioException {
        return persoInfoList.stream()
                .filter(persoInfo -> persoInfo.getIdPersoInfo().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("PersoInfo not found"));
    }
}
