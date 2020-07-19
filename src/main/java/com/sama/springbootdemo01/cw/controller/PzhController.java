package com.sama.springbootdemo01.cw.controller;

import com.sama.springbootdemo01.cw.model.Pzh;
import com.sama.springbootdemo01.cw.model.Pzk;
import com.sama.springbootdemo01.cw.service.PzhService;
import com.sama.springbootdemo01.cw.service.PzkService;
import com.sama.springbootdemo01.system.service.UserService;
import com.sama.springbootdemo01.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * 凭证号
 * @author fjk
 * @since jdk 1.8
 * @date 2019-11-06
 */
@RequestMapping("/cw/pzh")
@Controller
public class PzhController {

    @Autowired
    private PzhService pzhService;

    @Autowired
    private PzkService pzkService;

    @Autowired
    private UserService userService;

    /**
     * 获取凭证号
     * @param ny
     * @return
     */
    @RequestMapping(value = "getLastPzhByNy",method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg getLastPzhByNy(@RequestParam("ny") String ny){

        try{
            int pzh = pzhService.getLastPzhByNy(ny);
            return new ResultMsg().success(pzh);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultMsg().error();
        }

    }

    /**
     * 添加凭证
     * @param pzh
     * @return
     */
    @RequestMapping(value = "savePz",method = RequestMethod.POST)
    @ResponseBody
    @Transactional(propagation = Propagation.REQUIRED)
    public ResultMsg savePz(@RequestBody Pzh pzh){

        try {
            if(pzh.getPzks().size()==0){
                return new ResultMsg().error("凭证信息不能为空");
            }
            //检查凭证是否相平
            Set<Pzk> pzks = pzh.getPzks();
            BigDecimal jfje = new BigDecimal(0); //初始化借方金额
            BigDecimal dfje = new BigDecimal(0); //初始化贷方金额
            for (Pzk pzk : pzks) {
                jfje = jfje.add(pzk.getJfje() == null ? BigDecimal.ZERO : pzk.getJfje());
                dfje = dfje.add(pzk.getDfje() == null ? BigDecimal.ZERO : pzk.getDfje());
            }
            if(jfje.compareTo(dfje) != 0)
                return new ResultMsg().error("凭证借贷不平，请检查凭证");


            pzh.setStatus(0);//状态：未审核
            //pzh.setCzy(userService.loginUserInfo().getUsername()); //操作员
            pzh.setCzy("admin"); //操作员
            pzh.setLogintime(new Date()); //录入时间
            //添加凭证号
            pzhService.savePzh(pzh);
            //保存凭证库
            List<Pzk> listPzk = new ArrayList<>();
            for (Pzk pzk : pzh.getPzks()){
                pzk.setPzhId(pzh.getId());
                pzk.setDfje(pzk.getDfje() == null ? BigDecimal.ZERO : pzk.getDfje());
                pzk.setJfje(pzk.getJfje() == null ? BigDecimal.ZERO : pzk.getJfje());
                listPzk.add(pzk);
            }
            pzkService.savePzkAll(listPzk);

            return new ResultMsg().success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultMsg().error();
    }


    /**
     * 修改凭证
     * @param pzh
     * @return
     */
    @Transactional
    @RequestMapping(value = "updatePz",method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg updatePz(@RequestBody Pzh pzh){

        try {
            //数据校验（自定义校验，未完成）

            //保存凭证号
            Pzh pzhOld = pzhService.getPzhById(pzh.getId());
            //操作员
            pzhOld.setCzy("admin");
            //制单日期
            pzhOld.setMakedate(pzh.getMakedate());

            //删除原凭证库
            pzkService.removePzksByPzId(pzh.getId());
            //批量添加新凭证库
            List<Pzk> listPzk = new ArrayList<>();
            for (Pzk pzk : pzh.getPzks()){
                pzk.setPzhId(pzh.getId());
                pzk.setDfje(pzk.getDfje() == null ? BigDecimal.ZERO : pzk.getDfje());
                pzk.setJfje(pzk.getJfje() == null ? BigDecimal.ZERO : pzk.getJfje());
                listPzk.add(pzk);
            }
            if(pzhService.updatePzh(pzhOld)){
                pzkService.savePzkAll(listPzk);
            }

            return new ResultMsg().success();
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return new ResultMsg().error();
    }

    /**
     * 根据id获取凭证信息
     * @param id
     * @return
     */
    @RequestMapping(value = "getPzById", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg getPzById(@RequestParam("id") Long id){
        Pzh pzh = pzhService.getPzhById(id);
        return new ResultMsg().success(pzh);
    }

    //分页多条件查询凭证列表

    //批量审核

    //批量记账

    //取消审核

    //取消记账

    //凭证科目汇总
}
