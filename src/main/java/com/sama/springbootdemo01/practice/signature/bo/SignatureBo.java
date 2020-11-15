package com.sama.springbootdemo01.practice.signature.bo;

/**
 * 接口签名测试
 * @author fjk
 * @date 2020年11月02日
 * @since jdk 1.8
 */
public class SignatureBo {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private String age;

    /**
     * 开发者标识
     */
    private String accessKey;

    /**
     * 每个请求的唯一的标识符
     */
    private String nonce;

    /**
     * 请求时间戳
     */
    private long timestamp;

    /**
     * 签名信息
     */
    private String signInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignInfo() {
        return signInfo;
    }

    public void setSignInfo(String signInfo) {
        this.signInfo = signInfo;
    }
}
