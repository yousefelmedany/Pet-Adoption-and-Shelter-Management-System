package com.example.pets.service;

import com.example.pets.entity.Shelter;
import com.example.pets.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class ShelterService implements IShelterService{
    @Autowired
    ShelterRepository shelterRepository;
    @Override
    public Shelter saveShelter(Shelter shelter) {
        return shelterRepository.save(shelter);
    }
}
