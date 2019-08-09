package net.itta.ittaspringaop;

import java.lang.reflect.Method;
import org.aopalliance.intercept.*;
import org.springframework.aop.*;
import org.springframework.aop.framework.*;
import org.springframework.aop.support.*;


public class PrincipaleManuel {
    public static void main(String[] args) {
        
        PojoUn pu = new PojoUn();
        
        MethodInterceptor advice = invocation -> {//Around
            System.out.println("before " +invocation.getMethod().getName());
            Object returnvalue=  invocation.proceed();
            System.out.println("after "+invocation.getThis().getClass()+ "return "+ (returnvalue==null ?"void":returnvalue));
            return returnvalue;
        };
        
        Pointcut pc = new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
               return "goTo".equals(method.getName());
            }
            @Override
            public ClassFilter getClassFilter(){
                return c->c==PojoUn.class;
            }
        };
         AfterReturningAdvice after = (Object returnvalue, Method method, Object[] argvs, Object target) -> {
            System.out.println("After Returning "+method.getName());
        };
        Advisor advisor = new DefaultPointcutAdvisor(pc, advice);
        
        ProxyFactory factory1 = new ProxyFactory();
        factory1.addAdvisor(0,advisor);
        factory1.addAdvice(1, /* after ou bien..*/(AfterReturningAdvice)(returnvalue, method,argvs, target) -> {
            System.out.println("After Returning "+method.getName()+ " return "+ (returnvalue==null ?"void":returnvalue));
        });
        factory1.addAdvice( new MyThrowsAdvice());
        factory1.setTarget(pu);
        
        ProxyFactory factory2 = new ProxyFactory();
        factory2.addAdvisor(advisor);
        factory2.setTarget(new PojoDeux());
        
        
        PojoUn pup1 = (PojoUn)factory1.getProxy();
        try {
            pup1.doIt();
        } catch (Exception e) {
        }
        pup1.goTo(5);
        
        PojoDeux pup2 = (PojoDeux)factory2.getProxy();
        pup2.doIt();
        pup2.goTo();

    }    

}


class PojoDeux {
    
    void doIt(){
        System.out.println("\tdo it de Pojo Deux");
    }
    
    int goTo(){
         System.out.println("\tgo to de Pojo Deux");
         return 20;
    }
    
}


