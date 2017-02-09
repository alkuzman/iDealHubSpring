package com.bottle.team.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Viki on 2/3/2017.
 */
@EnableConfigurationProperties
@Configuration
@ConfigurationProperties(prefix = "app.security.certificate")
public class CertificateSettings {
    private Integer validityDays;
    private String caFile;
    private String caPassphrase;
    private String caAlias;
    private String filesStorePath;
    private String requestsStorePath;

    public Integer getValidityDays() {
        return validityDays;
    }

    public void setValidityDays(Integer validityDays) {
        this.validityDays = validityDays;
    }

    public String getCaFile() {
        return caFile;
    }

    public void setCaFile(String caFile) {
        this.caFile = caFile;
    }

    public String getCaPassphrase() {
        return caPassphrase;
    }

    public void setCaPassphrase(String caPassphrase) {
        this.caPassphrase = caPassphrase;
    }

    public String getCaAlias() {
        return caAlias;
    }

    public void setCaAlias(String caAlias) {
        this.caAlias = caAlias;
    }

    public String getFilesStorePath() {
        return filesStorePath;
    }

    public void setFilesStorePath(String filesStorePath) {
        this.filesStorePath = filesStorePath;
    }

    public String getRequestsStorePath() {
        return requestsStorePath;
    }

    public void setRequestsStorePath(String requestsStorePath) {
        this.requestsStorePath = requestsStorePath;
    }
}
