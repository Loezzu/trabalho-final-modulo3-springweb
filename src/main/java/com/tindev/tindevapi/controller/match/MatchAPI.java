package com.tindev.tindevapi.controller.match;

import com.tindev.tindevapi.dto.address.AddressCreateDTO;
import com.tindev.tindevapi.dto.address.AddressDTO;
import com.tindev.tindevapi.dto.match.MatchDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Api
@Validated
public interface MatchAPI {

    @ApiOperation(value = "Retorna uma lista de matches")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de matches"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<List<MatchDTO>> list();

    @ApiOperation(value = "Retorna uma lista de matches de um usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de matches de um usuário"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<List<MatchDTO>> listMatchesOfTheUser(@PathVariable("userId") Integer userId);

    @ApiOperation(value = "Adiciona um match")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Adiciona um match"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<String> addMatch(@RequestParam("userId1") Integer userId1, @RequestParam("userId2") Integer userId2) throws Exception;


    @ApiOperation(value = "Deleta um match")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem de sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<String> deleteMatch(@PathVariable("matchId") Integer matchId) throws Exception;
}
