package com.example.pets.service;

import com.example.pets.entity.Pet;

import java.util.List;

public interface IPetService {
    Pet savePet(Pet pet,Long shelterId);
    List<Pet> getPetsByShelterId(Long shelterId);
}