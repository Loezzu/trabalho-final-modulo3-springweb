package com.tindev.tindevapi.controller.persoinfo;

import com.tindev.tindevapi.dto.persoInfo.PersoInfoCreateDTO;
import com.tindev.tindevapi.dto.persoInfo.PersoInfoDTO;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Api
@Validated
public interface PersoInfoAPI {

    @ApiOperation(value = "Retorna uma lista de dados pessoais")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de dados pessoais"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<List<PersoInfoDTO>> list();

    @ApiOperation(value = "Adiciona um dado pessoal")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um dado pessoal com um id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<PersoInfoDTO> create(@Valid @RequestBody PersoInfoCreateDTO persoInfoDTO) throws Exception;

    @ApiOperation(value = "Atualiza um dado pessoal")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um dado pessoal atualizado pelo id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<PersoInfoDTO> update(@PathVariable("persoInfoId") Integer id, @Valid @RequestBody PersoInfoCreateDTO persoInfoUpdate) throws Exception;


    @ApiOperation(value = "Deleta um dado pessoal")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem de sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<String> delete(@PathVariable("persoInfoId") Integer id) throws Exception;
}
