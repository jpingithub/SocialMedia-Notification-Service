package com.rb.email.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/emails")
public class EmailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

    @Autowired private EmailService emailService;

    @PostMapping("/to")
    public String sendEmail(@RequestParam("to")String toEmail,@RequestParam("content")String content,@RequestParam("subject")String subject){
        LOGGER.info("Received data in controller : to - {}, content - {}, subject - {}",toEmail,content,subject);
        return emailService.sendEmail(toEmail,content,subject);
    }

}
