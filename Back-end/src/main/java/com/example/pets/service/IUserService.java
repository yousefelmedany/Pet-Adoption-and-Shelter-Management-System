package com.example.pets.service;
import com.example.pets.dto.RegistrationDto;
import com.example.pets.dto.UserInfoDTO;
import com.example.pets.entity.*;

import java.util.List;

public interface IUserService {
    public void createUser(RegistrationDto user);
    boolean existsByEmail(String email);
    void deleteAllUsers();
    List<User> getAllUsers();
    public Role createRole(Role role);
    public Role findRoleByName(String role);
    List<User> getUserByRole(String role);
    public User getUserByEmail(String email);
    List<String> getAllUsersEmail();
    Object getMe(UserInfoDTO userInfoDTO);
}
