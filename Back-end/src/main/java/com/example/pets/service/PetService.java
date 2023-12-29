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
public class PetService implements IPetService {
    @Autowired
    PetRepository petRepository;
    @Autowired
    ShelterRepository shelterRepository;

    @Override
    public Pet savePet(Pet pet, Long shelterId) {
        Shelter shelter = shelterRepository.findById(shelterId).orElse(null);
        pet.setShelter(shelter);
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getPetsByShelterId(Long shelterId) {
        return petRepository.findByShelterShelterId(shelterId);
    }

    @Override
    public Pet editPet(Pet pet) {
        Pet oldPet = petRepository.findById(pet.getPetId()).orElse(null);
        if (oldPet == null) return null;
        oldPet.setPetName(pet.getPetName());
        oldPet.setAge(pet.getAge());
        oldPet.setGender(pet.getGender());
        oldPet.setBreed(pet.getBreed());
        oldPet.setSpecies(pet.getSpecies());
        oldPet.setColor(pet.getColor());
        oldPet.setHealthStatus(pet.getHealthStatus());
        oldPet.setVaccination(pet.getVaccination());
        oldPet.setSpayNeuter(pet.getSpayNeuter());
        oldPet.setTraining(pet.getTraining());
        return petRepository.save(oldPet);
    }
    @Override
    public void removePet(Long petId) {
        petRepository.deleteById(petId);
    }
}
