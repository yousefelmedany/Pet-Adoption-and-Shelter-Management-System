package com.example.pets.dto;

import com.example.pets.entity.Adopter;
import com.example.pets.entity.Role;
import com.example.pets.entity.Staff;
import com.example.pets.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private LocalDate birthDate;
    private String role;
    private Long shelterId;

    public Adopter toAdopter() {
        Adopter adopter = new Adopter();
        adopter.setName(name);
        adopter.setPhone(phone);
        adopter.setAddress(address);
        return adopter;
    }

    public Staff toStaff() {
        Staff staff = new Staff();
        staff.setName(name);
        staff.setPhone(phone);
        staff.setAddress(address);
        staff.setBirthDate(birthDate);
        return staff;
    }
    public User toUser() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

}
