package com.bottle.team.service.impl;

import com.bottle.team.model.Organization;
import com.bottle.team.repository.OrganizationRepository;
import com.bottle.team.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by PC on 09/10/2016.
 */
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public Iterable<Organization> findAll() {
        return organizationRepository.findAll();
    }

    @Override
    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Override
    public void delete(Long id) {
        organizationRepository.delete(id);
    }

    @Override
    public Organization findById(Long id) {
        return organizationRepository.findOne(id);
    }
}
