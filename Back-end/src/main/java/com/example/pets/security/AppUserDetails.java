package com.example.pets.security;

import com.example.pets.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Setter
@Getter
public class AppUserDetails implements UserDetails{

    private String password;
    private String email;
    private Set<GrantedAuthority> authorities;

    private boolean isEnabled;


    public AppUserDetails(User user) {
        super();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.isEnabled = user.isEnabled();

        authorities = new HashSet<>();
        if (!user.getRoles().isEmpty()) {
            user.getRoles().forEach(role -> authorities.add((new SimpleGrantedAuthority(role.toString()))));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
