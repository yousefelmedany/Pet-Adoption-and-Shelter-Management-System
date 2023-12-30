package com.example.pets.service;


import com.example.pets.entity.*;
import com.example.pets.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@org.springframework.stereotype.Service
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
    public List<Application> AcceptApplication(long AdopterId, long PetId) {

        ApplicationId Id = new ApplicationId(AdopterId, PetId);
        Optional<Application> currapp = applicationRepository.findById(Id);
        currapp.get().setStatus("Approved");
        applicationRepository.save(currapp.get());

        List<Application> remaining = applicationRepository.getremainingpendingrequests(PetId);
        for(int i=0;i<remaining.size();i++){
            remaining.get(i).setStatus("Removed");
            applicationRepository.save(remaining.get(i));
        }
        Optional<Pet> pet = petRepository.findById(PetId);
        pet.get().setAdopter(adopterRepository.getById(AdopterId));
        petRepository.save(pet.get());
        return applicationRepository.findByStatus("Pending");
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
    public Long getShelterOfStaff(long staffId) {
        Staff staff = staffRepository.findById(staffId).orElse(null);
        if(staff==null) return null;
        return staff.getShelter().getShelterId();
    }

}
