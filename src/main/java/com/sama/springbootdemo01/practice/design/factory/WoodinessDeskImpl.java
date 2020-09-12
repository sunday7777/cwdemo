package com.sama.springbootdemo01.practice.design.factory;

/**
 * 设计模式：工厂模式（实战：塑料桌子）
 * @author fjk
 * @date 2020年08月23日
 * @since jdk 1.8
 */
public class WoodinessDeskImpl implements Desk {

    @Override
    public void material(){
        System.out.println("您当前选择的是木头桌子");
    }
}
