package com.binchencoder.security.examples;

import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

public class KeyToolExample {

    public static void main(String[] args) throws Exception {
        if (Objects.isNull(Security.getProvider("BC"))) {
            Security.addProvider(new BouncyCastleProvider());
        }

        // 创建一个新的密钥库
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(null, null);

        // 生成密钥对
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();

        // 创建自签名证书
        X500Principal subject = new X500Principal(
            "CN=My Domain, O=My Organization, L=My City, ST=My State, C=My Country");
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        ContentSigner signer = new JcaContentSignerBuilder("SHA256withRSA")
            .setProvider("BC")
            .build(privateKey);
        X509Certificate cert = new JcaX509CertificateConverter().getCertificate(
            new JcaX509v3CertificateBuilder(
                subject,
                BigInteger.valueOf(System.currentTimeMillis()),
                Date.from(Instant.now()),
                Date.from(Instant.now().plusSeconds(365 * 24 * 60 * 60)),
                subject,
                publicKey
            ).build(signer));

        // 将密钥对和证书添加到密钥库
        keyStore.setKeyEntry("myalias", privateKey, "mypassword".toCharArray(),
            new Certificate[]{cert});

        // 将密钥库保存到文件
        try (FileOutputStream fos = new FileOutputStream("mykeystore.jks")) {
            keyStore.store(fos, "mypassword".toCharArray());
        }
    }
}
