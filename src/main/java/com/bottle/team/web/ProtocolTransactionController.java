package com.bottle.team.web;


import com.bottle.team.model.security.ProtocolSession;
import com.bottle.team.service.ProtocolSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/protocoltransactions")
public class ProtocolTransactionController {

    private final ProtocolSessionService protocolSessionService;

    @Autowired
    public ProtocolTransactionController(ProtocolSessionService protocolSessionService) {
        this.protocolSessionService = protocolSessionService;
    }

    @RequestMapping(value = "/session/{id}", method = RequestMethod.PUT)
    public ProtocolSession updateProtocolSession(@RequestBody ProtocolSession protocolSession) {
        return this.protocolSessionService.save(protocolSession);
    }
}
