package com.sama.springbootdemo01.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 文件上传类
 * @author fjk
 * @date 2019-07-25
 * @since jdk 1.8
 */
public class FileUploadUtils {


    /**
     * 上传附件
     * @param request
     * @param path 上传文件路径
     * @return
     */
    public List<Map<String,String>> fileUpload(HttpServletRequest request, String path) {

        List<Map<String,String>> nameList = new ArrayList<>();

        //转换成多部分request
        MultipartHttpServletRequest multpartRequest = (MultipartHttpServletRequest) request;

        //获取全部上传文件名
        Iterator<String> names = multpartRequest.getFileNames();

        //循环上传文件
        while (names.hasNext()) {

            MultipartFile file = multpartRequest.getFile(names.next());
            //原名称
            String fileNameOld = file.getOriginalFilename();
            if (!fileNameOld.trim().equals("")) {
                //获取文件后缀并转化为小写
                String suffix = fileNameOld.substring(fileNameOld.lastIndexOf(".")).toLowerCase();
                //判断是否允许上传该附件
                if(!FileSuffix.suffixList.contains(suffix)){
                    System.out.println(suffix+"为不允许上传的文件类型");
                    continue;
                }
                //组装新名称
                String fileName = UUID.randomUUID().toString() + suffix;
                //上传路径
                File url = new File(path);
                //创建上传路径
                if (!url.exists()) {
                    url.mkdirs();
                }
                //创建文件对象
                File dest = new File(url +"/"+ fileName);
                //上传附件
                try {
                    file.transferTo(dest);
                    Map<String,String> nameMap = new HashMap();
                    nameMap.put("oName",fileNameOld);
                    nameMap.put("name",fileName);
                    nameList.add(nameMap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return nameList;

    }



}
