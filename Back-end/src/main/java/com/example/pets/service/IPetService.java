package com.example.pets.service;

import com.example.pets.entity.Pet;

import java.util.List;

public interface IPetService {
    Pet savePet(Pet pet,Long shelterId);
    Pet editPet(Pet pet);
    void removePet(Long petId);
    List<Pet> getPetsByShelterId(Long shelterId);
    void Backupdbtosql();


    void Restoredbfromsql(String s);
}