package itstep.learning;

import com.google.inject.Guice;
import itstep.learning.IoC.IocDemo;
import itstep.learning.IoC.ServicesModule;
import itstep.learning.oop.Shop;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        Guice
//                .createInjector(new ServicesModule()) //configuration
//                .getInstance(IocDemo.class)           // resolve
//                .run();                               //run
//
        new Shop().run();
    }
}
