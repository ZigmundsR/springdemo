package com.zigmunds.rieksts.springdemo.controller;

import com.zigmunds.rieksts.springdemo.entity.Role;
import com.zigmunds.rieksts.springdemo.entity.User;
import com.zigmunds.rieksts.springdemo.service.RoleService;
import com.zigmunds.rieksts.springdemo.service.UserService;
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

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/list")
    public String listUsers(Model theModel,
                            @RequestParam(name = "search", required = false) String search) {
        List<User> theUsers;
        if (search == null || search.isEmpty()){
            theUsers = userService.findAll();
        } else {
            theUsers = userService.findAll(search);
        }

        theModel.addAttribute("users", theUsers);

        return "users/list-users";
    }

    @GetMapping("/register")
    public String registerUser(Model theModel) {
        User user = new User();

        theModel.addAttribute("user", user);

        manageModelForRoles(theModel, user, "");

        return "users/user-form";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("userId") int theId,
                             Model theModel) {

        User user = userService.findById(theId);

        theModel.addAttribute("user", user);

        manageModelForRoles(theModel, user, "");

        return "users/user-form";
    }

    @PostMapping("/save")
    public String saveUser(
            @Valid @ModelAttribute("user") User user,
            BindingResult theBindingResult, Model theModel,
            @RequestParam(name = "selectedRoles", required = false) String selectedRoles) {

        if (theBindingResult.hasErrors()) {
            manageModelForRoles(theModel, user, selectedRoles);

            return "users/user-form";
        }
        if (user.getId() == 0) {
            boolean existing = userService.userExistByUserName(user.getUserName());

            if (!existing) {
                theModel.addAttribute("registrationError", "User name already exists.");

                manageModelForRoles(theModel, user, selectedRoles);

                return "users/user-form";
            }
        }
        if (selectedRoles != null) {
            List<String> rolesList = Arrays.asList(selectedRoles.split(","));

            Collection<Role> userRoles = roleService.findRolesByNames(rolesList);

            user.setRoles(userRoles);
        }

        userService.save(user);

        // use a redirect to prevent duplicate submissions
        return "redirect:/users/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("userId") int theId) {

        userService.deleteById(theId);

        return "redirect:/users/list";

    }

    public void manageModelForRoles(Model theModel, User user, String selectedRoles ){
        // send all roles
        List<String> rolesList = roleService.findRoleNames();

        theModel.addAttribute("rolesList", rolesList);

        if (user.getRoles() != null) {
            // send user roles
            List<String> rolesName = user.getRoles().stream()
                    .map(Role::getName)
                    .toList();

            selectedRoles = String.join(",", rolesName);
        }

        theModel.addAttribute("selectedRoles", selectedRoles);
    }

}
