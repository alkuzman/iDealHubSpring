package com.bottle.team.service.impl;

import com.bottle.team.model.authentication.User;
import com.bottle.team.service.MailSender;
import com.bottle.team.service.RegistrationMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import java.util.Locale;

/**
 * Created by AKuzmanoski on 13/11/2016.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 13/11/2016
 */
@Service
public class RegistrationMailServiceImpl implements RegistrationMailService {
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    @Qualifier("asyncMailSender")
    MailSender asyncMailSender;

    @Autowired
    @Qualifier("mailSenderImpl")
    MailSender syncMailSender;

    @Override
    public void sendActivationMail(User user, boolean isAsync) {

        Locale locale = Locale.getDefault();
        Context context = new Context(locale);
        context.setVariable("user", user);
        context.setVariable("baseUrl", "http://localhost:5555/auth");
        String content = templateEngine.process("mail/activationEmail", context);

        if (isAsync) {
            asyncMailSender.sendEmail(user, "registration completed", content, true);
        } else {
            syncMailSender.sendEmail(user, "registration completed", content, true);
        }
    }
}
