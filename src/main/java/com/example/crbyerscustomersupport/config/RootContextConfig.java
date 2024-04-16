package com.example.crbyerscustomersupport.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = "com.example.crbyerscustomersupport.site",
        excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootContextConfig {
}