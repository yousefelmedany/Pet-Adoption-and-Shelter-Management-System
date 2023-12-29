package com.example.pets.service;

import com.example.pets.entity.Shelter;

public interface IShelterService {
    Shelter saveShelter(Shelter shelter, Long id);

    void removeShelter(Long shelterId);

    Shelter editShelter(Shelter shelter);
}
