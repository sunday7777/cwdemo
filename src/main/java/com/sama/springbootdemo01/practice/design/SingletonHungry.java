package com.sama.springbootdemo01.practice.design;

/**
 * 设计模式：单例模式(饿汉模式，项目启动时就创建实例)
 * @author fjk
 * @date 2020年08月22日
 * @since jdk 1.8
 */
public class SingletonHungry {

    /**
     * 私有化构造方法，不允许外部直接创建
     */
    private SingletonHungry(){
    }

    /**
     * 创建唯一实例(私有静态)
     */
    private static SingletonHungry instance = new SingletonHungry();

    /**
     * 提供用于获取实例的方法（公有静态）
     */
    public static SingletonHungry getInstance(){
        return instance;
    }


}
