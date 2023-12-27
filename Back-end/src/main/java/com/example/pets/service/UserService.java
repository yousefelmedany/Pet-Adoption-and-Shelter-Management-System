package com.example.pets.service;

import com.example.pets.dto.RegistrationDto;
import com.example.pets.dto.UserInfoDTO;
import com.example.pets.entity.Adopter;
import com.example.pets.entity.Role;
import com.example.pets.entity.Staff;
import com.example.pets.entity.User;
import com.example.pets.repository.*;
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
    private final UserRepository userRepo;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final UserRoleRepository userRoleRepository;
    @Autowired
    private final AdopterRepository adopterRepository;
    @Autowired
    private final StaffRepository staffRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;


    //-----------------------------------------------UserMethods--------------------------------------------------//
    @Override
    public void createUser(RegistrationDto user) {
        String roles = user.getRole();
        System.out.println(roles);
        if (roles.contains("ADOPTER")) {
            User user1=user.toUser();
            Adopter adopter = adopterRepository.save(user.toAdopter());
            user1.setPassword(passwordEncoder.encode(user1.getPassword()));
            user1.setRoles(Set.of(Objects.requireNonNull(roleRepository.findByName("ADOPTER").orElse(null))));
            user1.setPersonId(adopter.getAdopterId());
            user1.setEnabled(true);
            userRepo.save(user1);
        }
        if (roles.contains("STAFF")) {
            User user1=user.toUser();
            Staff staff = staffRepository.save(user.toStaff());
            user1.setPassword(passwordEncoder.encode(user1.getPassword()));
            user1.setRoles(Set.of(Objects.requireNonNull(roleRepository.findByName("STAFF").orElse(null))));
            user1.setPersonId(staff.getStaffId());
            user1.setEnabled(true);
            userRepo.save(user1);
        }
        if (roles.contains("MANAGER")) {
            User user1=user.toUser();
            Staff staff = staffRepository.save(user.toStaff());
            user1.setPassword(passwordEncoder.encode(user1.getPassword()));
            user1.setRoles(Set.of(Objects.requireNonNull(roleRepository.findByName("MANAGER").orElse(null))));
            user1.setPersonId(staff.getStaffId());
            user1.setEnabled(true);
            userRepo.save(user1);
        }
    }


    @Override
    public boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByEmail(username);
        System.out.println(user);
        return new AppUserDetails(user.orElseThrow(() -> new UsernameNotFoundException("User not found")));
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

    @Override
    public Object getMe(UserInfoDTO userInfoDTO) {
        Role role = roleRepository.findByName(userInfoDTO.getRole().get(0)).orElse(null);
        if (role.getName().equals("ADOPTER")) {
            Adopter adopter = adopterRepository.findById(userInfoDTO.getPersonId()).orElse(null);
            return adopter;
        }
        if (role.getName().equals("STAFF")) {
            Staff staff = staffRepository.findById(userInfoDTO.getPersonId()).orElse(null);
            return staff;
        }
        if (role.getName().equals("MANAGER")) {
            Staff staff = staffRepository.findById(userInfoDTO.getPersonId()).orElse(null);
            return staff;
        }
        return null;
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





