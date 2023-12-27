package com.example.pets.controller;

import com.example.pets.entity.Shelter;
import com.example.pets.service.IShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shelter")
public class ShelterController {
    @Autowired
    private IShelterService shelterService;
    @PostMapping("/save")
    public Shelter saveShelter(@RequestBody Shelter shelter) {
        return shelterService.saveShelter(shelter);
    }
}
