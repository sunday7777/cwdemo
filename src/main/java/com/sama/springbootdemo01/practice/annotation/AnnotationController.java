package com.sama.springbootdemo01.practice.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 自定义注解测试Controller
 * @author fjk
 * @date 2020年10月09日
 * @since jdk 1.8
 */
@RestController
@RequestMapping("/annotation")
public class AnnotationController {

    @Autowired
    private AnnotationService annotationService;

    @RequestMapping(value = "save", method = RequestMethod.GET)
    public void save(){
        annotationService.save();
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public void delete(){
        annotationService.delete();
    }

    @RequestMapping(value = "annotationTest", method = RequestMethod.POST)
    public void annotationTest(@RequestBody @Validated User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldErrors().get(0).getDefaultMessage());
            return;
        }
        System.out.println("annotationTest");
    }
}
