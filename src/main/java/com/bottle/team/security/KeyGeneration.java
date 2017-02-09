package com.bottle.team.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by Viki on 2/2/2017.
 */
public class KeyGeneration {
    KeyPairGenerator generator;
    SecureRandom sr;

    public KeyGeneration(String algorithmName) {
        sr = new SecureRandom();
        try {
            generator = KeyPairGenerator.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        generator.initialize(1024, sr);
    }

    public KeyPair newKeyPair() {
        KeyPair keyPair = generator.generateKeyPair();
        return keyPair;
    }
}
