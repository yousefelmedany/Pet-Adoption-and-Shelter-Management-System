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
    private List<String> role;
    public UserInfoDTO(AppUserDetails user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.role = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

    }
}
