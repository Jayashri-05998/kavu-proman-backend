package com.kavu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class EndpointLogger implements CommandLineRunner {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("============================================");
        System.out.println("🚀 All Available Endpoints in the Application:");
        System.out.println("============================================");

        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        mapping.getHandlerMethods().forEach((key, value) -> {
            System.out.println(key + " -> " + value.getMethod().getDeclaringClass().getSimpleName() 
                               + "#" + value.getMethod().getName());
        });

        System.out.println("============================================");
        System.out.println("✅ Endpoints listing completed.");
        System.out.println("============================================");
    }
}
