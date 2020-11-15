package com.sama.springbootdemo01.practice.signature;

import com.sama.springbootdemo01.practice.signature.bo.SignatureBo;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import sun.security.rsa.RSASignature;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 接口签名测试类
 * @author fjk
 * @date 2020年11月4日
 * @since jdk1.8
 */
@Service
public class SignatureService {

    /**
     * 签名
     */
    public String checkSignature(SignatureBo signatureBo, String secretKey){

        //按顺序拼接参数字符串
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("name=").append(signatureBo.getName())
                .append("&age=").append(signatureBo.getAge())
                .append("&accessKey=").append(signatureBo.getAccessKey())
                .append("&nonce=").append(signatureBo.getNonce())
                .append("&timestamp=").append(signatureBo.getTimestamp())
                .append(secretKey);
        //md5加密
        String md5Str = DigestUtils.md5DigestAsHex(stringBuffer.toString().getBytes());
        //将md5字符串转为大写

        return md5Str.toUpperCase();
    }

    /**
     * 模拟从数据库查询nonce是否存在
     */
    public boolean checkNonce(){
        return false;
    }

    /**
     * 模拟保存用户
     */
    public boolean saveUser(){
        return true;
    }

    /**
     * 模拟保存本次请求的nonce
     */
    public boolean saveNonce(){
        return true;
    }


    /**
     * 模拟从数据库中删除大于有效期的nonce
     */
    public boolean removeNonce(){
        return true;
    }
}
