package com.bottle.team.model.security;

import com.bottle.team.model.BaseEntityImpl;
import com.bottle.team.model.authentication.Agent;
import com.bottle.team.model.enumaration.CertificateType;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by Viki on 2/7/2017.
 */
@NodeEntity
public class SecurityProfile extends BaseEntityImpl {
    @Property(name = "certificationRequestPEM")
    private String certificationRequestPEM;

    @Property(name = "certificatePEM")
    private String certificatePEM;

    @Property(name = "encryptedPrivateKey")
    private String encryptedPrivateKey;

    @Property(name = "certificateType")
    private CertificateType certificateType;

    @Relationship(type = "IDENTITY")
    private Agent agent;

    public String getCertificationRequestPEM() {
        return certificationRequestPEM;
    }

    public void setCertificationRequestPEM(String certificationRequestPEM) {
        this.certificationRequestPEM = certificationRequestPEM;
    }

    public String getCertificatePEM() {
        return certificatePEM;
    }

    public void setCertificatePEM(String certificatePEM) {
        this.certificatePEM = certificatePEM;
    }

    public String getEncryptedPrivateKey() {
        return encryptedPrivateKey;
    }

    public void setEncryptedPrivateKey(String encryptedPrivateKey) {
        this.encryptedPrivateKey = encryptedPrivateKey;
    }

    public CertificateType getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(CertificateType certificateType) {
        this.certificateType = certificateType;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    @Override
    public String toString() {
        return "Security information for" + agent.toString();
    }
}
