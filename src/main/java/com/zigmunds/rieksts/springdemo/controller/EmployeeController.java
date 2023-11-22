package com.zigmunds.rieksts.springdemo.controller;

import com.zigmunds.rieksts.springdemo.entity.Employee;
import com.zigmunds.rieksts.springdemo.service.EmployeeService;
import com.zigmunds.rieksts.springdemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController extends BaseController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel,
                                @RequestParam(name = "search", required = false) String search) {
        List<Employee> theEmployees;
        if (search == null || search.isEmpty()){
            theEmployees = employeeService.findAll();
        }
        else{
            theEmployees = employeeService.findAll(search);
        }

        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    @PostMapping("/update")
    public String updateEmployee(@RequestParam("employeeId") int theId,
                                    Model theModel) {

        Employee theEmployee = employeeService.findById(theId);

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(
            @Valid @ModelAttribute("employee") Employee theEmployee,
            BindingResult theBindingResult) {

        if (theBindingResult.hasErrors()) {
            return "employees/employee-form";
        }

        employeeService.save(theEmployee);

        // use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";
    }

}
