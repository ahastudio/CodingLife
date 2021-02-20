package com.ahastudio.events;

import org.springframework.context.ApplicationEvent;

public class HelpEvent extends ApplicationEvent {
    public HelpEvent(Object source) {
        super(source);
    }
}
