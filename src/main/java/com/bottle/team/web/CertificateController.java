package com.bottle.team.web;

import com.bottle.team.model.security.SecurityProfile;
import com.bottle.team.service.CertificateService;
import com.bottle.team.service.SecurityProfileService;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Random;

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

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public SecurityProfile save(@RequestBody SecurityProfile profile) {
        return securityProfileService.save(profile);
    }

    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public String sign(@RequestBody String requestPEMFormat) {
        PKCS10CertificationRequest pkcs10request = certificateService.
                convertCertificateSigningRequestFromPEM(requestPEMFormat);
        X509Certificate certificate = certificateService.createCertificateForSigningRequest(pkcs10request);
        String pemCertificate = certificateService.convertCertificateToPEM(certificate);
        return pemCertificate;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String get() {
        return "GET";
    }

    @RequestMapping(value = "/signrequest", method = RequestMethod.GET)
    public String signRequest() {
        PKCS10CertificationRequest request = certificateService.readCertificateSigningRequest("req.pem");
        X509Certificate certificate = certificateService.createCertificateForSigningRequest(request);
        certificateService.writeCertificate(certificate, "certificate-req1.pem", "PEM");
        return "sign";
    }

    @RequestMapping(value = "/keys")
    public boolean generateKey() {
        String[] array = new String[2];
        SecretKeyFactory factory = null;
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        final Random r = new SecureRandom();
        byte[] salt = new byte[32];
        r.nextBytes(salt);
        KeySpec spec = new PBEKeySpec("Viki".toCharArray(), salt, 65536, 256);
        SecretKey tmp = null;
        try {
            tmp = factory.generateSecret(spec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

        SecretKey tmp2 = null;
        try {
            tmp2 = factory.generateSecret(spec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        SecretKey secret2 = new SecretKeySpec(tmp.getEncoded(), "AES");

        return secret.equals(secret2);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String readCACertificate() {
        certificateService.createKeyPairAndCertificate();
        return "Certificate";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String writeCertificate() {
        X509Certificate certificate = certificateService.createKeyPairAndCertificate();
        certificateService.writeCertificate(certificate, "certificate1.pem", "PEM");
        return "write";
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String readCertificateRequest() {
        certificateService.readCertificateSigningRequest("req.pem");
        return "read";
    }

}
