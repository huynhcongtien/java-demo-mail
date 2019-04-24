package com.lampartvn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application implements ApplicationRunner {

    private static Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private EmailService emailService;

    @Value(value = "${email.test.from}")
    private String mailFrom;

    @Value(value = "${email.test.to}")
    private String mailTo;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        log.info("Sending Email with Thymeleaf HTML Template Example");

        Mail mail = new Mail();
        mail.setFrom(mailFrom);
        mail.setTo(mailTo);
        mail.setSubject("Sending Email with Thymeleaf HTML Template Example");

        Map model = new HashMap();
        model.put("name", "https://test.com");
        model.put("location", "Betonamu");
        model.put("signature", "https://test.com");
        model.put("logo", new ClassPathResource("static/img/logo.png"));
        mail.setModel(model);

        emailService.sendSimpleMessage(mail);
    }

}
