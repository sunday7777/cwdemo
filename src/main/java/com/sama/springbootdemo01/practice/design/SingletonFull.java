package com.sama.springbootdemo01.practice.design;

/**
 * 设计模式：饱汉模式（第一次调用时创建）
 * @author fjk
 * @date 2020年08月22日
 * @since jdk 1.8
 */
public class SingletonFull {

    /**
     * 私有化构造方法
     */
    private SingletonFull(){
    }

    private static SingletonFull singletonFull;

    /**
     * 实例不存在时创建
     */
    public static SingletonFull getInstance(){
        if (singletonFull == null){
            singletonFull = new SingletonFull();
        }
        return singletonFull;
    }
}
