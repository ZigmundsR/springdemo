package com.zigmunds.rieksts.springdemo.controller;

import com.zigmunds.rieksts.springdemo.entity.Course;
import com.zigmunds.rieksts.springdemo.entity.Student;
import com.zigmunds.rieksts.springdemo.service.CourseService;
import com.zigmunds.rieksts.springdemo.service.StudentService;
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
@RequestMapping("/students")
public class StudentController extends BaseController {

    private StudentService studentService;

    private CourseService courseService;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping("/list")
    public String listStudents(Model theModel){
        List<Student> students = studentService.findAll();

        theModel.addAttribute("students", students);

        return "students/list-students";
    }

    @GetMapping("/register")
    public String registerStudent(Model theModel){
        Student student = new Student();

        theModel.addAttribute("student", student);

        return "students/student-form";
    }

    @PostMapping("/update")
    public String updateStudent(@RequestParam("studentsId") int theId,
                               Model theModel) {
        Student student = studentService.findStudentById(theId);

        theModel.addAttribute("student", student);

        return "students/student-form";
    }

    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student,
                             BindingResult theBindingResult, Model theModel){
        if (theBindingResult.hasErrors()) {

            return "students/student-form";
        }

        studentService.save(student);

        return "redirect:/students/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("studentsId") int theId) {

        studentService.deleteStudentById(theId);

        return "redirect:/students/list";
    }

    @GetMapping("/{studentId}/courses")
    public String listStudentCourses(@PathVariable int studentId, Model theModel) {

        Student student = studentService.findStudentAndCoursesByCourseId(studentId);

        theModel.addAttribute("student", student);

        List<Course> courseList = courseService.findAll();

        theModel.addAttribute("courseList", courseList);

        theModel.addAttribute("course", new Course());

        return "students/list-student-courses";
    }

    @PostMapping("/{studentId}/courses/addCourse")
    public String addStudentCourse(@PathVariable int studentId, @ModelAttribute Course course){

        Student student = studentService.findStudentById(studentId);

        Course selectedCourse = courseService.findCourseById(course.getId());

        student.addCourse(selectedCourse);

        studentService.save(student);

        return "redirect:/students/{studentId}/courses";
    }

    @PostMapping("/{studentId}/courses/removeCourse")
    public String removeStudentCourse(@PathVariable int studentId, @RequestParam("courseId") int theId){

        Student student = studentService.findStudentById(studentId);

        Course course = courseService.findCourseById(theId);

        student.removeCourse(course);

        studentService.save(student);

        return "redirect:/students/{studentId}/courses";
    }

}
