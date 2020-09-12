package com.sama.springbootdemo01.practice.design.strategy;

/**
 * 设计模式：策略模式（vip1实现类,vip打9折）
 * @author fjk
 * @date 2020年08月22日
 * @since jdk 1.8
 */
public class Vip1DiscountStrategyImpl implements DiscountStrategy {

    @Override
    public double discount(double price) {
        System.out.println("vip1打9折");
        return price * 0.9;
    }
}
