package com.sama.springbootdemo01.system.controller;

import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试Controller
 * @author fjk
 * @since 2019-02-24
 */
@Controller
@RequestMapping(value = "test",method = RequestMethod.GET)
public class TestController {

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    public void httpclientTest(){

        /**
        // 创建Httpclient对象,相当于打开了浏览器
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // 创建HttpGet请求，相当于在浏览器输入地址
        HttpGet httpGet = new HttpGet("http://www.baidu.com/");

        CloseableHttpResponse response = null;
        try {
            // 执行请求，相当于敲完地址后按下回车。获取响应
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                // 解析响应，获取数据
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(content);
            }
        } finally {
            if (response != null) {
                // 关闭资源
                response.close();
            }
            // 关闭浏览器
            httpclient.close();
        }
         */
    }

    @RequestMapping("redisTest")
    @ResponseBody
    public void redisTest(){
        // 保存字符串
        stringRedisTemplate.opsForValue().set("ccc", "kkk");
    }

    /**
     * xml转json测试
    * @return
     */
    @RequestMapping("xmlTest")
    @ResponseBody
    public JSONObject xmlTest(){
        String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\n" +
                "<note>\n" +
                "<to>Tove</to>\n" +
                "<from>Jani</from>\n" +
                "<heading>哈哈哈哈</heading>\n" +
                "<body>Don't forget me this weekend!</body>\n" +
                "</note>";
        XMLSerializer xmlSerializer = new XMLSerializer();
        String resutStr = xmlSerializer.read(xml).toString();
        JSONObject result = JSONObject.fromObject(resutStr);

        return result;
    }
}
