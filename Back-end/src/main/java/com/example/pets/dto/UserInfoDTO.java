package com.example.pets.dto;


import com.example.pets.security.AppUserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@AllArgsConstructor
public class UserInfoDTO {
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String country;
    @NonNull
    private String address;
    @NonNull
    private String phone_number;
    @NonNull
    private String company_name;

    private String img;
    private List<String> role;
    private String department;
    public UserInfoDTO(AppUserDetails user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.country = user.getCountry();
        this.address = user.getAddress();
        this.phone_number = user.getPhone();
        this.company_name = user.getCompany_name();
        this.img = user.getImage();
        this.role = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        this.department = user.getDepartment();
    }
}
