package com.email.service;

import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface EmailService {

    //send email to one person
    void sendEmail(String to, String subject, String message);

    //send email to many person
    void sendEmail(String[] to, String subject, String message);

    //send email with html
    void sendEmailWithHtml(String to, String subject, String htmlContent) throws MessagingException;

    //Email with file
    void sendEmailWithFile(String to, String subject, String message, File file) throws MessagingException;

    void sendEmailWithFile(String to, String subject, String message, MultipartFile file) throws MessagingException;


}
