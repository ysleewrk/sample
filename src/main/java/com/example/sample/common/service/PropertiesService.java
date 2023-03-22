package com.example.sample.common.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PropertiesService {

    private Environment environment;

    public PropertiesService(Environment environment) {
        this.environment = environment;
    }

    public String getProperty(String key) {
    	return environment.getProperty(key);
    }


}
