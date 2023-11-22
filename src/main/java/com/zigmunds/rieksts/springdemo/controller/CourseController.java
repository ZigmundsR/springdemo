package com.zigmunds.rieksts.springdemo.controller;

import com.zigmunds.rieksts.springdemo.entity.Course;
import com.zigmunds.rieksts.springdemo.entity.Instructor;
import com.zigmunds.rieksts.springdemo.entity.Review;
import com.zigmunds.rieksts.springdemo.service.CourseService;
import com.zigmunds.rieksts.springdemo.service.InstructorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController extends BaseController{
    private CourseService courseService;

    private InstructorService instructorService;

    @Autowired
    public CourseController(CourseService courseService, InstructorService instructorService) {
        this.courseService = courseService;
        this.instructorService = instructorService;
    }

    @GetMapping("/list")
    public String listCourses(Model theModel){
        List<Course> courses = courseService.findAll();

        theModel.addAttribute("courses", courses);

        return "courses/list-courses";
    }

    @GetMapping("/register")
    public String registerCourse(Model theModel){
        Course course = new Course();

        theModel.addAttribute("course", course);

        List<Instructor> instructorList = instructorService.findAll();
        theModel.addAttribute("instructorList", instructorList);

        return "courses/course-form";
    }

    @PostMapping("/update")
    public String updateCourse(@RequestParam("courseId") int theId,
                             Model theModel) {
        Course course = courseService.findCourseById(theId);

        theModel.addAttribute("course", course);

        theModel.addAttribute("instructor", course.getInstructor());

        List<Instructor> instructorList = instructorService.findAll();

        theModel.addAttribute("instructorList", instructorList);

        return "courses/course-form";
    }
    @PostMapping("/save")
    public String saveCourse(@Valid @ModelAttribute("course") Course course,
                             BindingResult theBindingResult, Model theModel){
        if (theBindingResult.hasErrors()) {
            List<Instructor> instructorList = instructorService.findAll();

            theModel.addAttribute("instructorList", instructorList);

            return "courses/course-form";
        }

        courseService.save(course);

        return "redirect:/courses/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("courseId") int theId) {

        courseService.deleteCourseById(theId);

        return "redirect:/courses/list";
    }

    @GetMapping("/{courseId}/reviews")
    public String showReviews(@PathVariable int courseId, Model model){
        Course course = courseService.findCourseAndReviewsByCourseId(courseId);

        model.addAttribute("courseId", courseId);
        model.addAttribute("review", new Review());
        model.addAttribute("reviews", course.getReviews());

        return "courses/list-reviews";
    }

    @PostMapping("/{courseId}/reviews/addReview")
    public String addReview(@PathVariable int courseId, @ModelAttribute Review review){
        Course course = courseService.findCourseById(courseId);

        course.addReview(review);

        courseService.save(course);

        return "redirect:/courses/{courseId}/reviews";
    }

    @PostMapping("/{courseId}/reviews/delete")
    public String deleteReview(@PathVariable int courseId, @RequestParam("reviewId") int theId) {

        courseService.deleteReviewById(theId);

        return "redirect:/courses/{courseId}/reviews";
    }

    @GetMapping("/{courseId}/students")
    public String showStudents(@PathVariable int courseId, Model model){
        Course course = courseService.findCourseById(courseId);

        model.addAttribute("students", course.getStudents());

        return "courses/list-course-students";
    }
}
