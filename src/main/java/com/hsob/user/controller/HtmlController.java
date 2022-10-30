package com.hsob.user.controller;

import com.hsob.user.model.html.SelectValues;
import com.hsob.user.model.user.UserRequest;
import com.hsob.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class HtmlController {
    @Autowired
    private UserService userService;
    @GetMapping("/hello")
    public String hello(ModelMap modelMap) {
        List<UserRequest> users = userService.getAllUsers();
        modelMap.addAttribute("users",users);
        return "hello";
    }

    @GetMapping("/home")
    public String home( @ModelAttribute("userRequest") UserRequest userRequest, BindingResult bindingResult,
                       HttpServletRequest request, Model model) {
        if(bindingResult.hasErrors()){
//            new ResponseStatusException(HttpStatus.NOT_FOUND, "There was a error "+bindingResult);
            System.out.println("There was a error "+bindingResult);
            return "index";
        }
        List<SelectValues> gender = new ArrayList<>();
        gender.add(new SelectValues("", ""));
        gender.add(new SelectValues("Cisgenêro", "CISGENDER"));
        gender.add(new SelectValues("Transgênero", "TRANSGENDER"));
        gender.add(new SelectValues("Não-Binario", "NOT-BINARY"));
        gender.add(new SelectValues("Intersex", "INTERSEX"));
        List<SelectValues> genderIdentity = new ArrayList<>();
        genderIdentity.add(new SelectValues("", ""));
        genderIdentity.add(new SelectValues("Masculino", "MALE"));
        genderIdentity.add(new SelectValues("Feminino", "FEMALE"));
        genderIdentity.add(new SelectValues("Homem-Trans", "TRANSMALE"));
        genderIdentity.add(new SelectValues("Mulher-Trans", "TRANSFEMALE"));

        model.addAttribute("userRequest", new UserRequest());
        model.addAttribute("genders", gender);
        model.addAttribute("genderIdentities", genderIdentity);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute("userRequest") UserRequest userRequest) {
        ModelAndView mv = new ModelAndView("create");
        UserRequest userCreated = userService.saveUser(userRequest);
        mv.addObject("userRequest", userCreated);
        return "index";
    }


}
