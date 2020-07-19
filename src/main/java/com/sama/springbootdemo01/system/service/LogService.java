package com.sama.springbootdemo01.system.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sama.springbootdemo01.system.dao.LogDao;
import com.sama.springbootdemo01.system.model.Log;
import com.sama.springbootdemo01.system.model.User;
import com.sama.springbootdemo01.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 系统日志
 * @author fjk
 * @since jdk1.8
 * @date 2019-06-07
 */
@Service
public class LogService {

    @Autowired
    private LogDao logDao;

    @Autowired
    private UserService userService;

    /**
     * 获取日志列表
     * @return
     */
    public Page<Log> findLogByPage(int pageNo,int pageSize){
        //获取当前登陆用户行政区划
        String orgcode = userService.loginUserInfo().getOrgcode();
        PageHelper.startPage(pageNo,pageSize);//分页
        return logDao.findLogByPage(orgcode);
    }


    /**
     * 添加日志
     * @param request
     * @param content
     * @param cztype
     * @return
     */
    public boolean logAdd(HttpServletRequest request, String content, String cztype){
        try{


            Log log = new Log();
            log.setContent(content); //操作内容
            log.setCztype(cztype); //操作类型
            log.setIp(IpUtils.getIpAddress(request));//客户端ip

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            log.setCztime(sdf.format(new Date())); //操作时间

            //当前登陆用户信息
            User user = userService.loginUserInfo();
            log.setOrgcode(user.getOrgcode());
            log.setCzrid(user.getId()); //操作人id
            log.setCzzh(user.getUsername()); //操作人用户名

            logDao.logAdd(log);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查询当前地区全部日志
     * @return
     */
    public List<Log> findAllByOrgcode(){
        //当前登陆用户信息
        User user = userService.loginUserInfo();
        return logDao.findAllByOrgcode(user.getOrgcode());
    }
}
