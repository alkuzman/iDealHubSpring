package com.bottle.team.web;

import com.bottle.team.model.security.SecurityProfile;
import com.bottle.team.service.CertificateService;
import com.bottle.team.service.SecurityProfileService;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping(method = RequestMethod.GET)
    public String get(@RequestParam String email) {
        SecurityProfile securityProfile = securityProfileService
                .findByUserEmail(email);
        return securityProfile.getCertificatePEM();
    }

    @RequestMapping(value = "/issuer")
    public String getIssuerCertificate() {
        return this.certificateService.getCACertificate();
    }

    @RequestMapping(value = "/publickey", method = RequestMethod.GET)
    public String getPublicKey(@RequestParam String email) {
        SecurityProfile securityProfile = securityProfileService
                .findByUserEmail(email);
        return securityProfile.getEncryptionPair().getPublicPem();
    }

}
