package com.rb.email.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/emails")
public class EmailController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

    @Autowired private EmailService emailService;

    @GetMapping("restart")
    public ResponseEntity<String> restartTheServer(){
        return ResponseEntity.ok("Server restarted .......... ");
    }

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestParam("to")String toEmail, @RequestParam("content")String content, @RequestParam("subject")String subject){
        LOGGER.info("Received data in controller : to - {}, content - {}, subject - {}",toEmail,content,subject);
        return new ResponseEntity<>(emailService.sendEmail(toEmail,content,subject), HttpStatus.OK);
    }

}
