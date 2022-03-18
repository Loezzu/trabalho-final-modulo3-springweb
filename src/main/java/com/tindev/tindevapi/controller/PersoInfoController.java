package com.tindev.tindevapi.controller;


import com.tindev.tindevapi.dto.persoInfo.PersoInfoCreateDTO;
import com.tindev.tindevapi.dto.persoInfo.PersoInfoDTO;
import com.tindev.tindevapi.entities.PersoInfo;
import com.tindev.tindevapi.service.PersoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/persoinfo")
public class PersoInfoController {


    @Autowired
    private PersoInfoService persoInfoService;


    @GetMapping
    public ResponseEntity<List<PersoInfoDTO>> list() throws Exception {
        return ResponseEntity.ok(persoInfoService.list());
    }

    @PostMapping
    public ResponseEntity<PersoInfoDTO> create(@Valid @RequestBody PersoInfoCreateDTO persoInfoDTO) throws Exception {
        return ResponseEntity.ok(persoInfoService.create(persoInfoDTO));
    }

    @PutMapping("/{persoInfoId}")
    public ResponseEntity<PersoInfoDTO> update(@Valid @PathVariable("persoInfoId") Integer id, @RequestBody PersoInfoCreateDTO persoInfoUpdate) throws Exception {
        return ResponseEntity.ok(persoInfoService.update(id, persoInfoUpdate));
    }

    @DeleteMapping("/{persoInfoId}")
    public ResponseEntity<String> delete(@PathVariable("persoInfoId") Integer id) throws Exception {
        persoInfoService.delete(id);
        return new ResponseEntity<>("PersoInfo deleted", HttpStatus.ACCEPTED);
    }

}
