package com.bottle.team.web;

import com.bottle.team.model.enumaration.CertificateType;
import com.bottle.team.model.security.SecurityProfile;
import com.bottle.team.service.SecurityProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Viki on 2/18/2017.
 */
@RestController
@RequestMapping(value = "/securityprofiles")
public class SecurityProfileController {

    @Autowired
    public SecurityProfileService securityProfileService;

    @RequestMapping(method = RequestMethod.POST)
    public SecurityProfile save(@RequestBody SecurityProfile profile) {
        return securityProfileService.save(profile);
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    public SecurityProfile get(@PathVariable CertificateType type) {
        return securityProfileService.getAuthenticatedUserSecurityProfile(type);
    }
}
