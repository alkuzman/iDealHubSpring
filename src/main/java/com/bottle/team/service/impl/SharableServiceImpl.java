package com.bottle.team.service.impl;

import com.bottle.team.model.sharing.Shareable;
import com.bottle.team.repository.SharableRepository;
import com.bottle.team.service.SharableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AKuzmanoski on 04/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 04/01/2017
 */
@Service
public class SharableServiceImpl implements SharableService {
    @Autowired
    SharableRepository sharableRepository;

    @Override
    public Iterable<Shareable> findAll() {
        return sharableRepository.findAll();
    }

    @Override
    public Shareable save(Shareable object) {
        return sharableRepository.save(object);
    }

    @Override
    public Shareable add(Shareable object) {
        return this.save(object);
    }

    @Override
    public void delete(Long id) {
        sharableRepository.delete(id);
    }

    @Override
    public Shareable findById(Long id) {
        return sharableRepository.findOne(id);
    }
}
