package com.example.pets.service;

import com.example.pets.entity.Application;
import com.example.pets.entity.Shelter;
import com.example.pets.entity.Staff;

import java.util.List;

public interface IStaffService {

    List<Application> GetPendinRequests();
    List<Application> AcceptApplication(long AdopterId, long PetId);

    boolean DeclineApplication(long AdopterId, long PetId);

    Staff UpdateStaffMember(Staff newstaff);
    Long getShelterOfStaff(long staffId);

}
