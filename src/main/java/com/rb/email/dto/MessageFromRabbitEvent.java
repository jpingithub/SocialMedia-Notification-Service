package com.rb.email.dto;

import lombok.Data;

@Data
public class MessageFromRabbitEvent {
    private String toMail;
    private String content;
    private String subject;
}
