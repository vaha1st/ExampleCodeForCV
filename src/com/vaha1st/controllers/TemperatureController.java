package com.vaha1st.controllers;

import com.vaha1st.temperature.SimpleValuesInput;
import com.vaha1st.temperature.SpringConfig;
import com.vaha1st.temperature.TemperatureUnits;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private String processConversion(@ModelAttribute("input") SimpleValuesInput input) {

        input.performConvert();

        return "temperature-converter";
    }
}
