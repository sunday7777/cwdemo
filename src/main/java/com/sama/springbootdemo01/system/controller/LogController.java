package com.sama.springbootdemo01.system.controller;

import com.github.pagehelper.Page;
import com.sama.springbootdemo01.system.model.ExcelData;
import com.sama.springbootdemo01.system.model.Log;
import com.sama.springbootdemo01.system.model.User;
import com.sama.springbootdemo01.system.service.LogService;
import com.sama.springbootdemo01.utils.ExcelUtils;
import com.sama.springbootdemo01.utils.IpUtils;
import com.sama.springbootdemo01.utils.PageInfo;
import com.sama.springbootdemo01.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统日志
 * @author fjk
 * @since jdk1.8
 * @date 2019-06-04
 */
@Controller
@RequestMapping(value = "/system/log")
public class LogController {

    @Autowired
    private LogService logService;

    @RequestMapping(value = "index")
    public String index(){
        return "/log/index";
    }

    /**
     * 分页查询日志列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value= "findLogByPage",method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg findLogByPage(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
        try{
            Page<Log> list = logService.findLogByPage(pageNo,pageSize);
            PageInfo<Log> pageInfo = new PageInfo<>(list); //获取分页信息
            return new ResultMsg(200,"操作成功", list,pageInfo.getTotal());
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMsg().error();
        }
    }

    /**
     * 日志导出
     * @param response
     * @return
     */
    @RequestMapping(value = "excelExport" ,method = RequestMethod.GET)
    @ResponseBody
    public void excelExport(HttpServletResponse response){
        try{
            //查询导出日志信息
            List<Log> logList = logService.findAllByOrgcode();
            //设置导出信息
            ExcelData excelData = new ExcelData();
            //设置页签名称
            excelData.setName("日志列表");
            //设置表头
            List<String> titleList = new ArrayList<>();
            titleList.add("操作人ID");
            titleList.add("操作账号");
            titleList.add("操作类型");
            titleList.add("操作内容");
            titleList.add("操作IP");
            titleList.add("操作时间");
            excelData.setTitles(titleList);
            //设置导出数据
            List<List<Object>> rowList = new ArrayList<>();
            for(int i=0;i<logList.size();i++){
                Log log = logList.get(i);
                List<Object> oList = new ArrayList<>();
                oList.add(log.getCzrid());
                oList.add(log.getCzzh());
                oList.add(log.getCztype());
                oList.add(log.getContent());
                oList.add(log.getIp());
                oList.add(log.getCztime());
                rowList.add(oList);
            }
            excelData.setRows(rowList);
            //文件名称
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMdd");
            String fileName = "log"+dateFormat.format(date);
            //导出数据
            ExcelUtils.exportExcel(response,fileName,excelData);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
