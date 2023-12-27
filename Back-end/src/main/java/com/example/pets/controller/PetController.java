package com.example.pets.controller;

import com.example.pets.entity.Pet;
import com.example.pets.service.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private IPetService petService;
    @PostMapping("/save")
    public Pet savePet(@RequestBody Pet pet, @RequestParam("shelterid") Long shelterId) {
        return petService.savePet(pet, shelterId);
    }
    @GetMapping("/get")
    public List<Pet> getPetsByShelterId(@RequestParam("shelterid") Long shelterId) {
        return petService.getPetsByShelterId(shelterId);
    }
}
