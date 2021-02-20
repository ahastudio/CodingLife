package com.ahastudio;

import com.ahastudio.components.PropertyEditorBean;
import com.ahastudio.components.Publisher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.stream.Collectors;

public class HelloWorld {
    public static void main(String... args) throws IOException {
//        mainPropertyEditor();
//        mainI18nText();
//        mainEvent();
        mainResource();
    }

    private static void mainPropertyEditor() {
        GenericXmlApplicationContext context =
                new GenericXmlApplicationContext();
        context.load("spring/app-context.xml");
        context.refresh();

        PropertyEditorBean propertyEditorBean =
                context.getBean("property-editor", PropertyEditorBean.class);

        propertyEditorBean.print();
    }

    private static void mainI18nText() {
        GenericXmlApplicationContext context =
                new GenericXmlApplicationContext();
        context.load("spring/app-context.xml");
        context.refresh();

        System.out.println(context.getMessage("hello", null, Locale.ENGLISH));
        System.out.println(context.getMessage("hello", null, Locale.KOREAN));
    }

    private static void mainEvent() {
        GenericXmlApplicationContext context =
                new GenericXmlApplicationContext();
        context.load("spring/app-context.xml");
        context.refresh();

        Publisher publisher = context.getBean("publisher", Publisher.class);
        publisher.helpMe();
    }

    private static void mainResource() throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext();

        File file = File.createTempFile("test", "txt");
        file.deleteOnExit();

        Resource resource1 = context.getResource("file://" + file.getPath());
        display(resource1);

        // 먼저 파일을 준비합시다!
        // echo "Hello" > app/build/classes/java/main/test.txt
        Resource resource2 = context.getResource("classpath:test.txt");
        display(resource2);

        Resource resource3 = context.getResource("https://www.naver.com/");
        display(resource3);
    }

    private static void display(Resource resource) throws IOException {
        System.out.println("Class: " + resource.getClass());
        System.out.println("URL: " + resource.getURL());
        System.out.println("Content: " + resource.getURL().getContent());
        System.out.println("Real Content: " + contentAsString(resource));
        System.out.println();
    }

    private static String contentAsString(Resource resource) throws IOException {
        InputStream inputStream = resource.getInputStream();
        InputStreamReader inputStreamReader =
                new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        return reader.lines().collect(Collectors.joining("\n"));
    }
}
