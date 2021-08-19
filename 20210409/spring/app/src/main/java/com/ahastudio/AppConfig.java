package com.ahastudio;

import com.ahastudio.components.MessageProvider;
import com.ahastudio.components.MessageRenderer;
import com.ahastudio.components.StandardOutMessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = "classpath:message.properties")
//@Import(BaseAppConfig.class)
@ImportResource(value = "classpath:spring/app-context-01.xml")
public class AppConfig {
    @Autowired
    private Environment env;

    @Autowired
    private MessageProvider messageProvider;

    @Bean(name = "messageRenderer")
//    @Scope(value = "prototype")
//    @DependsOn(value = "messageProvider")
    public MessageRenderer messageRenderer() {
        return new StandardOutMessageRenderer(messageProvider);
    }

//    @Bean
//    public MessageProvider messageProvider() {
//        return new GreetingMessageProvider(env.getProperty("name"));
//    }
}
