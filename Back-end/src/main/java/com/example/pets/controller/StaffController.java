package com.example.pets.controller;

import com.example.pets.dto.APIResponse;
import com.example.pets.entity.Application;
import com.example.pets.entity.Pet;
import com.example.pets.entity.Staff;
import com.example.pets.service.IPetService;
import com.example.pets.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.AccessibleObject;
import java.util.List;

@RestController
@RequestMapping("/Staff")
public class StaffController {
    @Autowired
    private IStaffService staffservice;

    @GetMapping("/getpendingRequests")
    public List<Application> GetPendinRequests() {
        return this.staffservice.GetPendinRequests();
    }

    @PutMapping("/AcceptRequest")
    public boolean AcceptApplication(long AdopterId, long PetId) {
        return this.staffservice.AcceptApplication(AdopterId, PetId);
    }

    @PutMapping("/DeclineRequest")
    public boolean DeclineApplication(long AdopterId, long PetId) {
        return this.staffservice.DeclineApplication(AdopterId, PetId);
    }

    @PutMapping("/updateStaff")
    public Staff UpdateStaffMember(Staff newstaff) {
        return this.staffservice.UpdateStaffMember(newstaff);
    }

    @GetMapping("/GetPets")
    public List<Pet> GetPets(long ShelterId) {
        return this.staffservice.GetPets(ShelterId);
    }

    @PostMapping("/AddPet")
    public Pet AddPet(Pet newpet) {
        return this.staffservice.AddPet(newpet);
    }

    @DeleteMapping("/DeletePet")
    public APIResponse DeletePet(long PetId) {
        this.staffservice.DeletePet(PetId);
        return new APIResponse(1, "User Deleted");
    }

    @PutMapping("/UpdatePet")
    public Pet UpdatePet(Pet newpet) {
        return this.staffservice.UpdatePet(newpet);
    }
}
