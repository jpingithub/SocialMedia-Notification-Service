package com.rb.email.process;

import com.rb.email.dto.MessageFromRabbitEvent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Value("${spring.mail.sender}") private String fromEmail;

    @Autowired private JavaMailSender javaMailSender;

    @RabbitListener(queues = "${spring.messaging.queue}")
    public void handleFollowEvent(MessageFromRabbitEvent event) {
        log.info("Received message : {}",event.toString());
        sendEmail(event.getToMail(),event.getContent(),event.getSubject());
    }

    public String sendEmail(String to,String content,String subject){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        simpleMailMessage.setFrom(fromEmail);
        javaMailSender.send(simpleMailMessage);
        LOGGER.info("Received data in Service : to - {}, content - {}, subject - {} and sending from {}",to,content,subject,fromEmail);
        return "Mail sent successfully";
    }

}
