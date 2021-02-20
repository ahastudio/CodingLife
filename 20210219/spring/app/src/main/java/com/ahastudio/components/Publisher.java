package com.ahastudio.components;

import com.ahastudio.events.HelpEvent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Publisher implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void helpMe() {
        applicationContext.publishEvent(new HelpEvent(this));
    }

    public String getName() {
        return "간절한 객체";
    }
}
