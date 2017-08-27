package com.bottle.team.service.impl;

import com.bottle.team.model.interfaces.BaseEntity;
import com.bottle.team.model.payment.Contract;
import com.bottle.team.neo4j.Neo4jUtils;
import com.bottle.team.repository.ContractRepository;
import com.bottle.team.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    ContractRepository contractRepository;

    @Override
    public Iterable<? extends BaseEntity> init() {
        List<Contract> contractList = new ArrayList<>();
        Contract c = new Contract();
        c.setTitle("200 euros one time payment");
        c.setText("If the idea is accepted and used after purchasing the other side will be payed 200 euros.");
        contractList.add(c);

        c = new Contract();
        c.setTitle("3% from all earnings");
        c.setText("If the idea is accepted and used after purchasing the other side will get 3% from all the earnings.");
        contractList.add(c);

        return contractRepository.saveAll(contractList);
    }

    @Override
    public Iterable<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract save(Contract object) {
        return contractRepository.save(object);
    }

    @Override
    public Contract add(Contract object) {
        return save(object);
    }

    @Override
    public void delete(Long id) {
        contractRepository.deleteById(id);
    }

    @Override
    public Contract findById(Long id) {
        return Neo4jUtils.findById(contractRepository, id);

    }
}
