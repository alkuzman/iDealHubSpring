package com.bottle.team.web;

import com.bottle.team.model.enumaration.CertificateType;
import com.bottle.team.model.security.SecurityProfile;
import com.bottle.team.service.CertificateService;
import com.bottle.team.service.SecurityProfileService;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.cert.X509Certificate;

/**
 * Created by Viki on 2/3/2017.
 */
@RestController
@RequestMapping(value = "/certificates")
public class CertificateController {

    @Autowired
    public CertificateService certificateService;

    @Autowired
    public SecurityProfileService securityProfileService;

    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public String sign(@RequestBody String requestPEMFormat) {
        PKCS10CertificationRequest pkcs10request = certificateService.
                convertCertificateSigningRequestFromPEM(requestPEMFormat);
        X509Certificate certificate = certificateService.createCertificateForSigningRequest(pkcs10request);
        String pemCertificate = certificateService.convertCertificateToPEM(certificate);
        return pemCertificate;
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.GET)
    public String get(@RequestParam String email, @PathVariable CertificateType type) {
        SecurityProfile securityProfile = securityProfileService.findByUserEmailAndCertificateType(email, type);
        return securityProfile.getCertificatePEM();
    }

}
