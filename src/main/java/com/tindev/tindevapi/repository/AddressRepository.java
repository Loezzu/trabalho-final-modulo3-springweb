package com.tindev.tindevapi.repository;

import com.tindev.tindevapi.entities.Address;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AddressRepository {

    private static final List<Address> listAddress = new ArrayList<>();
    private final AtomicInteger COUNTER = new AtomicInteger();

    public AddressRepository(){
        listAddress.add(new Address(COUNTER.incrementAndGet(), "Rua Amapa", "12", "Cachoeirinha", "94910-200"));
        listAddress.add(new Address(COUNTER.incrementAndGet(), "Rua Tabajara", "122", "Cachoeirinha", "94910-299"));
        listAddress.add(new Address(COUNTER.incrementAndGet(), "Rua Tapajos", "1", "Cachoeirinha", "94910-102"));
        listAddress.add(new Address(COUNTER.incrementAndGet(), "Rua Criciuma", "45", "Cachoeirinha", "09910-090"));
        listAddress.add(new Address(COUNTER.incrementAndGet(), "Rua Tamoios", "365", "Cachoeirinha", "94800-100"));
    }

    public Address create(Address address){
        address.setIdAddress(COUNTER.incrementAndGet());
        listAddress.add(address);
        return address;
    }

    public List<Address> list(){
        return listAddress;
    }

    public Address update(Integer id, Address newAddress) throws RegraDeNegocioException {
        Address updatedAddress = listAddress.stream()
                .filter(address -> address.getIdAddress().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Address not found"));
        updatedAddress.setCep(newAddress.getCep());
        updatedAddress.setNumber(newAddress.getNumber());
        updatedAddress.setStreet(newAddress.getStreet());
        updatedAddress.setCity(newAddress.getCity());
        return updatedAddress;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        Address addressToDelete = listAddress.stream()
                .filter(address -> address.getIdAddress().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Address not found"));
        listAddress.remove(addressToDelete);
    }

    public Address getAddressById(Integer id) throws RegraDeNegocioException {
        return listAddress.stream()
                .filter(address -> address.getIdAddress().equals(id))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Address not found"));
    }
}
