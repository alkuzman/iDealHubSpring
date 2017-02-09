package com.bottle.team.security;

import java.security.PrivateKey;
import java.security.cert.X509Certificate;

/**
 * Created by Viki on 2/3/2017.
 */
public class CertificateAndPrivateKeyWrapper {
    private X509Certificate certificate;
    private PrivateKey privateKey;

    public CertificateAndPrivateKeyWrapper(X509Certificate certificate, PrivateKey privateKey) {
        this.certificate = certificate;
        this.privateKey = privateKey;
    }

    public X509Certificate getCertificate() {
        return certificate;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}
