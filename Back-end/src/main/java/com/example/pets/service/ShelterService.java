package com.example.pets.service;

import com.example.pets.dto.APIResponse;
import com.example.pets.dto.ShelterDto;
import com.example.pets.entity.Shelter;
import com.example.pets.entity.Staff;
import com.example.pets.repository.ShelterRepository;
import com.example.pets.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class ShelterService implements IShelterService{
    @Autowired
    ShelterRepository shelterRepository;
    @Autowired
    StaffRepository staffRepository;
    @Override
    public Shelter saveShelter(Shelter shelter, Long id) {
        Staff staff=staffRepository.findById(id).orElse(null);
        shelter.setShelterAdmin(staff);
        assert staff != null;
        staff.setShelter(shelter);
        staffRepository.save(staff);
        return shelter;
    }
    @Override
    public void removeShelter(Long shelterId) {
        Shelter shelter=shelterRepository.findById(shelterId).orElse(null);
        assert shelter != null;
        Staff staff=shelter.getShelterAdmin();
        staff.setShelter(null);
        staffRepository.save(staff);
        shelterRepository.deleteById(shelterId);
    }
    @Override
    public Shelter editShelter(Shelter shelter) {
        Shelter shelter1=shelterRepository.findById(shelter.getShelterId()).orElse(null);
        assert shelter1 != null;
        shelter1.setShelterName(shelter.getShelterName());
        shelter1.setShelterAddress(shelter.getShelterAddress());
        shelter1.setShelterPhone(shelter.getShelterPhone());
        return shelterRepository.save(shelter1);
    }
    @Override
    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    @Override
    public List<ShelterDto> getShelterNames() {
        return shelterRepository.findAllShelters();
    }
}
