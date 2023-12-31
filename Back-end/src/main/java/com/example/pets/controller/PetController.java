package com.example.pets.controller;

import com.example.pets.entity.Pet;
import com.example.pets.service.IPetService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private IPetService petService;
    @PostMapping("/save")
    public Pet savePet(@RequestParam("file")MultipartFile file,@RequestParam("pet") String petJson, @RequestParam ("shelterid") Long shelterId) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Pet pet=objectMapper.readValue(petJson,Pet.class);
        pet.setImage(file.getBytes());
        return petService.savePet(pet,shelterId);
    }
    @GetMapping("/get")
    public List<Pet> getPetsByShelterId(@RequestParam("shelterid") Long shelterId) {
        return petService.getPetsByShelterId(shelterId);
    }
    @PostMapping("/edit")
    public Pet editPet(@RequestBody Pet pet){
        return petService.editPet(pet);
    }
    @DeleteMapping("/delete")
    public void removePet(@RequestParam("petid") Long petId){
        petService.removePet(petId);
    }
    @GetMapping("/backup")
    public void Backupdbtosql() {
        petService.Backupdbtosql();
    }
    @GetMapping("/restore")
    public void Restoredbfromsql() {
        petService.Restoredbfromsql("backup.sql");
    }
}
