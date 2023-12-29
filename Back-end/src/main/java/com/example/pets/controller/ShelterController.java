package com.example.pets.controller;

import com.example.pets.dto.APIResponse;
import com.example.pets.dto.ShelterDto;
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
    public Shelter saveShelter(@RequestBody Shelter shelter,@RequestParam("id") Long id) {
        return shelterService.saveShelter(shelter,id);
    }
    @DeleteMapping("/delete")
    public void removeShelter(@RequestParam("shelterid") Long shelterId){
        shelterService.removeShelter(shelterId);
    }
    @PutMapping("/edit")
    public Shelter editShelter(@RequestBody Shelter shelter){
        return shelterService.editShelter(shelter);
    }
    @GetMapping("/getall")
    public List<Shelter> getAllShelters() {
        return shelterService.getAllShelters();
    }
    @GetMapping("/getShelterNames")
    public List<ShelterDto> getShelterNames() {
        return shelterService.getShelterNames();
    }
}
