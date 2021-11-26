package com.withmeal.infra.email;

import com.withmeal.exception.email.EmailCodeWrongException;
import com.withmeal.exception.email.EmailSendException;
import com.withmeal.infra.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

/**
 * created by Gyunny 2021/11/26
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {

    @Value("${with.email.name}")
    private String name;

    @Value("${with.email.link}")
    private String link;

    @Value("${with.email.validTime}")
    private Long validTime;

    private final JavaMailSender emailSender;
    private final RedisUtil redisUtil;
    private final SpringTemplateEngine templateEngine;

    public void sendEmailMessage(String email) {
        try {
            String code = createCode();
            redisUtil.setDataExpire(code, email, validTime);
            MimeMessage message = createMessage(email, code);
            emailSender.send(message);
        } catch (Exception e) {
            throw new EmailSendException();
        }
    }

    private MimeMessage createMessage(String email, String code) throws Exception {
        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("[WithMeal] 비밀번호를 잊으셨나요? " + code);
        message.setText(setContext(code), "utf-8", "html");
        message.setFrom(new InternetAddress(link, name));
        return message;
    }

    private String setContext(String code) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process("email", context);
    }

    private String createCode() {
        StringBuilder code = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < 7; i++) {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
                case 0:
                    code.append((char) (rnd.nextInt(26) + 97));
                    break;
                case 1:
                    code.append((char) (rnd.nextInt(26) + 65));
                    break;
                case 2:
                    code.append((rnd.nextInt(10)));
                    break;
            }
        }
        return code.toString();
    }

    public void verifyCode(String code) {
        if (redisUtil.getData(code) == null) {
            throw new EmailCodeWrongException();
        }
    }

}