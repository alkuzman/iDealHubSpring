package com.bottle.team.web;

import com.bottle.team.model.helpers.Currency;
import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viki on 2/21/2017.
 */
@RestController
@RequestMapping(value = "/currencies")
public class CurrencyController {

    final private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<? extends BaseEntity> getCurrencies() {
        return currencyService.findAll();
    }

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public Iterable<? extends BaseEntity> init() {
        List<Currency> currencies = new ArrayList<>();

        Currency currency = new Currency();
        currency.setValue("MKD");
        currencies.add(currency);
        currency = new Currency();
        currency.setValue("USD");
        currencies.add(currency);
        currency = new Currency();
        currency.setValue("EUR");
        currencies.add(currency);

        return this.currencyService.save(currencies);
    }
}
