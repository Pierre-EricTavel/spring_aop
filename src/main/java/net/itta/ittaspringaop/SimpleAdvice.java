/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.ittaspringaop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


public class SimpleAdvice implements MethodInterceptor{

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("before " + invocation.getMethod().getName());
        Object returnvalue = invocation.proceed();
        System.out.println("after " + invocation.getThis().getClass() + "return " + (returnvalue == null ? "void" : returnvalue));
        return returnvalue;
    }
    
}