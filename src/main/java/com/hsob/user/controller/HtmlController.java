package com.hsob.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.swing.text.html.HTML;

@Controller
public class HtmlController {

    @GetMapping("/home")
    public String hello() {
        try {
        return "hello.html";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
