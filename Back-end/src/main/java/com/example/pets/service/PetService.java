package com.example.pets.service;

import com.example.pets.entity.Pet;
import com.example.pets.entity.Shelter;
import com.example.pets.repository.PetRepository;
import com.example.pets.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.List;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class PetService implements IPetService {
    @Autowired
    PetRepository petRepository;
    @Autowired
    ShelterRepository shelterRepository;

    @Override
    public Pet savePet(Pet pet, Long shelterId) {
        Shelter shelter = shelterRepository.findById(shelterId).orElse(null);
        pet.setShelter(shelter);
        return petRepository.save(pet);
    }

    @Override
    public List<Pet> getPetsByShelterId(Long shelterId) {
        return petRepository.findByShelterShelterId(shelterId);
    }

    @Override
    public Pet editPet(Pet pet) {
        Pet oldPet = petRepository.findById(pet.getPetId()).orElse(null);
        if (oldPet == null) return null;
        oldPet.setPetName(pet.getPetName());
        oldPet.setAge(pet.getAge());
        oldPet.setGender(pet.getGender());
        oldPet.setBreed(pet.getBreed());
        oldPet.setSpecies(pet.getSpecies());
        oldPet.setColor(pet.getColor());
        oldPet.setHealthStatus(pet.getHealthStatus());
        oldPet.setVaccination(pet.getVaccination());
        oldPet.setSpayNeuter(pet.getSpayNeuter());
        oldPet.setTraining(pet.getTraining());
        return petRepository.save(oldPet);
    }
    @Override
    public void removePet(Long petId) {
        petRepository.deleteById(petId);
    }
    @Override
    public   void Backupdbtosql() {
        try {

            /*NOTE: Creating Database Constraints*/
            String dbName = "petshelter";
            String dbUser = "scott";
            String dbPass = "tiger";

            /*NOTE: Used to create a cmd command*/
            String executeCmd = "mysqldump -u" + dbUser + " -p" + dbPass + " --databases " + dbName + " -r " + "D:\\backup.sql";

            /*NOTE: Executing the command here*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                System.out.println("Backup Complete");
            } else {
                System.out.println("Backup Failure");
            }

        } catch ( IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
        }
    }
   @Override
    public void Restoredbfromsql(String s) {
        try {

            /*NOTE: Do not create a single large string, this will cause buffer locking, use string array*/
            String comando = "D:\\mysql.exe  --host=localhost --port=3306 --user=scott --password=tiger < D:\\backup.sql";
            File f = new File("restore.bat");
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(comando.getBytes());
            fos.close();
            Process run = Runtime.getRuntime().exec("cmd /C start restore.bat ");
            int processComplete = run.waitFor();
            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "Successfully restored from SQL : " + s);
            } else {
                JOptionPane.showMessageDialog(null, "Error at restoring");
            }


        } catch (  IOException | InterruptedException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error at Restoredbfromsql" + ex.getMessage());
        }

    }
}
