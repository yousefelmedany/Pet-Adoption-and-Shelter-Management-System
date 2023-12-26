package com.example.pets.controller;

import com.example.pets.dto.LoginRequestDto;
import com.example.pets.entity.User;
import com.example.pets.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private IUserService service;

    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER')")
    @GetMapping("/getUserByRole/{role}")
    public List<User> getUserByRole(@PathVariable String role) {
        System.out.println(role);
        return service.getUserByRole(role);
    }
    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER')")
    @DeleteMapping("/deleteAllUsers")
    public void deleteAllUsers() {
        service.deleteAllUsers();
    }
    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER')")
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        List<User> users =service.getAllUsers();
        return users;
    }
    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('USER')")
    @GetMapping("/getUserByEmail")
    public User getUserByEmail(@Param("email") String email) {
        return service.getUserByEmail(email);
    }

    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('USER')")
    @GetMapping("/getAllUsersEmail")
    public List<String> getAllUsersEmail() {
        return service.getAllUsersEmail();
    }
}
