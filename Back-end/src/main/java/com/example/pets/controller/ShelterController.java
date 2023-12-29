package com.example.pets.controller;

import com.example.pets.entity.Shelter;
import com.example.pets.service.IShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelter")
public class ShelterController {
    @Autowired
    private IShelterService shelterService;
    @PostMapping("/save")
    public Shelter saveShelter(@RequestBody Shelter shelter) {
        return shelterService.saveShelter(shelter);
    }
    @GetMapping("/getall")
    public List<Shelter> getAllShelters() {
        return shelterService.getAllShelters();
    }
}
