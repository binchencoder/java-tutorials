package com.binchencoder.security.examples;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.io.File;
import java.nio.charset.Charset;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class KeyPairGeneratorTest {

    @Test
    public void test() throws Exception {
        // 加密算法
        String algorithm = "RSA";
        //  创建密钥对生成器对象
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        // 生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 生成私钥
        PrivateKey privateKey = keyPair.getPrivate();
        // 生成公钥
        PublicKey publicKey = keyPair.getPublic();

        // 加密算法
        String algorithm2 = "sha256withrsa";
        String input = "Hello World";
        // 生成签名
        String signaturedData = getSignature(input, algorithm2, privateKey);
        // 校验签名
        boolean verifySignature = verifySignature(input, algorithm2, publicKey, signaturedData);
    }

    @Test
    public void generateKeyToFile() throws Exception {
        String algorithm = "RSA";
        String pubPath = "/home/chenbin/a.store";
        String priPath = "/home/chenbin/b.store";
        //获取密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        //获取密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //获取公钥
        PublicKey publicKey = keyPair.getPublic();
        //获取私钥
        PrivateKey privateKey = keyPair.getPrivate();
        //获取byte数组
        byte[] publicKeyEncode = publicKey.getEncoded();
        byte[] privateKeyEncoded = privateKey.getEncoded();
        //进行Base64编码
        String publicKeyStr = Base64.encode(publicKeyEncode);
        String privateKeyStr = Base64.encode(privateKeyEncoded);
        //保存文件
        FileUtils.writeStringToFile(new File(pubPath), publicKeyStr, Charset.forName("UTF-8"));
        FileUtils.writeStringToFile(new File(priPath), privateKeyStr, Charset.forName("UTF-8"));
    }

    /**
     * 生成签名
     *
     * @param input      原文
     * @param algorithm  算法
     * @param privateKey 私钥
     * @return : 签名
     */
    private static String getSignature(String input, String algorithm, PrivateKey privateKey)
        throws Exception {
        // 获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        // 初始化签名
        signature.initSign(privateKey);
        // 传入原文
        signature.update(input.getBytes());
        // 开始签名
        byte[] sign = signature.sign();
        // 对签名数据进行Base64编码
        return Base64.encode(sign);
    }

    /**
     * 校验签名
     *
     * @param input          原文
     * @param algorithm      算法
     * @param publicKey      公钥
     * @param signaturedData 签名
     * @return : 数据是否被篡改
     */
    private static boolean verifySignature(String input, String algorithm, PublicKey publicKey,
        String signaturedData) throws Exception {
        // 获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        // 初始化签名
        signature.initVerify(publicKey);
        // 传入原文
        signature.update(input.getBytes());
        // 校验数据
        return signature.verify(Base64.decode(signaturedData));
    }
}
