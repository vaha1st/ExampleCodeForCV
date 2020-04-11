package com.vaha1st.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    private String HomeController() {
        return "converter-main";
    }
}
