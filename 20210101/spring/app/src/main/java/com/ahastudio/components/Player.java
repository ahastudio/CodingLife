package com.ahastudio.components;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component("johnMayer")
@DependsOn("gopher")
public class Player implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    private ZGuitar guitar;

    public Player() {
        System.out.println("\n*** Create Player\n");
    }

    public String play() {
        guitar = applicationContext.getBean("gopher", ZGuitar.class);
        return guitar.sound();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }
}
