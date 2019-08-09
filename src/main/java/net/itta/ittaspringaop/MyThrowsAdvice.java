/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.ittaspringaop;

import java.lang.reflect.Method;
import org.springframework.aop.ThrowsAdvice;


public class MyThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex){
        System.out.println(ex.getMessage()+ " dans "+method.getName() + " de "+target.getClass().getSimpleName());
    }
        
}