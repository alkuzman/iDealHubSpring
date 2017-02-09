package com.bottle.team.service;

import org.bouncycastle.pkcs.PKCS10CertificationRequest;

import java.security.cert.X509Certificate;

/**
 * Created by Viki on 2/3/2017.
 */
public interface CertificateService {

    X509Certificate createKeyPairAndCertificate();

    X509Certificate createCertificateForSigningRequest(PKCS10CertificationRequest request);

    void writeCertificate(X509Certificate certificate, String fileName, String type);

    PKCS10CertificationRequest readCertificateSigningRequest(String filename);

    PKCS10CertificationRequest convertCertificateSigningRequestFromPEM(String requestPEMFormat);

    String convertCertificateToPEM(X509Certificate certificate);

}
