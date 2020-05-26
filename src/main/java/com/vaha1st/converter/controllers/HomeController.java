package com.vaha1st.converter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * {@code HomeController} обрабатывает пустой запрос к серверу и адресует на домашнюю страницу converter-main.jsp.
 *
 * @author Руслан Вахитов
 * @version 1.00 17 Apr 2020
 */

@Controller
public class HomeController {

    @GetMapping("/")
    private String HomeController() {
        return "converter-main";
    }
}
