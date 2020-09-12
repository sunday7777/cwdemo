package com.sama.springbootdemo01.practice.design.strategy;

/**
 * 设计模式：策略模式（打折策略，模拟vip打折）
 * @author fjk
 * @date 2020年08月22日
 * @since jdk 1.8
 */
public interface DiscountStrategy {

    /**
     * 打折方法
     */
    double discount(double price);
}
