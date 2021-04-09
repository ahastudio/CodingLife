package com.ahastudio;

import com.ahastudio.components.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorld {
    public static void main(String... args) {
//        mainJavaConfig();
        mainXmlConfigWithJavaConfig();
    }

    private static void mainJavaConfig() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        MessageRenderer renderer =
                context.getBean("messageRenderer", MessageRenderer.class);
        renderer.render();
    }

    private static void mainXmlConfigWithJavaConfig() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "classpath:spring/app-context-02.xml");
        MessageRenderer renderer =
                context.getBean("messageRenderer", MessageRenderer.class);
        renderer.render();
    }
}
