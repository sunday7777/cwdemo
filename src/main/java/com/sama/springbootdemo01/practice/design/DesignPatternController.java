package com.sama.springbootdemo01.practice.design;

import com.sama.springbootdemo01.practice.design.factory.Desk;
import com.sama.springbootdemo01.practice.design.factory.DeskFactory;
import com.sama.springbootdemo01.practice.design.factory.DeskMaterialEnum;
import com.sama.springbootdemo01.practice.design.strategy.DiscountStrategy;
import com.sama.springbootdemo01.practice.design.strategy.Vip;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设计模式练习
 * @author fjk
 * @date 2020年08月22日
 * @since jdk 1.8
 */
@RestController
@RequestMapping(value = "/practice/designPattern")
public class DesignPatternController {

    /**
     * 单例模式调用
     */
    @RequestMapping(value = "singletonTest", method = RequestMethod.GET)
    public void singletonTest(){

        //饿汉模式测试
        SingletonHungry singletonHungry1 = SingletonHungry.getInstance();
        SingletonHungry singletonHungry2 = SingletonHungry.getInstance();
        if (singletonHungry1 == singletonHungry2){
            System.out.println("俩个饿汉实例相同");
        }

        //懒汉模式测试
        SingletonFull singletonFull1 = SingletonFull.getInstance();
        SingletonFull singletonFull2 = SingletonFull.getInstance();
        if (singletonFull1 == singletonFull2){
            System.out.println("俩个懒汉实例相同");
        }
    }

    /**
     * 策略模式调用（练习代替if else,模拟vip打折）
     * @param vipName vip名称
     * @param price 原价
     * @return 打折后价格
     */
    @RequestMapping(value = "strategyTest", method = RequestMethod.GET)
    public double strategyTest(@RequestParam("vipName") String vipName, @RequestParam("price") double price) throws ClassNotFoundException
            , IllegalAccessException, InstantiationException {

        Vip vip = new Vip();
        //根据类名反射获取类的实例
        DiscountStrategy discountStrategy = (DiscountStrategy)Class.forName("com.sama.springbootdemo01.practice.design.strategy." + vipName + "DiscountStrategyImpl").newInstance();
        //使用当前的vip进行打折
        vip.setDiscountStrategy(discountStrategy);
        return vip.discount(price);
    }



    /**
     * 工厂模式调用(通过反射)
     */
    @RequestMapping(value = "factoryTest", method = RequestMethod.GET)
    public void factoryTest(@RequestParam("key") String key){
        DeskFactory deskFactory = new DeskFactory();
        Desk desk = deskFactory.getDestByKey(key);
        if (desk == null){
            System.out.println("无此项选择");
            return;
        }
        desk.material();
    }


    /**
     * 工厂模式调用(通过枚举)
     */
    @RequestMapping(value = "factoryTestByEnum", method = RequestMethod.GET)
    public void factoryTestByEnum(@RequestParam("enumNum") String enumNum){
        DeskFactory deskFactory = new DeskFactory();
        Desk desk = null;
        try {
            desk = deskFactory.getDestByEnum(DeskMaterialEnum.valueOf(enumNum));
        } catch (IllegalArgumentException e) {
            System.out.println("枚举转换失败");
            e.printStackTrace();
            return;
        }
        if (desk == null){
            System.out.println("无此项选择");
            return;
        }
        desk.material();
    }
}
