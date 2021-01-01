package com.ahastudio;

import com.ahastudio.components.Person;
import com.ahastudio.components.Player;
import com.ahastudio.components.Show;
import com.ahastudio.components.Singer;
import com.ahastudio.components.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

public class HelloWorld {
    public static void main(String... args) {
//        mainMethodInject();
//        mainMethodInjectUsingAnnotation();
//        mainMethodInjectUsingJavaConfig();
//        mainMethodReplacement();
//        mainBeanNaming();
//        mainBeanNamingUsingAnnotation();
//        mainBeanAliasUsingJavaConfig();
//        mainDependsOn();
//        mainDependsOnUsingAnnotation();
//        mainDependencyInjection();
//        mainAutowire();
//        mainAutowireUsingAnnotation();
//        mainAutowireUsingQualifier();
        mainInheritance();
    }

    private static void mainMethodInject() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "spring/app-context-lookup-method.xml");

        Stage abstractStage =
                context.getBean("abstract-lookup-stage", Stage.class);
        Stage standardStage =
                context.getBean("standard-lookup-stage", Stage.class);

        System.out.println("* Abstract Lookup Stage");
        displayStage(abstractStage);

        System.out.println();

        System.out.println("* Standard Lookup Stage");
        displayStage(standardStage);
    }

    private static void mainMethodInjectUsingAnnotation() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "spring/app-context-annotation.xml");

        Stage abstractStage =
                context.getBean("abstract-lookup-stage", Stage.class);
        Stage standardStage =
                context.getBean("standard-lookup-stage", Stage.class);

        System.out.println("* Abstract Lookup Stage");
        displayStage(abstractStage);

        System.out.println();

        System.out.println("* Standard Lookup Stage");
        displayStage(standardStage);
    }

    private static void mainMethodInjectUsingJavaConfig() {
        GenericApplicationContext context =
                new AnnotationConfigApplicationContext(
                        HelloWorldComponentScanConfiguration.class);

        Stage abstractStage =
                context.getBean("abstract-lookup-stage", Stage.class);
        Stage standardStage =
                context.getBean("standard-lookup-stage", Stage.class);

        System.out.println("* Abstract Lookup Stage");
        displayStage(abstractStage);

        System.out.println();

        System.out.println("* Standard Lookup Stage");
        displayStage(standardStage);
    }

    private static void displayStage(Stage stage) {
        System.out.println(stage.show(" <<!>>"));

        Singer singer1 = stage.getSinger();
        Singer singer2 = stage.getSinger();
        System.out.println("가수를 계속 얻으면 같을까? -> " + (singer1 == singer2));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("lookup!");
        IntStream.range(0, 1000000).forEach(i -> {
//        IntStream.iterate(0, (i) -> i + 1).limit(1000000).forEach(i -> {
            Singer singer = stage.getSinger();
            singer.sing("");
        });
        stopWatch.stop();
        System.out.println("걸마나 걸리나?: " + stopWatch.getTotalTimeMillis());
    }

    private static void mainMethodReplacement() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "spring/app-context-method-replacement.xml");

        Singer hackedSinger = context.getBean("hacked-singer", Singer.class);
        Singer standardSinger =
                context.getBean("standard-singer", Singer.class);

        System.out.println("* Method replacement");
        displaySinger(hackedSinger);

        System.out.println();

        System.out.println("* Standard");
        displaySinger(standardSinger);
    }

    private static void displaySinger(Singer singer) {
        System.out.println(singer.sing("<<!>>"));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        IntStream.range(0, 1000000).forEach(i -> {
            singer.sing("");
        });
        stopWatch.stop();
        System.out.println("걸마나 걸리나?: " + stopWatch.getTotalTimeMillis());
    }

    public static void mainBeanNaming() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "spring/app-context-string.xml");

        Map<String, String> beans = context.getBeansOfType(String.class);

        beans.entrySet().stream().forEach(item -> {
            System.out.println("ID: " + item.getKey());
            String[] aliases = context.getAliases(item.getKey());
            System.out.println("Alias: " + Arrays.toString(aliases));
        });

        System.out.println("------------");

        String s1 = context.getBean("john", String.class);
        String s2 = context.getBean("jon", String.class);
        String s3 = context.getBean("johnny", String.class);
        String s4 = context.getBean("jonathan", String.class);
        String s5 = context.getBean("jim", String.class);
        String s6 = context.getBean("ion", String.class);
        String s7 = context.getBean("string1", String.class);

        System.out.println(s1 == s2);
        System.out.println(s2 == s3);
        System.out.println(s3 == s4);
        System.out.println(s4 == s5);
        System.out.println(s5 == s6);
        System.out.println("이건 달라야 해! -> " + (s6 == s7));
    }

    public static void mainBeanNamingUsingAnnotation() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "spring/app-context-annotation.xml");

        Map<String, Singer> beans = context.getBeansOfType(Singer.class);

        beans.entrySet().stream().forEach(item -> {
            System.out.println("ID: " + item.getKey());
            String[] aliases = context.getAliases(item.getKey());
            System.out.println("Alias: " + Arrays.toString(aliases));
        });
    }

    public static void mainBeanAliasUsingJavaConfig() {
        GenericApplicationContext context =
                new AnnotationConfigApplicationContext(
                        HelloWorldConfiguration.class);

        Map<String, Singer> beans = context.getBeansOfType(Singer.class);

        beans.entrySet().stream().forEach(item -> {
            System.out.println("ID: " + item.getKey());
            String[] aliases = context.getAliases(item.getKey());
            System.out.println("Alias: " + Arrays.toString(aliases));
        });
    }

    public static void mainDependsOn() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring/app-context.xml");

        Player player = context.getBean("john-mayer", Player.class);

        System.out.println(player.play());
    }

    public static void mainDependsOnUsingAnnotation() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "spring/app-context-annotation.xml");

        Player player = context.getBean("johnMayer", Player.class);

        System.out.println(player.play());
    }

    public static void mainDependencyInjection() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring/app-context.xml");

        Stage stage = context.getBean("stage", Stage.class);

        System.out.println(stage.show("~~~"));
    }

    public static void mainAutowire() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "spring/app-context-autowire.xml");

        Stage stage = null;

        System.out.println("* Autowire by name");
        stage = context.getBean("stage-by-name", Stage.class);
        System.out.println(stage.show("~~~"));

        System.out.println("* Autowire by type");
        stage = context.getBean("stage-by-type", Stage.class);
        System.out.println(stage.show("~~~"));

        System.out.println("* Autowire with contructor");
        stage = context.getBean("stage-constructor", Stage.class);
        System.out.println(stage.show("~~~"));
    }

    public static void mainAutowireUsingAnnotation() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "spring/app-context-annotation.xml");

        Stage stage = context.getBean("standard-lookup-stage", Stage.class);
        System.out.println(stage.show("~~~"));
    }

    public static void mainAutowireUsingQualifier() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "spring/app-context-annotation.xml");

        Show show = context.getBean("show", Show.class);
        System.out.println(show.show());
    }

    public static void mainInheritance() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring/app-context.xml");

        Person person1 = context.getBean("superman", Person.class);
        Person person2 = context.getBean("batman", Person.class);

        System.out.println(person1);
        System.out.println(person2);
    }
}
