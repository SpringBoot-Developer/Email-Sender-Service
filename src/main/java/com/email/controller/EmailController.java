package com.email.controller;

import com.email.request.EmailRequest;
import com.email.response.EmailResponse;
import com.email.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class EmailController {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    EmailService emailService;


    @PostMapping("/sendEmail")
    public ResponseEntity<EmailResponse> sendEmail(@RequestBody EmailRequest request) {
        emailService.sendEmail(request.getTo(), request.getSubject(), request.getMessage());
        EmailResponse response = EmailResponse.builder().message("Email sent successfully..").status(HttpStatus.OK).build();
        return ResponseEntity.ok(response);
    }


    @PostMapping(value = "/sendEmailWithFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<EmailResponse> sendEmailWithFile(@RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("message") String message, @RequestParam("file") MultipartFile file) throws MessagingException {
        emailService.sendEmailWithFile(to, subject, message, file);
        EmailResponse response = EmailResponse.builder().message("Email sent successfully..").status(HttpStatus.OK).build();
        log.info("Email sent successfully to: {}", to);
        return ResponseEntity.ok(response);
    }


}

