package com.example.securityBasicUserDetail.controller;

import com.example.securityBasicUserDetail.model.User;
import com.example.securityBasicUserDetail.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {

    @Autowired
    private IUserService userService;

    //Go to Registration Page
    @GetMapping("/register")
    public String register(){
        return "registerUser";
    }

    //Read from data to save into DB
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user,
                           Model model){
        Integer id = userService.saveUser(user);
        String message = "User '" + id +"' saved successfully!";
        model.addAttribute("msg",message);
        return "registerUser";
    }

    @GetMapping("/home")
    public String getHomePage() {
        return "homePage";
    }

    @GetMapping("/welcome")
    public String getWelcomePage() {
        return "welcomePage";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "adminPage";
    }

    @GetMapping("/emp")
    public String getEmployeePage() {
        return "empPage";
    }

    @GetMapping("/mgr")
    public String getManagerPage() {
        return "mgrPage";
    }

    @GetMapping("/hr")
    public String getHrPage() {
        return "hrPage";
    }

    @GetMapping("/common")
    public String getCommonPage() {
        return "commonPage";
    }

    @GetMapping("/accessDenied")
    public String getAccessDeniedPage() {
        return "accessDeniedPage";
    }

}
