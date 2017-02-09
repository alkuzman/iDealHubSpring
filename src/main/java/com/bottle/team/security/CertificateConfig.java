package com.bottle.team.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Viki on 2/3/2017.
 */
@Configuration
public class CertificateConfig {

    private final
    CertificateAndPrivateKeyWrapperFactory certificateAndPrivateKeyWrapperFactory;

    @Autowired
    public CertificateConfig(CertificateAndPrivateKeyWrapperFactory certificateAndPrivateKeyWrapperFactory) {
        this.certificateAndPrivateKeyWrapperFactory = certificateAndPrivateKeyWrapperFactory;
    }

    @Bean
    public CertificateAndPrivateKeyWrapper getCACertificate() {
        return certificateAndPrivateKeyWrapperFactory.createCACertificateAndPrivateKeyWrapper();
    }

    @Bean
    public KeyGeneration getKeyGenerator() {
        return new KeyGeneration("RSA");
    }
}
