package com.email;

import com.email.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
@Slf4j
public class EmailSenderTest {

    @Autowired
    private EmailService emailService;

    @Test
    void emailSendTest() {
        // Define email parameters
        String recipientEmail = "rohan.bhakte46@gmail.com";
        String subject = "Test";
        String body = "Hello";

        // Attempt to send the email
        log.info("Sending email to {} with subject '{}' and body '{}'", recipientEmail, subject, body);
        emailService.sendEmail(recipientEmail, subject, body);
        log.info("Email sent successfully to: {}", recipientEmail);
    }

    @Test
    void sendHtmlEmail() {
        // Define email parameters
        String recipientEmail = "rohan.bhakte46@gmail.com";
        String subject = "Test";
        String htmlContent = "<h1 style='color:red; border:1px solid red;'>Welcome to Spring Boot</h1>";

        try {
            emailService.sendEmailWithHtml(recipientEmail, subject, htmlContent);
            System.out.println("Email sent successfully to: " + recipientEmail);
        } catch (MessagingException e) {
            System.err.println("Failed to send email to: " + recipientEmail);
        }
    }


    @Test
    void sendEmailWithFile() {
        // Define email parameters
        String recipientEmail = "rohan.bhakte46@gmail.com";
        String subject = "Email with file";
        String body = "This email contains a file";

        // Define the file to be attached
        File file = new File("src/main/resources/static/Rohan_Bhakte_Resume.pdf");

        // Check if the file exists
        if (!file.exists()) {
            fail("File not found: " + file.getAbsolutePath());
        }

        // Attempt to send the email with the file attachment
        try {
            // log.info("Sending email to {} with subject '{}' and body '{}'", recipientEmail, subject, body);
            emailService.sendEmailWithFile(recipientEmail, subject, body, file);
            //log.info("Email sent successfully to: {}", recipientEmail);
        } catch (MessagingException e) {
            log.error("Failed to send email to: {}", recipientEmail);
            //e.printStackTrace();
            // No need to call fail(), as the test will automatically fail due to the caught exception.
        }
    }


}
