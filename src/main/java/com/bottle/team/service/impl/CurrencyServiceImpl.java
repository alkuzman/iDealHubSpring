package com.bottle.team.service.impl;

import com.bottle.team.model.helpers.Currency;
import com.bottle.team.neo4j.Neo4jUtils;
import com.bottle.team.repository.CurrencyRepository;
import com.bottle.team.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Viki on 2/21/2017.
 */
@Service
public class CurrencyServiceImpl implements CurrencyService {

    final private CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public Iterable<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public Currency save(Currency object) {
        return null;
    }

    @Override
    public Currency add(Currency object) {
        return null;
    }

    @Override
    public void delete(Long id) {
        currencyRepository.deleteById(id);
    }

    @Override
    public Currency findById(Long id) {
        return Neo4jUtils.findById(currencyRepository, id);
    }

    @Override
    public Iterable<Currency> save(Iterable<Currency> currencies) {
        return this.currencyRepository.save(currencies, 20);
    }
}
