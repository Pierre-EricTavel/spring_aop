/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.ittaspringaop;


public class PojoUn implements IPojo {
    
    @Override
    @MyAopAnnotation(activated = false)
    public void doIt(){
        if(true)
            throw new RuntimeException("C'est super chaud!");
        System.out.println("\tdo it de PojoUn");
    }
    
    @Override
    public int goTo( int i){
         System.out.println("\tgo to de PojoUn");
         return 10*i;
    }
    
}