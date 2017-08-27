package com.bottle.team.web;


import com.bottle.team.model.security.ProtocolSession;
import com.bottle.team.model.sharing.Notice;
import com.bottle.team.service.NoticeService;
import com.bottle.team.service.ProtocolSessionService;
import com.bottle.team.service.ProtocolTransactionNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/protocoltransactions")
public class ProtocolTransactionController {

    private final ProtocolSessionService protocolSessionService;
    private final ProtocolTransactionNoticeService protocolTransactionNoticeService;

    @Autowired
    public ProtocolTransactionController(ProtocolSessionService protocolSessionService,
                                         ProtocolTransactionNoticeService protocolTransactionNoticeService) {
        this.protocolSessionService = protocolSessionService;
        this.protocolTransactionNoticeService = protocolTransactionNoticeService;
    }

    @RequestMapping(value = "/session/{id}", method = RequestMethod.PUT)
    public ProtocolSession updateProtocolSession(@RequestBody ProtocolSession protocolSession) {
        return this.protocolSessionService.save(protocolSession);
    }

    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public String transactionRequestProcessed(@RequestBody Map<String, String> info) {
        String userEmail = info.get("user");
        String message = info.get("message");
        String epoId = info.get("epoId");

        Notice notice = this.protocolTransactionNoticeService.createProtocolTransactionSevenNotice(message, epoId, userEmail);
        notice = this.protocolTransactionNoticeService.add(notice);

        if (notice != null) {
            return "Message sent";
        }
        return "Message not sent";
    }
}
