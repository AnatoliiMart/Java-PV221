package itstep.learning.IoC;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import itstep.learning.services.generators.*;
import itstep.learning.services.hash.HashService;
import itstep.learning.services.hash.Md5HashService;
import itstep.learning.services.hash.ShaHashService;

public class ServicesModule extends AbstractModule {
    @Override
    protected void configure() {
        //Lazy
        bind(HashService.class)
                .annotatedWith(Names.named("digest"))
                .to(Md5HashService.class);
        bind(HashService.class)
                .annotatedWith(Names.named("signature"))
                .to(ShaHashService.class);
        bind(String.class)
                .annotatedWith(Names.named("appName"))
                .toInstance("Java-PV221");


        bind(Generator.class)
                .annotatedWith(Names.named("fileNameGenerator"))
                .to(FileNameGenerator.class);
        bind(Generator.class)
                .annotatedWith(Names.named("saltGenerator"))
                .to(SaltGenerator.class);
        bind(Generator.class)
                .annotatedWith(Names.named("otpGenerator"))
                .to(OTPGenerator.class);
        bind(Generator.class)
                .annotatedWith(Names.named("passwordGenerator"))
                .to(PasswordGenerator.class);

        //Eager
        //bind(HashService.class).toInstance(new Md5HashService());
    }
}
/*
Модуль реєстрації залежностей (служб)
 */