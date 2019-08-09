/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.ittaspringaop;



import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Administrator
 */
public class Principale {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans_1.xml");
        PojoUn pu = (PojoUn) context.getBean("pojo1");
        
        pu.goTo(5);
        try {
            pu.doIt();
        } catch (Exception e) {
        }

    }

    static void throughXml() throws BeansException {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        IPojo pu = (IPojo) context.getBean("pojo1");
        
        pu.goTo(6);
        try {
            pu.doIt();
        } catch (Exception e) {
        }
    }

    static void throughProxy() throws BeansException {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        PojoUn pu = (PojoUn) context.getBean("pojo1proxy");
        try {
            pu.doIt();
        } catch (Exception e) {
        }
        pu.goTo(2);
    }

}
