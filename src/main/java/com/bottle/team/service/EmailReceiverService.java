package com.bottle.team.service;

import com.bottle.team.model.authentication.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class EmailReceiverService {

  public void receive(MimeMessage mimeMessage) throws MessagingException {
    System.out.println("recevied mail from: " + mimeMessage.getFrom()[0].toString());
  }
}
