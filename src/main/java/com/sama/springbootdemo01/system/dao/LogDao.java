package com.sama.springbootdemo01.system.dao;

import com.github.pagehelper.Page;
import com.sama.springbootdemo01.system.model.Log;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统日志
 * @author fjk
 * @since jdk1.8
 * @date 2019-06-04
 */
@Mapper
public interface LogDao {

    Page<Log> findLogByPage(String orgcode);

    void logAdd(Log log);

    List<Log> findAllByOrgcode(String orgcode);
}
