package com.ahastudio;

import com.ahastudio.components.MessageDigestFactoryBean;
import com.ahastudio.components.MessageDigester;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.MessageDigest;

@Configuration
public class HelloWorldConfiguration {
    @Bean
    public MessageDigestFactoryBean messageDigestMD5() {
        return new MessageDigestFactoryBean();
    }

    @Bean
    public MessageDigestFactoryBean messageDigestSha1() {
        MessageDigestFactoryBean messageDigestFactoryBean =
                new MessageDigestFactoryBean();
        messageDigestFactoryBean.setAlgorithmName("SHA1");
        return messageDigestFactoryBean;
    }

    @Bean
    public MessageDigestFactoryBean messageDigestSha256() {
        MessageDigestFactoryBean messageDigestFactoryBean =
                new MessageDigestFactoryBean();
        messageDigestFactoryBean.setAlgorithmName("SHA256");
        return messageDigestFactoryBean;
    }

    @Bean(name = "message-digester")
    public MessageDigester messageDigester() throws Exception {
        MessageDigest messageDigest1 = messageDigestMD5().getObject();
        MessageDigest messageDigest2 = messageDigestSha256().getObject();
        return new MessageDigester(messageDigest1, messageDigest2);
    }
}
