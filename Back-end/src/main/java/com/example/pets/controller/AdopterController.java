package com.example.pets.controller;

import com.example.pets.entity.Adopter;
import com.example.pets.entity.Application;
import com.example.pets.service.IAdopterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/adopter")
public class AdopterController {
    @Autowired
    private IAdopterService adopterService;

    @PostMapping("/save")
    public Adopter saveAdopter(@RequestBody Adopter adopter) {
        return adopterService.saveAdopter(adopter);

    }
    @PostMapping("/adoptpet")
    public Adopter adoptPet(@RequestParam("adopterid") Long adopterId, @RequestParam("petid") Long petId) {
        return adopterService.adoptPet(adopterId, petId);
    }
    @PostMapping("/makeapplication")
    public Application makeApplication(@RequestParam("adopterid") Long adopterId, @RequestParam("petid") Long petId, @RequestParam("description") String description) {
        return adopterService.makeApplication(adopterId, petId, description);
    }
    @GetMapping("/getapplications")
    public List<Application> getApplicationsByAdopter(@RequestParam("adopterid") Long adopterId) {
        return adopterService.getApplicationsByAdopter(adopterId);
    }
    @GetMapping("/getadopter")
    public Adopter getAdopterById(@RequestParam("adopterid") Long adopterId) {
        return adopterService.getAdopterById(adopterId);
    }
}
