package com.example.pets.controller;

import com.example.pets.dto.LoginRequestDto;
import com.example.pets.dto.UserInfoDTO;
import com.example.pets.entity.Role;
import com.example.pets.entity.User;
import com.example.pets.security.AuthService;
import com.example.pets.Service.IUserService;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.utility.RandomString;
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

    @Value("verified")
    private String url;
    @Value("contact")
    private String con_url;


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
    public ResponseEntity<?> register(@RequestBody User newUser , HttpServletRequest request){
        if (service.existsByUser_name(newUser.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }
        if (service.existsByEmail(newUser.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }
        String randomCode = RandomString.make(64);
        newUser.setVerificationCode(randomCode);
        newUser.setEnabled(false);


        Set<Role> roles1 = newUser.getRoles();

        Role role;
        if (roles1.isEmpty()){
            role =service.find_role_by_name("USER");
            if (role == null) role = service.create_role(new Role("USER"));
        }
        else {
            role = roles1.iterator().next();
        }
        Role find_role = service.find_role_by_name(role.getName());
        if (find_role == null) service.create_role(role);
        newUser.setRoles(Set.of(role));
        try {
        service.sendVerificationEmail(newUser,this.getClientUrl(request)+url, this.getClientUrl(request)+con_url);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        service.create_user(newUser);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/verify")
    public ResponseEntity<?> verifyUser(@Param("code") String code) {
        if (service.verify(code)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Error: Verification code is invalid!");
        }
    }

    @GetMapping("/forgetPassword")
    public ResponseEntity<?> forgetPassword(@Param("email") String email) throws MessagingException, UnsupportedEncodingException {
        User user = service.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.badRequest().body("Error: Email is not valid!");
        }
        String randomCode = RandomString.make(8);
        user.setVerificationCode(randomCode);
        try{
        service.sendForgetPasswordEmail(user);
            }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error: Email is not valid!");
        }
        service.create_user(user);
        return ResponseEntity.ok().build();
    }
@GetMapping("/checkOtp")
public ResponseEntity<?> checkOtp(@Param("otp") String otp,@Param("email") String email) {
    User user = service.getUserByEmail(email);
    if (user == null) {
        return ResponseEntity.badRequest().body("Error: Email is not valid!");
    }
    else if (!user.getVerificationCode().equals(otp)) {
        return ResponseEntity.badRequest().body("Error: OTP is not valid!");
    }
    return ResponseEntity.ok().build();
}
    @PostMapping("/resetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody LoginRequestDto loginRequest) {
        User user = service.getUserByEmail(loginRequest.getLogin());
        if (user == null) {
            return ResponseEntity.badRequest().body("Error: Email is not valid!");
        }
        user.setPassword(loginRequest.getPassword());
        service.create_user(user);
        return ResponseEntity.ok().build();
    }

    public String getClientUrl(HttpServletRequest request) {
        String client = request.getHeader("Referer");
        if(client == null) {
            client = request.getHeader("Origin");

        }
        else if(client.contains("github")){
            client=client+"Galaxy-Front/";
        }
        return client;
    }

}

