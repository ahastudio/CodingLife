package com.ahastudio;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorld {
    public static void main(String... args) {
//        MessageSupportFactory factory = MessageSupportFactory.getInsatance();
//        MessageProvider messageProvider = factory.getMessageProvider();
//        MessageRenderer renderer = factory.getMessageRenderer();
//        renderer.setMessageProvider(messageProvider);

        ApplicationContext context =
//                new ClassPathXmlApplicationContext("spring/app-context.xml");
                new AnnotationConfigApplicationContext(
                        HelloWorldConfiguration.class);

        MessageRenderer renderer =
                context.getBean("renderer", MessageRenderer.class);

        renderer.render();
    }
}
