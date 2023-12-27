package com.example.pets.appConfig;

import com.example.pets.entity.Role;
import com.example.pets.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements ApplicationRunner {

    @Autowired
    private RoleRepository yourRepository;


    private void insertInitialData() {
        yourRepository.save(new Role("MANAGER"));
        yourRepository.save(new Role("STAFF"));
        yourRepository.save(new Role("ADOPTER"));

    }

    @Override
    public void run(ApplicationArguments args) {
        if (yourRepository.count() == 0) {
            insertInitialData();
        }
    }
}