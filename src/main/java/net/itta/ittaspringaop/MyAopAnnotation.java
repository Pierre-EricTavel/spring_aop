
package net.itta.ittaspringaop;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAopAnnotation {
    boolean activated() default false;
}
