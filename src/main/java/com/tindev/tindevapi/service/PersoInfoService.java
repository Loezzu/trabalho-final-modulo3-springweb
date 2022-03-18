package com.tindev.tindevapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tindev.tindevapi.dto.persoInfo.PersoInfoCreateDTO;
import com.tindev.tindevapi.dto.persoInfo.PersoInfoDTO;
import com.tindev.tindevapi.entities.PersoInfo;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.repository.PersoInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PersoInfoService {

    @Autowired
    private PersoInfoRepository persoInfoRepository;

    @Autowired
    private ObjectMapper objectMapper;


    public PersoInfoDTO create(PersoInfoCreateDTO persoInfoCreate) throws Exception {
        log.info("calling the Create method");

       PersoInfo persoInfo = objectMapper.convertValue(persoInfoCreate, PersoInfo.class);

        if (persoInfoRepository.getByEmail(persoInfo.getEmail())) {
            throw new RegraDeNegocioException("Email já cadastrado");
        }

       PersoInfo persoInfoCreated = persoInfoRepository.create(persoInfo);

        return objectMapper.convertValue(persoInfoCreated, PersoInfoDTO.class);
    }

    public List<PersoInfoDTO> list(){
        log.info("calling the List method");
        return persoInfoRepository.list()
                .stream()
                .map(persoInfo -> objectMapper.convertValue(persoInfo, PersoInfoDTO.class))
                .collect(Collectors.toList());
    }


    public PersoInfoDTO update(Integer id, PersoInfoCreateDTO persoInfoUpdate) throws Exception {
        log.info("calling the Update method");
        PersoInfo persoInfo = objectMapper.convertValue(persoInfoUpdate, PersoInfo.class);
        return objectMapper.convertValue(persoInfoRepository.update(id, persoInfo), PersoInfoDTO.class);
    }

    public void delete(Integer id) throws Exception {
        log.info("calling the Delete method");
        persoInfoRepository.delete(id);
    }


}
