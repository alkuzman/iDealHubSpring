package com.bottle.team.service.impl;

import com.bottle.team.model.security.SecurityProfile;
import com.bottle.team.repository.SecurityProfileRepository;
import com.bottle.team.service.SecurityProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Viki on 2/12/2017.
 */
@Service
public class SecurityProfileServiceImpl implements SecurityProfileService {

    final private SecurityProfileRepository securityProfileRepository;

    @Autowired
    public SecurityProfileServiceImpl(SecurityProfileRepository securityProfileRepository) {
        this.securityProfileRepository = securityProfileRepository;
    }

    @Override
    public Iterable<SecurityProfile> findAll() {
        return null;
    }

    @Override
    public SecurityProfile save(SecurityProfile object) {
        return this.securityProfileRepository.save(object);
    }

    @Override
    public SecurityProfile add(SecurityProfile object) {
        return this.save(object);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public SecurityProfile findById(Long id) {
        return null;
    }
}
