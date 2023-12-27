package com.example.pets.controller;

import com.example.pets.dto.APIResponse;
import com.example.pets.dto.LoginRequestDto;
import com.example.pets.dto.RegistrationDto;
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
    public ResponseEntity<?> register(@RequestBody RegistrationDto newUser) {
        try {
            service.createUser(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new APIResponse<String>(0, e.getMessage()));
        }
        return ResponseEntity.ok(
                new APIResponse<String>(1, "User created successfully")
        );
    }



    @GetMapping("/getMe")
    public ResponseEntity<?> getMe(@RequestBody UserInfoDTO userInfoDTO) {
        return ResponseEntity.ok().body(service.getMe(userInfoDTO));
    }
}

