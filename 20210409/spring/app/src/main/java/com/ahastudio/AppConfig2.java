package com.ahastudio;

import com.ahastudio.components.GreetingMessageProvider;
import com.ahastudio.components.MessageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig2 {
    @Bean
    public MessageProvider messageProvider() {
        return new GreetingMessageProvider();
    }
}
