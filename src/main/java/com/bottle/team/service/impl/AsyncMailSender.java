package com.bottle.team.service.impl;

import com.bottle.team.model.authentication.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by ristes on 4/13/16.
 */
@Service
public class AsyncMailSender extends MailSenderImpl {

    @Async
    @Override
    public void sendEmail(User user, String subject, String content, boolean isHtml) {
        super.sendEmail(user, subject, content, isHtml);
    }
}
