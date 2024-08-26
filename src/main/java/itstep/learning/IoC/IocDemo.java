package itstep.learning.IoC;

import com.google.inject.Inject;
import itstep.learning.services.hash.HashService;

import javax.inject.Named;
import java.util.logging.Logger;

public class IocDemo {
    private final HashService DigestHashService;
    private final HashService SignatureHashService;
    private final String AppName;

    @Inject private Logger logger; // Інжекція через поле (не рекомендуєтся)

    @Inject
    public IocDemo(
            @Named("digest") HashService DigestHashService,        //
            @Named("signature") HashService SignatureHashService,  // Інжекція через коструктор (рекомендуєтся)
            @Named("appName") String AppName)                      //
    {
        this.DigestHashService = DigestHashService;
        this.SignatureHashService = SignatureHashService;
        this.AppName = AppName;
    }
    public void run(){
        System.out.println("----------------------" + AppName + "----------------------");
        System.out.println("Hash: " + DigestHashService.digest("123"));
        System.out.println("Signature: " + SignatureHashService.digest("123"));
        logger.info(AppName);
    }
}
