package com.example.pets.service;

import com.example.pets.entity.Pet;
import com.example.pets.entity.Shelter;
import com.example.pets.repository.PetRepository;
import com.example.pets.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class PetService implements IPetService{
    @Autowired
    PetRepository petRepository;
    @Autowired
    ShelterRepository shelterRepository;
    @Override
    public Pet savePet(Pet pet,Long shelterId) {
        Shelter shelter = shelterRepository.findById(shelterId).orElse(null);
        pet.setShelter(shelter);
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getPetsByShelterId(Long shelterId) {
        return petRepository.findByShelterShelterId(shelterId);
    }
}
