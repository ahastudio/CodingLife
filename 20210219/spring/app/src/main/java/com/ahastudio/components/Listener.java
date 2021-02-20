package com.ahastudio.components;

import com.ahastudio.events.HelpEvent;
import org.springframework.context.ApplicationListener;

public class Listener implements ApplicationListener<HelpEvent> {
    @Override
    public void onApplicationEvent(HelpEvent event) {
        Publisher publisher = (Publisher) event.getSource();
        System.out.println(publisher.getName() + "의 간절한 도움 요청!");
        System.out.println("아~ 도움이 필요하구나...");
    }
}
