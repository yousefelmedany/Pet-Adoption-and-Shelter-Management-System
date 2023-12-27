package com.example.pets.dto;


import com.example.pets.security.AppUserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    @NonNull
    private String email;
    private List<String> role;
    private Long personId;
    public UserInfoDTO(AppUserDetails user) {
        this.email = user.getEmail();
        this.role = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        this.personId = user.getPersonId();
    }
}
