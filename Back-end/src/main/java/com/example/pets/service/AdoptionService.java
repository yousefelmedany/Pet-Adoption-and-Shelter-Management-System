package com.example.pets.service;

import com.example.pets.entity.Adopter;
import com.example.pets.entity.Application;
import com.example.pets.entity.ApplicationId;
import com.example.pets.entity.Pet;
import com.example.pets.repository.AdopterRepository;
import com.example.pets.repository.ApplicationRepository;
import com.example.pets.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class AdoptionService implements IAdopterService{
    @Autowired
    private AdopterRepository adopterRepository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public Adopter saveAdopter(Adopter adopter) {
        return adopterRepository.save(adopter);
    }
    @Override
    public Adopter adoptPet(Long adopterId, Long petId) {
        Adopter adopter= adopterRepository.findById(adopterId).orElse(null);
        Pet pet= petRepository.findById(petId).orElse(null);
        if(adopter==null || pet==null) return null;
        pet.setAdopter(adopter);
        return adopterRepository.save(adopter);
    }
    @Override
    public Application makeApplication(Long adopterId, Long petId, String description) {
        Adopter adopter= adopterRepository.findById(adopterId).orElse(null);
        Pet pet= petRepository.findById(petId).orElse(null);

        if(adopter==null || pet==null) return null;

        ApplicationId applicationId=new ApplicationId();
        applicationId.setAdopter(adopterId);
        applicationId.setPet(petId);
        Application oldApplication=applicationRepository.findById(applicationId).orElse(null);
        if(oldApplication!=null) return null;
        Application application=new Application();
        application.setAdopter(adopter);
        application.setPet(pet);
        application.setDescription(description);
        application.setStatus("Pending");
        return applicationRepository.save(application);
    }
    @Override
    public List<Application> getApplicationsByAdopter(Long adopterId) {
        return applicationRepository.findByAdopterAdopterId(adopterId);
    }
@Override
    public Adopter getAdopterById(Long adopterId) {
        return adopterRepository.findById(adopterId).orElse(null);
    }

}
