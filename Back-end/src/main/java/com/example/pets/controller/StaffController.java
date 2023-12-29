package com.example.pets.controller;

import com.example.pets.entity.Application;
import com.example.pets.entity.Staff;
import com.example.pets.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Application> AcceptApplication(@RequestParam("adopterid") long AdopterId, @RequestParam("petid") long PetId) {
        System.out.println(AdopterId+" "+PetId);
        return this.staffservice.AcceptApplication(AdopterId, PetId);
    }

    @PutMapping("/DeclineRequest")
    public boolean DeclineApplication(@RequestParam("adopterid") long AdopterId,@RequestParam("petid") long PetId) {
        return this.staffservice.DeclineApplication(AdopterId, PetId);
    }

    @PutMapping("/updateStaff")
    public Staff UpdateStaffMember(@RequestBody Staff newstaff) {
        return this.staffservice.UpdateStaffMember(newstaff);
    }

}
