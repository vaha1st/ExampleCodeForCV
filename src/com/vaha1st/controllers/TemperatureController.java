package com.vaha1st.controllers;

import com.vaha1st.entity.TempConversion;
import com.vaha1st.service.TemperatureService;
import com.vaha1st.temperature.HibernateReadyInput;
import com.vaha1st.temperature.SimpleValuesInput;
import com.vaha1st.temperature.SpringConfig;
import com.vaha1st.temperature.TemperatureUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * {@code TemperatureController} контроллер для обработки запросов по работе с температурным конвертером.
 *
 * @author Руслан Вахитов
 * @version 1.00 17 Apr 2020
 */

@Controller
@RequestMapping("/temperature")
public class TemperatureController {

    // Создание spring контейнера с помощью java конфигурационного класса
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    @Autowired
    TemperatureService temperatureService;

    // Обработка запросов на ввод данных.
    @RequestMapping("/input")
    private String input(Model model) {

        List<TempConversion> tempConversion = temperatureService.getTempEntity();

        // Получение бина простейшей конвертации.
        HibernateReadyInput input = context.getBean("hibernateReadyInput", HibernateReadyInput.class);

        // Добавление в атрибуты модели полученного бина и значений ENUM TemperatureUnits
        model.addAttribute("input", input);
        model.addAttribute("temperatureUnits", TemperatureUnits.values());

        // Адресация к temperature-converter.jsp
        return "temperature-converter";
    }

    // Обработка введенных данных из формы в temperature-converter.jsp
    @RequestMapping("/result")
    private String processConversion(
            @Valid @ModelAttribute("input") HibernateReadyInput input, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "temperature-converter";
        } else {
            input.performConvert();
            temperatureService.saveConversion(input.getTempConversion());
            return "temperature-converter";
        }
    }
}
