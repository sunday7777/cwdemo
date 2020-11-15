package com.sama.springbootdemo01.practice.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义一个类型校验注解，通过@Constraint(）来实现注解功能。
 * @Constraint(）注解可以指定自定义注解的处理逻辑类，如：@Constraint(validatedBy = AgrijCheckedIntValidator.class)
 * @author fjk
 * @date 2020年10月09日
 * @since jdk 1.8
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyCheckedIntValidator.class)
public @interface ConstraintAnnotation {

    String message() default "不满足自定义校验";

    /**
     * 字段的中文标注
     */
    String comment() default "";

    /**
     * 字段的长度 {最小长度，最大长度}
     * @return
     */
    int[] length() default { 0 , 0 };

    /**
     * 是否允许为空
     */
    boolean nullable() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
