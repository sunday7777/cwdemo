package com.sama.springbootdemo01.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 允许上传的文件后缀
 */
public class FileSuffix {

    public static List<String> suffixList = new ArrayList<>();

    static {
        suffixList.add(".jpg");
        suffixList.add(".jpge");
        suffixList.add(".png");
        suffixList.add(".gif");
        suffixList.add(".avi");
        suffixList.add(".mp4");
        suffixList.add(".mp3");
        suffixList.add(".doc");
        suffixList.add(".docx");
        suffixList.add(".xls");
        suffixList.add(".xlsx");
        suffixList.add(".ppt");
        suffixList.add(".pptx");
        suffixList.add(".pdf");
        suffixList.add(".txt");
        suffixList.add(".zip");
        suffixList.add(".rar");

    }
}
