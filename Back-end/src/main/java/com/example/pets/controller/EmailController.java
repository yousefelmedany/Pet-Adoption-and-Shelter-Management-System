package com.example.pets.controller;
import com.example.pets.mails.EmailDetails;
import com.example.pets.mails.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired private EmailService emailService;
    @PostMapping("/sendMail")
    public ResponseEntity<?>
    sendMail(@RequestBody EmailDetails details)
    {
        return ResponseEntity.ok(emailService.sendSimpleMail(details));
    }
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestBody EmailDetails details) {
        return emailService.sendMailWithAttachment(details);
    }
}
