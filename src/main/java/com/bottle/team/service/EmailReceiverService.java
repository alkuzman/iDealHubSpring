package com.bottle.team.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailReceiverService {

    public void receive(MimeMessage mimeMessage) throws MessagingException {
        System.out.println("recevied mail from: " + mimeMessage.getFrom()[0].toString());
    }
}
