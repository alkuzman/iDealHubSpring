package com.bottle.team.service.impl;

import com.bottle.team.model.authentication.Organization;
import com.bottle.team.repository.OrganizationRepository;
import com.bottle.team.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by PC on 09/10/2016.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public Iterable<Organization> findAll() {
        return organizationRepository.findAll();
    }

    @Override
    public Organization save(Organization organization) {
        if (organization.getId() == null)
            organization.setCreationDate(new Date());
        organization.setLastModified(new Date());
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

    @Override
    public Organization add(Organization object) {
        return save(object);
    }
}
