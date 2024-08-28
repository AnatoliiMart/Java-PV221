package itstep.learning.IoC;

import com.google.inject.Inject;
import itstep.learning.services.generators.Generator;
import itstep.learning.services.hash.HashService;

import javax.inject.Named;
import java.util.logging.Logger;

public class IocDemo {
    private final HashService digestHashService;
    private final HashService signatureHashService;
    private final String appName;
    private final Generator fileNameGenerator;
    private final Generator saltGenerator;
    private final Generator otpGenerator;
    private final Generator passwordGenerator;


    @Inject private Logger logger; // Інжекція через поле (не рекомендуєтся)

    @Inject
    public IocDemo(
            @Named("digest") HashService DigestHashService,        //
            @Named("signature") HashService SignatureHashService,  // Інжекція через коструктор (рекомендуєтся)
            @Named("appName") String AppName,
            @Named("fileNameGenerator") Generator FileNameGenerator,
            @Named("saltGenerator") Generator SaltGenerator,
            @Named("otpGenerator") Generator OtpGenerator,
            @Named("passwordGenerator") Generator PasswordGenerator
            )
    {
        this.digestHashService = DigestHashService;
        this.signatureHashService = SignatureHashService;
        this.appName = AppName;
        this.fileNameGenerator = FileNameGenerator;
        this.saltGenerator = SaltGenerator;
        this.otpGenerator = OtpGenerator;
        this.passwordGenerator = PasswordGenerator;
    }
    public void run(){
        System.out.println("----------------------" + appName + "----------------------");
        System.out.println("Hash: " + digestHashService.digest("123"));
        System.out.println("Signature: " + signatureHashService.digest("123"));
        System.out.println("----------------------GENERATORS----------------------");
        System.out.println("File name: " + fileNameGenerator.generate(10));
        System.out.println("Salt: " + saltGenerator.generate(10));
        System.out.println("OTP: " + otpGenerator.generate(10));
        System.out.println("Password: " + passwordGenerator.generate(10));
        logger.info(appName);
    }
}
