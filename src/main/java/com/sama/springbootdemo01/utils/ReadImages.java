package com.sama.springbootdemo01.utils;

import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 读取图片类
 * @author fjk
 * @date 2019-08-11
 * @since jdk 1.8
 */
public class ReadImages {


    /**
     * 根据图片路径读取图片
     * @param path  图片路径
     */
    public static void show(String path, HttpServletResponse response){

        File file = new File(path); //根据图片路径创建file对象

        try{
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file)); //创建输入流，BufferedInputStream是一个带有缓冲区的输入流,可以提高读取效率
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer); //从输入流中读取一定数量的字节，并将其存储在缓冲区数组 buffer 中。以整数形式返回实际读取的字节数。
            inputStream.close(); //关闭输入流
            OutputStream outputStream = response.getOutputStream();//通过response对象获取outputStream流
            OutputStream os = new BufferedOutputStream(outputStream);  //创建BufferedOutputStream流
            //response.setContentType("application/octet-stream");  //下载文件
            os.write(buffer); //将byte数据写入输出流，来输出文件
            os.flush(); //刷新此缓冲的输出流。这迫使所有缓冲的输出字节被写出到底层输出流中。(这里可以不用flush，close方法会自动flush)
            os.close(); //关闭输出流
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
