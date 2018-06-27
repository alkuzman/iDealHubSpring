package com.bottle.team.web;

import com.bottle.team.model.security.SecurityProfile;
import com.bottle.team.service.SecurityProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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


        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> info = new HashMap<>();
        info.put("email", profile.getAgent().getEmail());
        info.put("application", "iDeal");
        info.put("certificate", profile.getCertificatePEM());
        info.put("publicKey", profile.getEncryptionPair().getPublicPem());
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<Map<String,String>>(info, headers);
        template.postForObject("https://ideal.com:8090/protocol/profile", httpEntity, String.class);

        return securityProfileService.save(profile);
    }

    @RequestMapping(method = RequestMethod.GET)
    public SecurityProfile get() {
        return securityProfileService.getAuthenticatedUserSecurityProfile();
    }
}
