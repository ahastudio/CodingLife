package com.ahastudio;

import com.ahastudio.components.MessageProvider;
import com.ahastudio.components.MessageRenderer;
import com.ahastudio.components.Oracle;
import com.ahastudio.components.Person;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class HelloWorld {
    public static void main(String... args) {
        mainUsingFactory();
        mainUsingSpringWithXmlConfig();
        mainUsingSpringWithJavaConfig();
        mainUsingContextualizedDependencyLookup();
        mainUsingXmlConfigWithBeanFactory();
        mainUsingApplicaionContext();
        mainUsingApplicaionContextWithXmlConfigAnnotation();
        mainUsingApplicaionContextWithJavaConfigAnnotation();
        mainUsingApplicaionContextWithXmlConfigValue();
        mainUsingApplicaionContextWithXmlConfigAnnotationValue();
    }

    private static void mainUsingFactory() {
        MessageSupportFactory factory = MessageSupportFactory.getInsatance();

        MessageProvider messageProvider = factory.getMessageProvider();

        MessageRenderer renderer = factory.getMessageRenderer();
        renderer.setMessageProvider(messageProvider);

        renderer.render();
    }

    private static void mainUsingSpringWithXmlConfig() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring/app-context.xml");

        MessageRenderer renderer =
                context.getBean("renderer", MessageRenderer.class);

        renderer.render();
    }

    private static void mainUsingSpringWithJavaConfig() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(
                        HelloWorldConfiguration.class);

        MessageRenderer renderer =
                context.getBean("renderer", MessageRenderer.class);

        renderer.render();
    }

    private static void mainUsingContextualizedDependencyLookup() {
        Container container = new LookupContainer();

        MessageRenderer renderer =
                (MessageRenderer) container.getDependency("message-renderer");

        System.out.println(renderer);

        renderer.render();
    }

    private static void mainUsingXmlConfigWithBeanFactory() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader =
                new XmlBeanDefinitionReader(factory);
        beanDefinitionReader.loadBeanDefinitions(
                new ClassPathResource("spring/xml-bean-factory-config.xml"));

        Oracle oracle = factory.getBean("oracle", Oracle.class);
        System.out.println(oracle.defineMeaningOfLife());
    }

    private static void mainUsingApplicaionContext() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "spring/xml-bean-factory-config.xml");

        Oracle oracle = context.getBean("oracle", Oracle.class);
        System.out.println(oracle.defineMeaningOfLife());
    }

    private static void mainUsingApplicaionContextWithXmlConfigAnnotation() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "spring/app-context-annotation.xml");

        Oracle oracle = context.getBean("oracle", Oracle.class);
        System.out.println(oracle.defineMeaningOfLife());
    }

    private static void mainUsingApplicaionContextWithJavaConfigAnnotation() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(
                        HelloWorldComponentScanConfiguration.class);

        Oracle oracle = context.getBean("oracle", Oracle.class);
        System.out.println(oracle.defineMeaningOfLife());

        MessageRenderer renderer =
                context.getBean("renderer", MessageRenderer.class);
        renderer.render();
    }

    private static void mainUsingApplicaionContextWithXmlConfigValue() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "spring/app-context-value.xml");

        Person person = context.getBean("person", Person.class);
        System.out.println(person);
    }

    private static void mainUsingApplicaionContextWithXmlConfigSpelValue() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "spring/app-context-spel-value.xml");

        Person person = context.getBean("person", Person.class);
        System.out.println(person);
    }

    private static void mainUsingApplicaionContextWithXmlConfigAnnotationValue() {
        ApplicationContext context =
                new GenericXmlApplicationContext(
                        "spring/app-context-annotation.xml");

        Person person = context.getBean("person", Person.class);
        System.out.println(person);
    }
}
