package com.bottle.team.security;

import org.apache.commons.io.FileUtils;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.security.x509.X509CertImpl;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Viki on 2/2/2017.
 */
@Component
public class CertificateOperations {

    @Autowired
    private CertificateSettings settings;

    @Autowired
    private CertificateAndPrivateKeyWrapper caCertificate;

    public X509Certificate generateSignedCertificate(PKCS10CertificationRequest request) {
        TBSCertificate tbsCertificate = generateCertificate(request.getSubjectPublicKeyInfo(),
                request.getSignatureAlgorithm(), request.getSubject());
        return generateSignedCertificate(tbsCertificate);
    }

    public X509Certificate generateSignedCertificate(PublicKey publicKey) {
        TBSCertificate tbsCertificate = generateCertificate(publicKey);
        return generateSignedCertificate(tbsCertificate);
    }

    private X509Certificate generateSignedCertificate(TBSCertificate tbsCertificate) {
        ASN1EncodableVector certASN = new ASN1EncodableVector();

        byte[] signature = signCertificate(tbsCertificate, caCertificate.getPrivateKey());

        certASN.add(tbsCertificate);
        certASN.add(tbsCertificate.getSignature());
        certASN.add(new DERBitString(signature));

        X509Certificate certificate = null;
        try {
            certificate = new X509CertImpl(new DERSequence(certASN).getEncoded());
        } catch (CertificateException | IOException e) {
            e.printStackTrace();
        }
        return certificate;
    }

