package com.bottle.team.web;

import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/contracts")
public class ContractController {

    @Autowired
    ContractService contractService;

    @RequestMapping(value = "/init")
    public Iterable<? extends BaseEntity> init() {
        return contractService.init();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<? extends BaseEntity> getContracts() {
        return contractService.findAll();
    }
}
