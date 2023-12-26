package com.example.pets.controller;

import com.example.pets.dto.LoginRequestDto;
import com.example.pets.dto.UserInfoDTO;
import com.example.pets.entity.Role;
import com.example.pets.entity.User;
import com.example.pets.security.AuthService;
import com.example.pets.service.IUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Set;

//@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")

@Service
@Log4j2
public class AuthController {


    @Autowired
    private AuthService authService;

    @Autowired
    private IUserService service;

    @Value("${auth.cookies.jwtCookieName}")
    private String jwtCookie;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        ResponseCookie cookie = authService.login(loginRequest.getLogin(), loginRequest.getPassword());
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(new UserInfoDTO(authService.getUser()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        try {
            ResponseCookie cookie = authService.logoutUser();
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(null);
        } catch (Exception e) {
            return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, ResponseCookie.from(jwtCookie, null).secure(true).path("/").build().toString()).body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User newUser) {
        if (service.existsByEmail(newUser.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
        newUser.setEnabled(true);
        Set<Role> roles1 = newUser.getRoles();
        Role role;
        if (roles1.isEmpty()) {
            role = service.findRoleByName("USER");
            if (role == null) role = service.createRole(new Role("USER"));
        } else {
            role = roles1.iterator().next();
        }
        Role find_role = service.findRoleByName(String.valueOf(role.getName()));
        if (find_role == null) service.createRole(role);
        newUser.setRoles(Set.of(role));
        service.createUser(newUser);
        return ResponseEntity.ok().build();
    }


}

