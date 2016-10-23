package com.bottle.team.web;

import com.bottle.team.model.authentication.Organization;
import com.bottle.team.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by PC on 10/10/2016.
 */
@RestController()
@RequestMapping(value = "/organizations")
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Organization> findAll() {
        return organizationService.findAll();
    }

    @RequestMapping(value = "/{id}" , method = RequestMethod.GET)
    public Organization findById(@PathVariable Long id) {
        return organizationService.findById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Organization save(@RequestBody Organization organization) {
        return organizationService.save(organization);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        organizationService.delete(id);
    }
}
