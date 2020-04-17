package com.vaha1st.controllers;

import com.vaha1st.temperature.SimpleValuesInput;
import com.vaha1st.temperature.SpringConfig;
import com.vaha1st.temperature.TemperatureUnits;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/temperature")
public class TemperatureController {

    // Создание spring контейнера с помощью java конфигурационного класса
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    @RequestMapping("/input")
    private String input(Model model) {

        SimpleValuesInput input = context.getBean("simpleValuesInput", SimpleValuesInput.class);

        model.addAttribute("input", input);

        model.addAttribute("temperatureUnits", TemperatureUnits.values());

        return "temperature-converter";
    }

    @RequestMapping("/result")
    private String processConversion(
            @Valid @ModelAttribute("input") SimpleValuesInput input, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "temperature-converter";
        } else {
            input.performConvert();
            return "temperature-converter";
        }
    }
}
