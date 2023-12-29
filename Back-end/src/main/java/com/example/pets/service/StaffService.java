package com.example.pets.service;


import com.example.pets.entity.*;
import com.example.pets.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class StaffService implements IStaffService{
    @Autowired
    PetRepository petRepository;
    @Autowired
    ShelterRepository shelterRepository;
    @Autowired
    ApplicationRepository applicationRepository;
    @Autowired
    AdopterRepository adopterRepository;
    @Autowired
    StaffRepository staffRepository;

    @Override
    public List<Application> GetPendinRequests() {
        return applicationRepository.findByStatus("Pending");
    }
    @Override
    public boolean AcceptApplication(long AdopterId, long PetId) {

        ApplicationId Id = new ApplicationId(AdopterId, PetId);
        Optional<Application> currapp = applicationRepository.findById(Id);
        currapp.get().setStatus("Approved");
        applicationRepository.save(currapp.get());

        Optional<Pet> pet = petRepository.findById(PetId);
        pet.get().setAdopter(adopterRepository.getById(AdopterId));

        return petRepository.save(pet.get()) != null;
    }
    @Override
    public boolean DeclineApplication(long AdopterId, long PetId) {

        ApplicationId Id = new ApplicationId(AdopterId, PetId);
        Optional<Application> currapp = applicationRepository.findById(Id);
        currapp.get().setStatus("Rejected");
        return applicationRepository.save(currapp.get())!=null;
    }

    @Override
    public Staff UpdateStaffMember(Staff newstaff) {
        Staff staff = staffRepository.getById(newstaff.getStaffId());
        staff.setAddress(newstaff.getAddress());
        staff.setBirthDate(newstaff.getBirthDate());
        staff.setPhone(newstaff.getPhone());
        return staffRepository.save(newstaff);
    }

    @Override
    public List<Pet> GetPets(long ShelterId) {
        return shelterRepository.findById(ShelterId).get().getPets();
    }

    @Override
    public Pet AddPet(Pet newpet) {
        return petRepository.save(newpet);
    }

    @Override
    public void DeletePet(long PetId) {
        petRepository.deleteById(PetId);
    }

    @Override
    public Pet UpdatePet(Pet newpet) {
        Pet pet = petRepository.getById(newpet.getPetId());
        pet.setImage(newpet.getImage());
        pet.setAge(newpet.getAge());
        pet.setPetName(newpet.getPetName());
        pet.setBreed(newpet.getBreed());
        pet.setBehavior(newpet.getBehavior());
        return petRepository.save(pet);
    }
}
