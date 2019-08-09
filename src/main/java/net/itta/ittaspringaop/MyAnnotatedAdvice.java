
package net.itta.ittaspringaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;


@Aspect
public class MyAnnotatedAdvice {
    
    @Pointcut("execution(* net.itta.ittaspringaop.*.*(..))")
    private void selectAll(){}
    
    //@Pointcut("execution(* net.itta.ittaspringaop.PojoUn.goTo())")
    @Pointcut("execution(* goTo())")
   // @Pointcut("execution(* goTo(..)) && within(net.itta.ittaspringaop.*)")
    private void selectOnlyGoto(){}
    
    //@Pointcut("@annotation(net.itta.ittaspringaop.MyAopAnnotation) && execution(* doIt())") 
    //et si on récupère les valeurs de l'annotation
    @Pointcut("@annotation(myAopAnnotation) && execution(* doIt())")
    public void myAnnotation(MyAopAnnotation myAopAnnotation){
    }
    
    @Before("selectAll()")
    public void beforeAdvice(){
        System.out.println("Before in MyAnnotatedAdvice");
    }
    @After(value = "selectOnlyGoto()")
    public void afterAdvice(){
        System.out.println("After goto in MyAnnotatedAdvice");
    }

    @AfterReturning(pointcut = "selectOnlyGoto()", returning = "returnVal")
    public void AfterReturningAdvice(JoinPoint jp, Object returnVal){
       MethodInvocationProceedingJoinPoint mijp = (MethodInvocationProceedingJoinPoint)jp;
       System.out.println("After Returning "+mijp.getSignature().getName()+ " return "+ (returnVal==null ?"void":returnVal));
    }
    
    @AfterThrowing(pointcut = "selectAll()", throwing = "ex")
    public void AfterThrowingAdvice(JoinPoint jp, Throwable ex){
       MethodInvocationProceedingJoinPoint mijp = (MethodInvocationProceedingJoinPoint)jp;
       System.out.println("After Throwing "+mijp.getSignature().getName()+ " threw "+ ex.getMessage());
    }
    
    @Around("selectAll()")
    public Object AroundAdvice(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("Around advice sur " + jp.toShortString() );
        Object[] args = jp.getArgs();
        if(args.length>0){
           System.out.print("Arguments passed: " );
           for (int i = 0; i < args.length; i++) {
              System.out.println("arg "+(i+1)+": "+args[i]);
           }
        }
        Object result = jp.proceed(args);
        System.out.println("Around advice Returning " + result);
        return result;
    }
     
   //@Before("@annotation(MyAopAnnotation)")
    @Before("myAnnotation(myAopAnnotation)")
    public void beforeAnnotatedAdvice(MyAopAnnotation myAopAnnotation){
        System.out.println("Before in @MyAopAnnotation annotated Method "+myAopAnnotation.activated());
    }
    
}
