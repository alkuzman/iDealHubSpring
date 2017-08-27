package com.bottle.team.service.impl;

import com.bottle.team.model.security.ProtocolSession;
import com.bottle.team.repository.ProtocolSessionRepository;
import com.bottle.team.service.ProtocolSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProtocolSessionServiceImpl implements ProtocolSessionService {

    private final ProtocolSessionRepository protocolSessionRepository;

    @Autowired
    public ProtocolSessionServiceImpl(ProtocolSessionRepository protocolSessionRepository) {
        this.protocolSessionRepository = protocolSessionRepository;
    }

    @Override
    public Iterable<ProtocolSession> findAll() {
        return null;
    }

    @Override
    public ProtocolSession save(ProtocolSession object) {
        return this.protocolSessionRepository.save(object);
    }

    @Override
    public ProtocolSession add(ProtocolSession object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ProtocolSession findById(Long id) {
        return null;
    }

    @Override
    public ProtocolSession findByEpoId(String epoId) {
        return this.protocolSessionRepository.findByEpoId(epoId);
    }
}
