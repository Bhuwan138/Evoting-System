
package com.evoting.evotingsystem.Handler;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryHandler {
    private static SessionFactory factory;
    public static SessionFactory getFactory(){
        try{
            if(factory==null){
                factory = new Configuration().configure().buildSessionFactory();         
            }  
        }catch(HibernateException e){
            System.out.println("Error in Factory Handler - com.evoting.evotingsystem.Handler");
            e.getMessage();
        }
        return factory;
    }   
}