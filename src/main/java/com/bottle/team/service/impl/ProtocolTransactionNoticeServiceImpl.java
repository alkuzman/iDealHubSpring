package com.bottle.team.service.impl;

import com.bottle.team.model.authentication.Agent;
import com.bottle.team.model.security.ProtocolSession;
import com.bottle.team.model.security.notices.ProtocolTransactionStepFiveNotice;
import com.bottle.team.model.security.notices.ProtocolTransactionStepNotice;
import com.bottle.team.model.security.notices.ProtocolTransactionStepSevenNotice;
import com.bottle.team.repository.ProtocolTransactionNoticeRepository;
import com.bottle.team.repository.UserRepository;
import com.bottle.team.service.ProtocolSessionService;
import com.bottle.team.service.ProtocolTransactionNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
public class ProtocolTransactionNoticeServiceImpl extends NoticeServiceImpl
        implements ProtocolTransactionNoticeService {

    @Autowired
    private ProtocolSessionService protocolSessionService;
    @Autowired
    private ProtocolTransactionNoticeRepository protocolTransactionNoticeRepository;
    @Autowired
    private UserRepository userRepository;

    private ProtocolTransactionStepFiveNotice findLastNoticeForProtocolSessionId(Long id) {
        ProtocolTransactionStepFiveNotice lastStep = protocolTransactionNoticeRepository
                .findProtocolTransactionStepFiveNoticeByProtocolSessionId(id);
        return lastStep;
    }

    @Override
    public ProtocolTransactionStepSevenNotice createProtocolTransactionSevenNotice(String message,
                                                                                   String epoId,
                                                                                   String user) {
        ProtocolSession protocolSession = protocolSessionService.findByEpoId(epoId);

        ProtocolTransactionStepSevenNotice notice = new ProtocolTransactionStepSevenNotice();

        notice.setMessage(message);

        notice.setProtocolSession(protocolSession);

        notice.setPreviousStepNotice(this.findLastNoticeForProtocolSessionId(protocolSession.getId()));

        Agent originator = new Agent();
        originator.setName("iDeal");
        notice.setOriginator(originator);

        Agent recipient = this.userRepository.findByEmail(user);
        notice.setRecipient(recipient);

        return notice;
    }
}
