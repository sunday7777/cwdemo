package com.sama.springbootdemo01.practice.design.strategy;

/**
 * 设计模式：策略模式（模拟不同vip打折，代替if else）
 * @author fjk
 * @date 2020年08月22日
 * @since jdk 1.8
 */
public class Vip {


    /**
     * 打折策略
     */
    public DiscountStrategy discountStrategy;


    /**
     * 通过set方法来注入策略
     * @param discountStrategy
     */
    public void setDiscountStrategy(DiscountStrategy discountStrategy){
        this.discountStrategy = discountStrategy;
    }

    /**
     * 实现打折
     * @param price
     * @return
     */
    public double discount(double price){
        return discountStrategy.discount(price);
    }

}
