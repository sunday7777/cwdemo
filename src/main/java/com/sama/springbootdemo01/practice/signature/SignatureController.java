package com.sama.springbootdemo01.practice.signature;

import com.sama.springbootdemo01.practice.signature.bo.SignatureBo;
import com.sama.springbootdemo01.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 接口签名及数字签名练习
 * @author fjk
 * @date 2020年10月31日
 * @since jdk 1.8
 */
@RequestMapping(value = "signatureTest")
@RestController
public class SignatureController {

    @Autowired
    private SignatureService signatureService;


    /**
     * 密钥
     */
    private String secretKey = "jskdjfkjoolljskdjfllalksnkkdjfjksk";

    /**
     * 请求有效时间10分钟
     */
    private int validMinute = 10;


    /**
     * 参数签名(模拟第三方生成签名)
     */
    @RequestMapping(value = "signature", method = RequestMethod.POST)
    public ResultMsg signature(@RequestBody SignatureBo signatureBo){
        String signStr = signatureService.checkSignature(signatureBo, secretKey);
        return new ResultMsg().success(signStr);
    }

    /**
     * AccessKey&SecretKey接口签名（模拟保存用户）
     */
    @RequestMapping(value = "test", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ResultMsg test(@RequestBody SignatureBo signatureBo){

        //根据timestamp判断接口是否过期
        long minute = (System.currentTimeMillis() - signatureBo.getTimestamp()) / 1000 / 60;
        if (minute > validMinute){
            return new ResultMsg().error("请求时间已超有效期，请重新发起请求");
        }

        //根据nonce判断接口是否已经请求过了
        if (signatureService.checkNonce()){
            return new ResultMsg().error("请勿重复发送请求");
        }

        //验证签名是否正确
        try {
            String signStr = signatureService.checkSignature(signatureBo, secretKey);
            if (!signatureBo.getSignInfo().equals(signStr)){
                return new ResultMsg().error("签名验证失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        //验证通过，保存用户
        if (!signatureService.saveUser()){
            return new ResultMsg().error("保存失败");
        }

        //登录成功
        try {
            //数据库储存本次请求的nonce
            signatureService.saveNonce();
            //清除数据库大于有效期的nonce
            signatureService.removeNonce();
            return new ResultMsg().success();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }


    /**
     * 生成数字签名公钥、私钥
     */
    @RequestMapping(value = "getKeyMap", method = RequestMethod.GET)
    public ResultMsg getKeyMap(){
        try {
            Map<String, String> keyMap = DigitalSignatureUtil.initKey();
            System.out.println("privateKey：" + keyMap.get("privateKey"));
            System.out.println("publicKey：" + keyMap.get("publicKey"));
            return new ResultMsg().success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMsg().error("公私钥生成失败");
    }

    /**
     * 模拟请求方，生成数字签名的方法
     */
    @RequestMapping(value = "digitalSign", method = RequestMethod.POST)
    public ResultMsg digitalSign(@RequestBody SignatureBo signatureBo){

        //getKeyMap方法中生成的私钥
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDf3hJOgcHHSH2d8KVjSsDghXO2" +
                "QjlJqAB0Ay76YzWnL5Q6fCAoVjkBYl5zn2fyJC5CkvARXenM1JEh7ZnevyZWOxdNPDNIH8dJaQmP" +
                "nIvK2HtNUKp5lcxEhOjCSX3lyGC7az/Lwkxp8E6/7filKUC2YqkSR9nKmLpfbVn00F3qSyKwIA7Q" +
                "/dboF4vYaaXeGhRiYNfelto7dR+8YEybuJvD88trAWMq54iEbGJ6PKwRpdLmzUuRU3EsnGEOMSHM" +
                "/R6Jk0YA4RV0/Cctvx1z7T5cfxM/esACeWPt/e8xC/ECrQ1CXgTVkxVxQIyVssWx9BGQ4DHDvXYz" +
                "KSJNVhkMux7DAgMBAAECggEADC5bNKZe0ucg3q0vcp2orQ7V4v7rexSHIcjij4FCdBxkpmIGpes7" +
                "ItYlaYks8MIDOmAga/F+PUT7uKrjCnnALkT82ZkC+VOxVk8+0++is6Df5K8U95StDrVkrN2u2gfz" +
                "K3+EcyPZYJJQ1qSuON5rxQDNTk9WjG2PBo4+rNGBqrPdzUnF2cHzaaHEHuUIrAokMNgdGWc1xhab" +
                "X2rN1DZc7DyT7heQJbBGWu0+f7q7IliwYTvTiBc9K0/fHVe4DpcfrEXw/jnD8ipoegJdC8S2LUge" +
                "/Sv18KqPH2dJ8HAbhrkAAjFNIQphaAFpWoWfy0iQyDGzl4G93ZHZVBCWCesmYQKBgQD/yimBcyse" +
                "iJ2P/jxBn2Ns5fEcnJK3VvF35ZpjFuyv8u0rwKerBnlIGEQ0SWaII0cbh1555y1jE+wRoUf1z7xs" +
                "kSIDDDf6i6AmbNIudE+WeZY1U08yvBWP4k/v7anVhAI79010KVtIaT5wGX2lkCrpBC4EjYJlW3kY" +
                "8FLwndmLiQKBgQDgDTDDYrgTADMS68EqKYyRvCnQHIUg7tMX1AhMqIilIgozgL/JSCa/HaX/BveI" +
                "NwiEaHapLnjz7PdZ0clXLyIYGQ/32jAObNoEvmykeNAzA5/EIRUS5rsjlzVLhdtKPeAtGQFlABpV" +
                "1Wj1lIuuNZLhkUF7qZJoC0Cy73fhLp3I6wKBgGVpQkFnZr4JTear1jnyNloTZB+C2AxGNR/i8O2+" +
                "RgKQHoXNMOx2eZkivc+5FiR9UBDS/5r3E6CHYn2u4rfLL5ofhEuPCZaUyt7RIBd416EB/ZKQwAKO" +
                "XUIZaLXoFQBpAPXDW3rldqJjz3RkNtNwMAMIPDJj5EHJHV7M0SVCf+B5AoGAE2JQw7a4wjwULyDi" +
                "d2lc1gb3fx/YjL7c2ITpqugx2keVplsPPLTXVVWVVPkfVVOdkwcOrRhuMnxf8g9qYfab9oXEkeb4" +
                "nWbhfeOTtq8VJDS+D9Pk5Nu9eRjN7ZjJHiU4Te6J5lkZ7i8mg4i0hP/xkz5j8fT7y7Cdil2Ze4vy" +
                "7r0CgYEAl8aqk3WSSOyREDvONPwJGAreaaEvHmAD2zN+THlCd4gaL4XMJgvwBDpU6MPgTqvEuCmw" +
                "az/eGyTTOT0ivK9AJIGOgtl2H+2yHZIzjwtHE0+YHeSnuUSwX7DKh0yO22s+8CZ+uRDZwoFv0JOM" +
                "7BkUh50YY5iIf4CqqEYUWFHAOGY=";
        //按顺序拼接参数字符串(要签名的字符串)
        String paramStr = "name=" + signatureBo.getName() + "&age=" + signatureBo.getAge();
        try {
            //生成并返回RSA数字签名字符串
            String signStr = DigitalSignatureUtil.sign(paramStr, privateKey);
            System.out.println("签名串：" + signStr);
            return new ResultMsg().success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMsg().error("签名失败");
    }

    /**
     * 服务器验证数字签名，保存用户
     */
    @RequestMapping(value = "saveUser", method = RequestMethod.POST)
    public ResultMsg saveUser(@RequestBody SignatureBo signatureBo){

        //getKeyMap方法中生成的公钥
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA394SToHBx0h9nfClY0rA4IVztkI5SagA" +
                "dAMu+mM1py+UOnwgKFY5AWJec59n8iQuQpLwEV3pzNSRIe2Z3r8mVjsXTTwzSB/HSWkJj5yLyth7" +
                "TVCqeZXMRITowkl95chgu2s/y8JMafBOv+34pSlAtmKpEkfZypi6X21Z9NBd6ksisCAO0P3W6BeL" +
                "2Gml3hoUYmDX3pbaO3UfvGBMm7ibw/PLawFjKueIhGxiejysEaXS5s1LkVNxLJxhDjEhzP0eiZNG" +
                "AOEVdPwnLb8dc+0+XH8TP3rAAnlj7f3vMQvxAq0NQl4E1ZMVcUCMlbLFsfQRkOAxw712MykiTVYZ" +
                "DLsewwIDAQAB";

        //按顺序拼接参数字符串
        String paramStr = "name=" + signatureBo.getName() + "&age=" + signatureBo.getAge();

        try {
            //使用公钥进行验签
            if (!DigitalSignatureUtil.verifySign(publicKey, paramStr, signatureBo.getSignInfo())){
                return new ResultMsg().error("签名验证不通过");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMsg().error("签名验证失败");
        }

        //保存用户
        signatureService.saveUser();
        return new ResultMsg().success("签名验证通过，用户保存成功");
    }
}
