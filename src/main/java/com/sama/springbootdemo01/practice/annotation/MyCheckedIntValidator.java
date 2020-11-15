package com.sama.springbootdemo01.practice.annotation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义校验测试
 * 需要继承ConstraintValidator<注解名，校验的Object>
 * 有两个方法initialize()和isValid()
 * 方法initialize()不需要一定有，使用可用来对注解定义的参数进行初始化给isValid()方法进行使用
 * 方法isValid()必须实现，是校验逻辑所在的位置
 *
 * @author fjk
 * @date 2020年10月13日
 * @since jdk 1.8
 */
public class MyCheckedIntValidator implements ConstraintValidator<ConstraintAnnotation, String> {

    private String comment;

    private int[] length;

    private boolean nullable;

    @Override
    public void initialize(ConstraintAnnotation myAnnotation) {
        this.comment = myAnnotation.comment();
        this.length = myAnnotation.length();
        this.nullable = myAnnotation.nullable();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (!nullable && StringUtils.isEmpty(value)){
            context.buildConstraintViolationWithTemplate(comment + "不允许为空").addConstraintViolation();
            return false;
        }

        if (value != null && length[0] != 0 && value.length() < length[0]){
            context.buildConstraintViolationWithTemplate(comment + "不能小于" + length[0])
                    .addConstraintViolation();
            return false;
        }

        if (value != null && length.length == 2 && length[1] != 0 && value.length() > length[1]){
            context.buildConstraintViolationWithTemplate(comment + "不能大于于" + length[1])
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
