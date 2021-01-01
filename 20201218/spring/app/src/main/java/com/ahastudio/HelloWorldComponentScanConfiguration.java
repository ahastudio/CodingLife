package com.ahastudio;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = {"com.ahastudio.components"}
//        excludeFilters = @ComponentScan.Filter(
//                type = FilterType.ASSIGNABLE_TYPE,
//                value = HelloWorldConfiguration.class
//        )
)
public class HelloWorldComponentScanConfiguration {
}
