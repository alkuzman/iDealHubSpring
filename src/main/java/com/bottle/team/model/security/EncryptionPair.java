package com.bottle.team.model.security;

import com.bottle.team.model.BaseEntityImpl;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by Viki on 2/26/2017.
 */
@NodeEntity
public class EncryptionPair extends BaseEntityImpl {

    @Property(name = "encryptedPrivateKey")
    private String privateEncrypted;

    @Property(name = "publicKeyPEM")
    private String publicPem;

    public String getPrivateEncrypted() {
        return privateEncrypted;
    }

    public void setPrivateEncrypted(String privateEncrypted) {
        this.privateEncrypted = privateEncrypted;
    }

    public String getPublicPem() {
        return publicPem;
    }

    public void setPublicPem(String publicPem) {
        this.publicPem = publicPem;
    }
}
