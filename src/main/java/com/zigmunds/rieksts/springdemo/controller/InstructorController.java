package com.zigmunds.rieksts.springdemo.controller;

import com.zigmunds.rieksts.springdemo.entity.Instructor;
import com.zigmunds.rieksts.springdemo.service.InstructorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/instructors")
public class InstructorController extends BaseController {
    private InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/list")
    public String listInstructor(Model theModel) {

        List<Instructor> instructors = instructorService.findAll();
        theModel.addAttribute("instructors", instructors);
        return "instructors/list-instructors";
    }

    @GetMapping("/register")
    public String registerInstructor(Model theModel) {
        Instructor instructor = new Instructor();

        theModel.addAttribute("instructor", instructor);

        return "instructors/instructor-form";
    }

    @PostMapping("/update")
    public String updateInstructor(@RequestParam("instructorId") int theId,
                                   Model theModel) {
        Instructor instructor = instructorService.findInstructorById(theId);

        theModel.addAttribute("instructor", instructor);

        return "instructors/instructor-form";
    }

    @PostMapping("/save")
    public String saveInstructor(@Valid @ModelAttribute("instructor") Instructor instructor,
                                 BindingResult theBindingResult, Model theModel) {

        if (theBindingResult.hasErrors()) {
            return "instructors/instructor-form";
        }

        instructorService.save(instructor);

        // use a redirect to prevent duplicate submissions
        return "redirect:/instructors/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("instructorId") int theId) {

        instructorService.deleteInstructorById(theId);

        return "redirect:/instructors/list";
    }
}
