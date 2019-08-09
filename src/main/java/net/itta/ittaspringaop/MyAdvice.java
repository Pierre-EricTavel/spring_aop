/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.ittaspringaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;


public class MyAdvice {
    
    public Object autour(ProceedingJoinPoint jp) throws Throwable{
      System.out.println("Around advice sur " + jp.toShortString() );
      Object[] args = jp.getArgs();
      if(args.length>0){
         System.out.print("Arguments passed: " );
         for (int i = 0; i < args.length; i++) {
            System.out.print("arg "+(i+1)+": "+args[i]);
         }
      }
      Object result = jp.proceed(args);
      System.out.println("Returning " + result);
      return result;
    }
    
      public void avant( JoinPoint jp) throws Throwable{
        System.out.println("Before advice sur " + jp.toLongString());
    }
    
}
