package com.example.pets.controller;

import com.example.pets.entity.Image;
import com.example.pets.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private IUserService service;
    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') ")
    @PostMapping("/addimg")
    public Image addImg(@RequestBody Image image) {
        return service.add_image(image);
    }
    @PreAuthorize("hasAuthority('admin') or hasAuthority('EMPLOYEE') or hasAuthority('MANAGER') ")
    @DeleteMapping("/deleteimg/{id}")
    public void deleteImg(@PathVariable int id) {
        service.delete_image(id);
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/getimgbyname/{name}")
    public Image getImgbyname(@PathVariable String name) {
        return service.get_image_by_name(name);
    }
}
