package itstep.learning;

import com.google.inject.Guice;
import itstep.learning.IoC.DbModule;
import itstep.learning.IoC.IocDemo;
import itstep.learning.IoC.ServicesModule;
import itstep.learning.async.AsyncDemo;
import itstep.learning.db.AppLogger;
import itstep.learning.db.DbDemo;
import itstep.learning.fs.ConfigWriter;
import itstep.learning.fs.FileDemo;
import itstep.learning.oop.Shop;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        if (args.length > 0 && "--journal".equals(args[0])) {
            Guice.createInjector(
                    new ServicesModule(), new DbModule())               //configuration
                    .getInstance(DbDemo.class)            // resolve
                    .run();                               //run
        }
        else{
            AppLogger logger = new AppLogger();
            logger.logStart();
            System.out.println("Application started.");
        }
    }
}
