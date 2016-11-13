package com.bottle.team.service;

import com.bottle.team.model.authentication.User;

/**
 * Created by ristes on 4/13/16.
 */
public interface MailSender {
  void sendEmail(User user, String subject, String content, boolean isHtml);
}
