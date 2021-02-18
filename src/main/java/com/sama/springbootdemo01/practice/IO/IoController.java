package com.sama.springbootdemo01.practice.IO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * IO流练习
 * @author fjk
 * @date 2020年12月12日
 * @since jdk 1.8
 */
@RequestMapping(value = "IO")
@RestController
public class IoController {

    /**
     * 上传文件
     */
    private void upload(){
        //自定义上传目录
        String path = "";


    }

    /**
     * 下载文件
     */
    public void download(@RequestParam("path") String path, @RequestParam("fileName") String fileName){

        File file = new File(path+fileName);

    }

    /**
     * 从输入流中读取参数
     */
}
