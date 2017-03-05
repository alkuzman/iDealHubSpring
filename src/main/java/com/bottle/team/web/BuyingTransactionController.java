package com.bottle.team.web;

import com.bottle.team.model.security.BuyingTransaction;
import com.bottle.team.service.BuyingTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/buyingtransaction")
public class BuyingTransactionController {

    private final BuyingTransactionService buyingTransactionService;

    @Autowired
    public BuyingTransactionController(BuyingTransactionService buyingTransactionService) {
        this.buyingTransactionService = buyingTransactionService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public BuyingTransaction geyBuyingTransaction(@PathVariable Long id) {
        return this.buyingTransactionService.findById(id);
    }
}
