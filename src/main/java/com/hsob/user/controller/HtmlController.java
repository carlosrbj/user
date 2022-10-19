package com.hsob.user.controller;

import com.hsob.user.model.user.UserRequest;
import com.hsob.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HtmlController {
    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/save-user")
    public String saveUser(UserRequest userRequest, String password, String confirmPassword) {
        ModelAndView mv = new ModelAndView("create");
        UserRequest userCreated = userService.saveUser(userRequest, password, confirmPassword);
        mv.addObject("userRequest", userCreated);
        return "index";
    }

    @GetMapping("/hello")
    public String hello(ModelMap modelMap) {
        modelMap.addAttribute("name", "Carlos");
        return "hello";
    }
}
