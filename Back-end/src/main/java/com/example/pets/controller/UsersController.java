package com.example.pets.controller;

import com.example.pets.dto.LoginRequestDto;
import com.example.pets.entity.User;
import com.example.pets.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private IUserService service;

    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER')")
    @GetMapping("/getUserByRole/{role}")
    public List<User> getUserByRole(@PathVariable String role) {
        System.out.println(role);
        return service.getUserByRole(role);
    }
    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER')")
    @DeleteMapping("/deleteAllUsers")
    public void deleteAllUsers() {
        service.deleteAllUsers();
    }
    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER')")
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        List<User> users =service.getAllUsers();
        return users;
    }
    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER')")
    @PostMapping("/editUser/{id}")
    public User editUser(@PathVariable int id,@RequestBody User user) {
        return service.Edit_user(id,user);
    }
    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('USER')")
    @GetMapping("/getUserByEmail")
    public User getUserByEmail(@Param("email") String email) {
        return service.getUserByEmail(email);
    }
    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER')")
    @DeleteMapping("/deleteUser")
    public void deleteUser(@Param("id") int id) {
        service.deleteUser(id);
    }
    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER')")
   @GetMapping("/changerole/{email}/{role}")
    public User changeRole(@PathVariable String email,@PathVariable String role) {
        return service.changeRole(email,role);
    }
    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER')")
    @GetMapping("/changeDepartment/{email}/{department}")
    public User changeDepartment(@PathVariable String email,@PathVariable String department) {
        return service.changeDepartment(email,department);
    }
    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER')")
    @PostMapping("/addimg/{email}")
    public User addImg(@PathVariable String email,@RequestBody String img) {
        return service.add_image_touser(email,img);
    }
    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER')")
    @GetMapping("/getAllUsersByRole/{role}")
    public List<User> getAllUsersByRole(@PathVariable String role) {
        return service.getAllUsersByRole(role);
    }
    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('USER')")
    @GetMapping("/getAllUsersEmail")
    public List<String> getAllUsersEmail() {
        return service.getAllUsersEmail();
    }
    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') or hasAuthority('USER')")
    @PostMapping("/changePassword")
    public User changePassword(@RequestBody LoginRequestDto loginRequest){
        return service.changePassword(loginRequest.getLogin(),loginRequest.getPassword());
    }
}
