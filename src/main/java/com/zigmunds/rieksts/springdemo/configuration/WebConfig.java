package com.zigmunds.rieksts.springdemo.configuration;

import com.zigmunds.rieksts.springdemo.Converter.StringToInstructorConverter;
import com.zigmunds.rieksts.springdemo.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private InstructorService instructorService;

    @Autowired
    public WebConfig(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToInstructorConverter(instructorService));
    }

}
