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
public class AppUserDetails implements UserDetails {

    private int id;

    private String image;
    private String userName;

    private String password;

    private String email;
    private String phone;
    private String country;
    private String address;
    private String company_name;
    private String department;


    private Set<GrantedAuthority> authorities;

    private boolean isEnabled;

    private boolean isCredentialsNonExpired;

    private boolean isAccountNonLocked;

    private boolean isAccountNonExpired;


    public AppUserDetails(User user) {
        super();
        this.id = user.getId();
        this.company_name = user.getCompany_name();
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.phone = user.getPhone_number();
        this.country = user.getCountry();
        this.address = user.getAddress();
        this.image = user.getImg();
        this.department = user.getDepartment();
        this.isEnabled = user.isEnabled();
        this.isCredentialsNonExpired = user.isCredentialsNonExpired();
        this.isAccountNonExpired = user.isAccountNonExpired();
        this.isAccountNonLocked = user.isAccountNonLocked();

        authorities = new HashSet<>();
        if (!user.getRoles().isEmpty()) {
            user.getRoles().forEach(role -> authorities.add((new SimpleGrantedAuthority(role.getName()))));
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
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
