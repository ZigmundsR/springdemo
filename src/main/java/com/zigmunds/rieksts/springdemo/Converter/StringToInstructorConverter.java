package com.zigmunds.rieksts.springdemo.Converter;


import com.zigmunds.rieksts.springdemo.entity.Instructor;
import com.zigmunds.rieksts.springdemo.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class StringToInstructorConverter implements Converter<String, Instructor> {

    private InstructorService instructorService;

    @Autowired
    public StringToInstructorConverter(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @Override
    public Instructor convert(String id) {
        return instructorService.findInstructorById(Integer.parseInt(id));
    }
}
