package com.bottle.team.web;

import com.bottle.team.model.security.SecurityProfile;
import com.bottle.team.service.SecurityProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Viki on 2/18/2017.
 */
public class SecurityProfileController {

    @Autowired
    public SecurityProfileService securityProfileService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public SecurityProfile save(@RequestBody SecurityProfile profile) {
        return securityProfileService.save(profile);
    }

}
