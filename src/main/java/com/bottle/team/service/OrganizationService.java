package com.bottle.team.service;

import com.bottle.team.model.Organization;

/**
 * Created by PC on 09/10/2016.
 */
public interface OrganizationService {
    Iterable<Organization> findAll();

    Organization save(Organization organization);

    void delete(Long id);

    Organization findById(Long id);
}
