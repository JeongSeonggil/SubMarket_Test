package com.submarket.userservice.service.impl;

import com.submarket.userservice.jpa.SubRepository;
import com.submarket.userservice.jpa.entity.SubEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Slf4j
@Service(value = "MailService")
public class MailService {
    private Environment env;
    private SubRepository subRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(Environment env, SubRepository subRepository) {
        this.subRepository = subRepository;
        this.env = env;
    }

    @Async
    public void sendMail(String mailAddress, String title, String mailMessage) {
        log.info(this.getClass().getName() + ".SendMail Start!");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(env.getProperty("spring.mail.username"));
        message.setTo(mailAddress);
        message.setSubject(title);
        message.setText(mailMessage);

        javaMailSender.send(message);

        log.info(this.getClass().getName() + ".SendMail End!");
    }

}