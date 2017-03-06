package com.bottle.team.security;

import javax.crypto.KeyGenerator;
import java.security.*;

/**
 * Created by Viki on 2/2/2017.
 */
public class KeyGeneration {
    KeyPairGenerator pairGenerator;
    KeyGenerator keyGenerator;
    SecureRandom sr;

    public KeyGeneration() {
        sr = new SecureRandom();
    }

    public void initializePairGenerator(String algorithm, int keySize) {
        try {
            this.pairGenerator = KeyPairGenerator.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        this.pairGenerator.initialize(keySize, sr);
    }

    public void initializeKeyGenerator(String algorithm, int keySize) {
        try {
            this.keyGenerator = KeyGenerator.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        this.keyGenerator.init(keySize, sr);
    }

    public KeyPair newKeyPair() {
        KeyPair keyPair = pairGenerator.generateKeyPair();
        return keyPair;
    }

    public Key newSymmetricKey() {
        Key key = keyGenerator.generateKey();
        return key;
    }
}
