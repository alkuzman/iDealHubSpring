package com.bottle.team.service.impl;

import com.bottle.team.security.CertificateAndPrivateKeyWrapper;
import com.bottle.team.security.CertificateOperations;
import com.bottle.team.security.KeyGeneration;
import com.bottle.team.service.CertificateService;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.cert.X509Certificate;

/**
 * Created by Viki on 2/3/2017.
 */
@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateOperations certificateOperations;

    @Autowired
    private CertificateAndPrivateKeyWrapper caCertificate;

    @Autowired
    private KeyGeneration keyGeneration;

    @Override
    public X509Certificate createKeyPairAndCertificate() {
        KeyPair keyPair = keyGeneration.newKeyPair();
        return certificateOperations.generateSignedCertificate(keyPair.getPublic());
    }

    @Override
    public X509Certificate createCertificateForSigningRequest(PKCS10CertificationRequest request) {
        return certificateOperations.generateSignedCertificate(request);
    }

    @Override
    public void writeCertificate(X509Certificate certificate, String fileName, String type) {
        certificateOperations.writeCertificate(certificate, fileName, type);
    }

    @Override
    public PKCS10CertificationRequest readCertificateSigningRequest(String filename) {
        return certificateOperations.readCertificateSigningRequest(filename);
    }

    @Override
    public PKCS10CertificationRequest convertCertificateSigningRequestFromPEM(String requestPEMFormat) {
        return certificateOperations.convertCertificateSigningRequestFromPEM(requestPEMFormat);
    }

    @Override
    public String convertCertificateToPEM(X509Certificate certificate) {
        return certificateOperations.convertCertificateToPEM(certificate);
    }

}
