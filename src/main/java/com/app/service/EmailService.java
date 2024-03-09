package com.app.service;

public interface EmailService {
    void send(String emailTo, String subject, String content);
}
