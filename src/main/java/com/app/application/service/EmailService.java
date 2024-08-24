package com.app.application.service;

public interface EmailService {
    void send(String emailTo, String subject, String content);
}
