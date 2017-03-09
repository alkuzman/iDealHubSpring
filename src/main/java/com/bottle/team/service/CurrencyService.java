package com.bottle.team.service;

import com.bottle.team.model.helpers.Currency;

/**
 * Created by Viki on 2/21/2017.
 */
public interface CurrencyService extends Service<Currency> {
    Iterable<Currency> save(Iterable<Currency> currencies);
}
