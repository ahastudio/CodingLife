package com.ahastudio;

import com.ahastudio.components.MessageDigestFactoryBean;
import com.ahastudio.components.MessageDigester;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.security.MessageDigest;

public class HelloWorld {
    public static void main(String... args) {
//        mainFactoryBean();
//        mainFactoryBeanJavaConfig();
//        mainAccessFactoryBean();
        mainFactoryBeanAndMethod();
    }

    private static void mainFactoryBean() {
        GenericXmlApplicationContext context =
                new GenericXmlApplicationContext();
        context.load("spring/app-context.xml");
        context.refresh();

        MessageDigester messageDiester =
                context.getBean("message-digester", MessageDigester.class);

        System.out.println(messageDiester.digest("test"));
    }

    private static void mainFactoryBeanJavaConfig() {
        GenericApplicationContext context =
                new AnnotationConfigApplicationContext(
                        HelloWorldConfiguration.class);

        MessageDigester messageDiester =
                context.getBean("message-digester", MessageDigester.class);

        System.out.println(messageDiester.digest("test"));
    }

    private static void mainAccessFactoryBean() {
        GenericXmlApplicationContext context =
                new GenericXmlApplicationContext();
        context.load("spring/app-context.xml");
        context.refresh();

        MessageDigestFactoryBean messageDigestFactoryBean =
                context.getBean("&message-digest-md5",
                        MessageDigestFactoryBean.class);

        try {
            MessageDigest messageDigest = messageDigestFactoryBean.getObject();
            byte[] digest = messageDigest.digest("Hello, world!".getBytes());
            System.out.println(digest);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private static void mainFactoryBeanAndMethod() {
        GenericXmlApplicationContext context =
                new GenericXmlApplicationContext();
        context.load("spring/app-context.xml");
        context.refresh();

        MessageDigester messageDiester =
                context.getBean("message-digester2", MessageDigester.class);

        System.out.println(messageDiester.digest("test"));
    }
}
