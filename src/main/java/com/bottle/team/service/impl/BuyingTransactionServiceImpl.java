package com.bottle.team.service.impl;

import com.bottle.team.auth.jwt.common.UserContext;
import com.bottle.team.model.authentication.Agent;
import com.bottle.team.model.authentication.User;
import com.bottle.team.model.security.BuyingTransaction;
import com.bottle.team.repository.BuyingTransactionRepository;
import com.bottle.team.service.BuyingTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by Viki on 3/4/2017.
 */
@Service
public class BuyingTransactionServiceImpl implements BuyingTransactionService {

    private final BuyingTransactionRepository buyingTransactionRepository;

    @Autowired
    public BuyingTransactionServiceImpl(BuyingTransactionRepository buyingTransactionRepository) {
        this.buyingTransactionRepository = buyingTransactionRepository;
    }

    @Override
    public Iterable<BuyingTransaction> findAll() {
        return null;
    }

    @Override
    public BuyingTransaction save(BuyingTransaction object) {
        return null;
    }

    @Override
    public BuyingTransaction add(BuyingTransaction object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public BuyingTransaction findById(Long id) {
        BuyingTransaction transaction = this.buyingTransactionRepository.findOne(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserContext userContext = (UserContext) authentication.getPrincipal();
        for (Agent member : transaction.getMembers()) {
            if (member instanceof User) {
                if (userContext.getUsername().equals(((User) member).getEmail())) {
                    return transaction;
                }
            }
        }
        return null;
    }
}
