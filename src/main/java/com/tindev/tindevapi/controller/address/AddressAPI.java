package com.tindev.tindevapi.controller.address;

import com.tindev.tindevapi.dto.address.AddressCreateDTO;
import com.tindev.tindevapi.dto.address.AddressDTO;
import com.tindev.tindevapi.dto.persoInfo.PersoInfoCreateDTO;
import com.tindev.tindevapi.dto.persoInfo.PersoInfoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Api
@Validated
public interface AddressAPI {

    @ApiOperation(value = "Retorna uma lista de endereços")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de endereços"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<List<AddressDTO>> listAddress();

    @ApiOperation(value = "Adiciona um endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um endereço com um id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<AddressDTO> postAddress(@Valid @RequestBody AddressCreateDTO addressCreateDTO);

    @ApiOperation(value = "Atualiza um endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um endereço atualizado pelo id"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<AddressDTO> updatedAddress(@PathVariable("addressId") Integer id,
                                              @Valid @RequestBody AddressCreateDTO addressCreateDTO) throws Exception;


    @ApiOperation(value = "Deleta um endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem de sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<String> deleteAddress(@PathVariable("addressId") Integer id);

    @ApiOperation(value = "Retorna um endereço por ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um endereço com ID"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),})
    ResponseEntity<AddressDTO> getByIdAddress(@PathVariable("addressId") Integer id) throws Exception;
}
