package com.sama.springbootdemo01.practice.design.factory;

/**
 * 设计模式：工厂模式（实战：桌子工厂）
 * @author fjk
 * @date 2020年08月23日
 * @since jdk 1.8
 */
public class DeskFactory {

    /**
     * 通过反射方式获取桌子对象
     * @param key
     * @return
     */
    public Desk getDestByKey(String key){
        try {
            Desk desk = (Desk) Class.forName("com.sama.springbootdemo01.practice.design.factory." + key + "DeskImpl").newInstance();
            return desk;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过枚举方式获取桌子对象
     */
    public Desk getDestByEnum(DeskMaterialEnum material){

        switch (material){
            case PLASTIC:
                return new PlasticDeskImpl();
            case WOODINESS:
                return new WoodinessDeskImpl();
            default:
                return null;
        }
    }
}
