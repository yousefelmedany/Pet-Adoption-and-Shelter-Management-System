package com.example.pets.service;

import com.example.pets.entity.Role;
import com.example.pets.entity.User;
import com.example.pets.repository.RoleRepository;
import com.example.pets.repository.UserRepository;
import com.example.pets.repository.UserRoleRepository;
import com.example.pets.security.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class UserService implements IUserService, UserDetailsService {


    @Autowired
    UserRepository userRepo;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;


    //-----------------------------------------------UserMethods--------------------------------------------------//
    @Override
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }


    @Override
    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(username);
        return new AppUserDetails(user.get());
    }


    @Override
    public void deleteAllUsers() {
        userRepo.deleteAll();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


    @Override
    public List<User> getUserByRole(String role) {
        Set<Role> roles = Set.of(Objects.requireNonNull(roleRepository.findByName(role).orElse(null)));
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }


    @Override
    public List<String> getAllUsersEmail() {
        List<String> emails = new ArrayList<>();
        List<User> users = userRepo.findAll();
        for (User user : users) {
            emails.add(user.getEmail());
        }
        return emails;
    }

    //---------------------------------------------------RoleMethods--------------------------------------------------//
    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findRoleByName(String role) {
        if (roleRepository.findByName(role).isEmpty())
            return null;
        else
            return roleRepository.findByName(role).get();
    }

    public Optional<Role> get_role_by_id(Long id) {
        return roleRepository.findById(id);
    }

    public Role Edit_role(Long id, Role role) {
        Role old_role = roleRepository.findById(id).orElse(null);
        assert old_role != null;
        old_role.setName(role.getName());
        roleRepository.save(old_role);
        return old_role;
    }


}





