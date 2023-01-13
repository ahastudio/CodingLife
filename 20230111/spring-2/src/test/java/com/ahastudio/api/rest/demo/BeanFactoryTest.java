package com.ahastudio.api.rest.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import com.ahastudio.api.rest.demo.application.GetPostDtoListService;
import com.ahastudio.api.rest.demo.application.PostService;
import com.ahastudio.api.rest.demo.controllers.PostController;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class BeanFactoryTest {
    @Test
    @DisplayName("Spring IoC Container를 통해 ObjectMapper 객체 얻기 테스트")
    void getObjectMapper() {
        DefaultListableBeanFactory beanFactory =
                new DefaultListableBeanFactory();

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(ObjectMapper.class);

        beanFactory.registerBeanDefinition("objectMapper", beanDefinition);

        ObjectMapper objectMapper =
                beanFactory.getBean("objectMapper", ObjectMapper.class);

        assertThat(objectMapper).isNotNull();
    }

    @Test
    @DisplayName("Spring IoC Container를 통해 PostController 객체 얻기 테스트")
    void getPostController() {
        GetPostDtoListService getPostDtoListService =
                new GetPostDtoListService();
        PostService postService = new PostService();

        DefaultListableBeanFactory beanFactory =
                new DefaultListableBeanFactory();

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(PostController.class);

        ConstructorArgumentValues constructorArgs =
                new ConstructorArgumentValues();
        constructorArgs.addIndexedArgumentValue(0, getPostDtoListService);
        constructorArgs.addIndexedArgumentValue(1, postService);

        beanDefinition.setConstructorArgumentValues(constructorArgs);

        beanFactory.registerBeanDefinition("postContoller", beanDefinition);

        PostController postController =
                beanFactory.getBean("postContoller", PostController.class);

        assertThat(postController).isNotNull();
    }
}
