package com.example.pets.service;

import com.example.pets.entity.Adopter;
import com.example.pets.entity.Application;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IAdopterService {
    Adopter saveAdopter(Adopter adopter);

    Adopter adoptPet(Long adopterId, Long petId);
    Application makeApplication(Long adopterId, Long petId, String description);
    List<Application> getApplicationsByAdopter(Long adopterId);
    Adopter getAdopterById(Long adopterId);
}
