package com.example.pets.service;

import com.example.pets.entity.Shelter;

import java.util.List;

public interface IShelterService {
    Shelter saveShelter(Shelter shelter, Long id);
    void removeShelter(Long shelterId);
    Shelter editShelter(Shelter shelter);
    List<Shelter> getAllShelters();

}
