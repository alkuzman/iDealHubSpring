package com.bottle.team.service;

import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.model.payment.Contract;

public interface ContractService extends Service<Contract> {

    Iterable<? extends BaseEntity> init();
}
