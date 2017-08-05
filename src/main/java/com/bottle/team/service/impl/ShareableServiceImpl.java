package com.bottle.team.service.impl;

import com.bottle.team.model.sharing.Shareable;
import com.bottle.team.neo4j.Neo4jUtils;
import com.bottle.team.repository.ShareableRepository;
import com.bottle.team.service.ShareableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by AKuzmanoski on 04/01/2017.
 *
 * @author AKuzmanoski
 * @version 1.0
 * @since 04/01/2017
 */
@Service
public class ShareableServiceImpl implements ShareableService {
    @Autowired
    ShareableRepository shareableRepository;

    @Override
    public Iterable<Shareable> findAll() {
        return shareableRepository.findAll();
    }

    @Override
    public Shareable save(Shareable object) {
        return shareableRepository.save(object);
    }

    @Override
    public Shareable add(Shareable object) {
        return this.save(object);
    }

    @Override
    public void delete(Long id) {
        shareableRepository.deleteById(id);
    }

    @Override
    public Shareable findById(Long id) {
        return Neo4jUtils.findById(shareableRepository, id);
    }
}
