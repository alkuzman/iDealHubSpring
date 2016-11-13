package com.bottle.team.service.impl;

import com.bottle.team.model.authentication.User;
import com.bottle.team.service.MailSender;
import org.apache.commons.codec.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

/**
 * Created by ristes on 4/13/16.
 */
@Service
public class MailSenderImpl implements MailSender {

    @Autowired
    private JavaMailSender mailSender;


    @Override
    public void sendEmail(User user, String subject, String content, boolean isHtml) {
        final String to = user.getEmail();
        final String from = "contact@ideal-hub.com";

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper message =
                    new MimeMessageHelper(
                            mimeMessage, false, CharEncoding.UTF_8);
            message.setTo(to);
            message.setFrom(from);
            message.setSubject(subject);
            message.setText(content, isHtml);
            mailSender.send(mimeMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