    private TBSCertificate generateCertificate(PublicKey pubKey) {
        //V_3 Certificate generator
        V3TBSCertificateGenerator certGen = new V3TBSCertificateGenerator();

        //Serial Number
        certGen.setSerialNumber(new ASN1Integer(BigInteger.valueOf(System.currentTimeMillis())));

        //Algorithm identifier
        ASN1ObjectIdentifier sigOID = X509Util.getAlgorithmOID("SHA1WithRSAEncryption");
        AlgorithmIdentifier sigAlgId = new AlgorithmIdentifier(sigOID, DERNull.INSTANCE);
        certGen.setSignature(sigAlgId);

        //Issuer Name
        X500Name caCert500Name = null;
        try {
            caCert500Name = new JcaX509CertificateHolder(caCertificate.getCertificate()).getSubject();
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        }
        certGen.setIssuer(caCert500Name);

        //TODO: Subject specific information
        String commonName = "Viki";
        String countryName = "MK";
        String stateOrProvinceName = "Macedonia";
        String organization = "iDeal";

        X500NameBuilder subjectNameBuilder = new X500NameBuilder();
        subjectNameBuilder.addRDN(X509ObjectIdentifiers.commonName, commonName);
        subjectNameBuilder.addRDN(X509ObjectIdentifiers.countryName, countryName);
        subjectNameBuilder.addRDN(X509ObjectIdentifiers.stateOrProvinceName, stateOrProvinceName);
        subjectNameBuilder.addRDN(X509ObjectIdentifiers.organization, organization);
        certGen.setSubject(subjectNameBuilder.build());

        //Subject public key info
        try {
            certGen.setSubjectPublicKeyInfo(SubjectPublicKeyInfo.getInstance(new ASN1InputStream(
                    new ByteArrayInputStream(pubKey.getEncoded())).readObject()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Validity
        Calendar expiry = Calendar.getInstance();
        expiry.add(Calendar.DAY_OF_YEAR, settings.getValidityDays());
        certGen.setStartDate(new Time(new Date(System.currentTimeMillis())));
        certGen.setEndDate(new Time(expiry.getTime()));

        return certGen.generateTBSCertificate();
    }

    private TBSCertificate generateCertificate(SubjectPublicKeyInfo subjectPublicKeyInfo, AlgorithmIdentifier sigAlgId,
                                               X500Name subjectName) {
        //V_3 Certificate generator
        V3TBSCertificateGenerator certGen = new V3TBSCertificateGenerator();

        //Serial Number
        certGen.setSerialNumber(new ASN1Integer(BigInteger.valueOf(System.currentTimeMillis())));

        //Algorithm identifier
        certGen.setSignature(sigAlgId);

        //Issuer Name
        X500Name caCert500Name = null;
        try {
            caCert500Name = new JcaX509CertificateHolder(caCertificate.getCertificate()).getSubject();
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        }
        certGen.setIssuer(caCert500Name);

        //Subject Name
        certGen.setSubject(subjectName);

        //Subject public key info
        certGen.setSubjectPublicKeyInfo(subjectPublicKeyInfo);


        //Validity
        Calendar expiry = Calendar.getInstance();
        expiry.add(Calendar.DAY_OF_YEAR, settings.getValidityDays());
        certGen.setStartDate(new Time(new Date(System.currentTimeMillis())));
        certGen.setEndDate(new Time(expiry.getTime()));

        return certGen.generateTBSCertificate();
    }

    private byte[] signCertificate(TBSCertificate certificate, PrivateKey caPrivateKey) {
        byte[] signature = null;
        try {
            Signature signer = Signature.getInstance("SHA1WithRSA");
            signer.initSign(caPrivateKey);
            signer.update(certificate.getEncoded());
            signature = signer.sign();
        } catch (IOException | SignatureException | InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return signature;
    }

    /*
    public void signCertificate2(TBSCertificate certificate, RSAPrivateCrtKeyParameters caPrivateKey,
                                AlgorithmIdentifier sigAlgId) {
        SHA1Digest digester = new SHA1Digest();
        AsymmetricBlockCipher rsa = new PKCS1Encoding(new RSAEngine());
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        DEROutputStream dOut = new DEROutputStream(bOut);
        try {
            dOut.writeObject(certificate);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] signature = null;
        byte[] certBlock = bOut.toByteArray();
        digester.update(certBlock, 0, certBlock.length);
        byte[] hash = new byte[digester.getDigestSize()];
        digester.doFinal(hash, 0);

        rsa.init(true, caPrivateKey);
        DigestInfo dInfo = new DigestInfo(new AlgorithmIdentifier(X509ObjectIdentifiers.id_SHA1, null), hash);
        byte[] digest = null;
        try {
            digest = dInfo.getEncoded(ASN1Encoding.DER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            signature = rsa.processBlock(digest, 0, digest.length);
        } catch (InvalidCipherTextException e) {
            e.printStackTrace();
        }

        ASN1EncodableVector  v = new ASN1EncodableVector();

        v.add(certificate);
        v.add(sigAlgId);
        v.add(new DERBitString(signature));
    }
    */

    public PKCS10CertificationRequest readCertificateSigningRequest(String filename) {
        String fullFileName = settings.getRequestsStorePath() + filename;
        PKCS10CertificationRequest request = null;
        try {
            InputStream is = new FileInputStream(fullFileName);
            Reader pemReader = new BufferedReader(new InputStreamReader(is));
            PEMParser pemParser = new PEMParser(pemReader);
            Object parsedObject = pemParser.readObject();
            request = (PKCS10CertificationRequest) parsedObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    public PKCS10CertificationRequest convertCertificateSigningRequestFromPEM(String requestPEMFormat) {
        PKCS10CertificationRequest request = null;
        try {
            PEMParser pemParser = new PEMParser(new StringReader(requestPEMFormat));
            Object parsedObject = pemParser.readObject();
            request = (PKCS10CertificationRequest) parsedObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return request;
    }

    public void writeCertificate(X509Certificate certificate, String fileName, String type) {
        if (type.equalsIgnoreCase("CRT")) {
            writeCrtCertificate(certificate, fileName);
            return;
        }
        if (type.equalsIgnoreCase("PEM")) {
            writePemCertificate(certificate, fileName);
        }

    }

    private void writePemCertificate(X509Certificate certificate, String fileName) {
        String fullFileName = settings.getFilesStorePath() + fileName;
        JcaPEMWriter pw;
        try {
            pw = new JcaPEMWriter(new FileWriter(fullFileName));
            pw.writeObject(certificate);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeCrtCertificate(X509Certificate certificate, String fileName) {
        String fullFileName = settings.getFilesStorePath() + fileName;
        try {
            FileUtils.writeByteArrayToFile(new File(fullFileName), certificate.getEncoded());
        } catch (CertificateEncodingException | IOException e) {
            e.printStackTrace();
        }
    }

    public String convertCertificateToPEM(X509Certificate certificate) {
        JcaPEMWriter pw;
        String result = null;
        try {
            StringWriter sw = new StringWriter();
            pw = new JcaPEMWriter(sw);
            pw.writeObject(certificate);
            pw.flush();
            pw.close();
            sw.flush();
            result = sw.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
