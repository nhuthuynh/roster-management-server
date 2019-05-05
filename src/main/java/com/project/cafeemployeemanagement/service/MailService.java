package com.project.cafeemployeemanagement.service;

import com.project.cafeemployeemanagement.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Properties;


@Service
public class MailService {

    @Value("${app.mail.supportEmail}")
    private String supportEmail;

    @Value("${app.mail.host}")
    private String mailHost;

    @Value("${app.mail.port}")
    private int mailPort;

    @Value("${app.mail.username}")
    private String mailAddress;

    @Value("${app.mail.password}")
    private String mailPassword;

    @Value("${app.mail.properties.mail.smtp.auth}")
    private boolean mailAuth;

    @Value("${app.mail.properties.mail.smtp.starttls.enable}")
    private boolean mailStartTLS;

    @Value("${app.mail.protocol}")
    private String mailProtocol;

    @Value("${app.mail.debug}")
    private boolean mailDebug;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);

        mailSender.setUsername(mailAddress);
        mailSender.setPassword(mailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", mailProtocol);
        props.put("mail.smtp.auth", mailAuth);
        props.put("mail.smtp.starttls.enable", mailStartTLS);
        props.put("mail.debug", mailDebug);

        return mailSender;
    }

    private SimpleMailMessage constructEmail(String subject, String body, Employee employee) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(employee.getEmail());
        email.setFrom(supportEmail);
        return email;
    }

    public SimpleMailMessage constructResetPasswordEmail(String contextPath, String token, Employee employee) {
        final String url = contextPath + "/resetPassword?employeeId=" + employee.getId() + "&token=" + token;
        final String message = "Hi," + employee.getFirstName() + "\r\n" + "Forgot your password? Just reset your password by clicking the following link.";
        return constructEmail("Reset Password", message + " \r\n" + url, employee);
    }

}
