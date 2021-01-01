package com.ahastudio;

import com.ahastudio.components.BookwarmOracle;
import com.ahastudio.components.HelloWorldMessageProvider;
import com.ahastudio.components.MessageProvider;
import com.ahastudio.components.MessageRenderer;
import com.ahastudio.components.Oracle;
import com.ahastudio.components.StandardOutMessageRenderer;
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

    @Bean
    Oracle oracle() {
        return new BookwarmOracle();
    }
}
