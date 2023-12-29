package com.example.pets.service;

import com.example.pets.entity.Application;
import com.example.pets.entity.Pet;
import com.example.pets.entity.Staff;

import java.util.List;

public interface IStaffService {

    List<Application> GetPendinRequests();
    boolean AcceptApplication(long AdopterId, long PetId);

    boolean DeclineApplication(long AdopterId, long PetId);

    Staff UpdateStaffMember(Staff newstaff);

    List<Pet> GetPets(long ShelterId);

    Pet AddPet(Pet newpet);

    void DeletePet(long PetId);

    Pet UpdatePet(Pet newpet);





}
