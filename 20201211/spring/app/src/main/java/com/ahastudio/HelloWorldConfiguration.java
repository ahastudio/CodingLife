package com.ahastudio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {
    @Bean
    MessageRenderer renderer() {
        MessageRenderer renderer = new StandardOutMessageRenderer();
        renderer.setMessageProvider(provider());
        return renderer;
    }

    @Bean
    MessageProvider provider() {
        return new HelloWorldMessageProvider();
//        return new WakeUpMessageProvider();
    }
}
