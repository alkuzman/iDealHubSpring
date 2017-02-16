package com.bottle.team.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateCrtKey;

@Component
public class CertificateAndPrivateKeyWrapperFactory {

    @Autowired
    private CertificateSettings settings;

    public CertificateAndPrivateKeyWrapper createCACertificateAndPrivateKeyWrapper() {
        return readCACertificate();
    }

    private CertificateAndPrivateKeyWrapper readCACertificate() {
        KeyStore caKs = null;
        X509Certificate caCert = null;
        RSAPrivateCrtKey privKey = null;
        try {
            caKs = KeyStore.getInstance("PKCS12");
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        try {
            if (caKs != null) {
                caKs.load(new FileInputStream(settings.getCaFile()),
                        settings.getCaPassphrase().toCharArray());
            }
            Key key = null;
            if (caKs != null) {
                key = caKs.getKey(settings.getCaAlias(), settings.getCaPassphrase().toCharArray());
            }
            if (key == null) {
                throw new RuntimeException("Got null key from keystore!");
            }
            privKey = (RSAPrivateCrtKey) key;

            caCert = (X509Certificate) caKs.getCertificate(settings.getCaAlias());
            if (caCert == null) {
                throw new RuntimeException("Got null cert from keystore");
            }
        } catch (IOException |
                UnrecoverableKeyException | KeyStoreException | CertificateException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return new CertificateAndPrivateKeyWrapper(caCert, privKey);
    }

}
