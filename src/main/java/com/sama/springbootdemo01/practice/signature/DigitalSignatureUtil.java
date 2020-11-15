package com.sama.springbootdemo01.practice.signature;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 数字签名工具类
 * @author fjk
 * @date 2020年11月12日
 * @since jdk 1.8
 */
public class DigitalSignatureUtil {

    public static final String KEY_ALGORITHM = "RSA";
    public static final int KEY_SIZE = 2048;
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    private static String CHARSET_ENCODING = "UTF-8";


    /**
     * 编码返回字符串
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }


    /**
     * 解码返回byte
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * 解码PrivateKey
     * @param key
     * @return
     */
    public static PrivateKey getPrivateKey(String key) {
        try {
            byte[] byteKey = Base64.getDecoder().decode(key);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

            return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 还原公钥
     *
     * @param key
     * @return
     */
    public static PublicKey getPublicKey(String key) {
        byte[] byteKey = Base64.getDecoder().decode(key);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteKey);
        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成RSA数字签名密钥
     */
    public static Map<String, String> initKey(){

        try {
            Map<String, String> map = new HashMap<>();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(KEY_SIZE);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            String publicKey = DigitalSignatureUtil.encryptBASE64(rsaPublicKey.getEncoded());
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            String privateKey = DigitalSignatureUtil.encryptBASE64(rsaPrivateKey.getEncoded());
            map.put("publicKey", publicKey);
            map.put("privateKey", privateKey);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用私钥签名
     * @param paramStr 签名字符串
     * @param privateKeyStr 私钥
     */
    public static String sign(String paramStr, String privateKeyStr){
        try {
            PrivateKey privateKey = getPrivateKey(privateKeyStr);
            Signature sign = Signature.getInstance(SIGNATURE_ALGORITHM);
            sign.initSign(privateKey);
            sign.update(paramStr.getBytes());
            byte[] signedData = sign.sign();
            return Base64.getEncoder().encodeToString(signedData);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 验签
     * @param publicKeyString 公钥字符串
     * @param paramStr 参数字符串
     * @param signed 签名字符串
     * @return
     */
    public static boolean verifySign(String publicKeyString, String paramStr, String signed) {

        boolean signedSuccess =false;
        try {
            PublicKey publicKey = getPublicKey(publicKeyString);

            Signature verifySign = Signature.getInstance(SIGNATURE_ALGORITHM);
            verifySign.initVerify(publicKey);
            verifySign.update(paramStr.getBytes());
            signedSuccess  = verifySign.verify(Base64.getDecoder().decode(signed));

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return signedSuccess ;
    }
}
