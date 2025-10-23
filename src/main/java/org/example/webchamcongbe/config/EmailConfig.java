package org.example.webchamcongbe.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class EmailConfig {

    @Value("${mail.host:smtp.gmail.com}")
    private String host;

    @Value("${mail.port:587}")
    private int port;

    @Value("${mail.username:username}")
    private String username;

    @Value("${mail.password:password}")
    private String password;

    @Value("${mail.smtp.auth:true}")
    private String auth;

    @Value("${mail.smtp.starttls.enable:true}")
    private String starttlsEnable;

    @Value("${mail.smtp.debug:true}")
    private String debug;

    @Value("${mail.from:emailai.system@gmail.com}")
    private String fromEmail;

    }

