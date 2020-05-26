package com.vaha1st.converter.controllers;

import com.vaha1st.converter.config.SpringContextConfig;
import com.vaha1st.converter.entity.TempConversion;
import com.vaha1st.converter.service.TemperatureService;
import com.vaha1st.converter.temperature.HibernateReadyInput;
import com.vaha1st.converter.temperature.TemperatureUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * {@code TemperatureController} контроллер для обработки запросов по работе с температурным конвертером.
 *
 * @author Руслан Вахитов
 * @version 1.10 4 May 2020
 */

@Controller
@RequestMapping("/temperature")
public class TemperatureController {

    // Создание spring контейнера с помощью java конфигурационного класса
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringContextConfig.class);

    // Подключение сервиса для обработки запросов
    @Autowired
    private TemperatureService temperatureService;

    // Обработка запросов на ввод данных.
    @GetMapping("/input")
    protected String input(Model model) {

        // Получение бина простейшей конвертации.
        HibernateReadyInput input = context.getBean("hibernateReadyInput", HibernateReadyInput.class);

        // Добавление в атрибуты модели полученного бина и значений ENUM TemperatureUnits
        model.addAttribute("input", input);
        model.addAttribute("temperatureUnits", TemperatureUnits.values());

        // Адресация к temperature-converter.jsp
        return "temperature-converter";
    }

    // Обработка запросов на ввод данных с опцией истории.
    @GetMapping("/input-with-history")
    protected String inputWithHistory(Model model) {

        // Список конвертация для отображения истории
        List<TempConversion> tempConversion = temperatureService.getTempEntity();

        // Получение бина простейшей конвертации.
        HibernateReadyInput input = context.getBean("hibernateReadyInput", HibernateReadyInput.class);

        // Добавление в атрибуты модели полученного бина ввода, истории и значений ENUM TemperatureUnits
        model.addAttribute("inputWH", input);
        model.addAttribute("inputHistory", tempConversion);
        model.addAttribute("temperatureUnits", TemperatureUnits.values());

        // Адресация к temperature-converter-with-history.jsp
        return "temperature-converter-with-history";
    }

    // Обработка введенных данных из формы в temperature-converter.jsp
    @PostMapping("/result")
    protected String processConversion(
            @Valid @ModelAttribute("input") HibernateReadyInput input, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "temperature-converter";
        } else {
            input.performConvert();
            temperatureService.saveConversion(input.getTempConversion());
            return "temperature-converter";
        }
    }

    // Обработка введенных данных из формы в temperature-converter-with-history.jsp
    @PostMapping("/resultWithHistory")
    protected String processConversionWithHistory(
            @Valid @ModelAttribute("inputWH") HibernateReadyInput input, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "temperature-converter-with-history";
        } else {
            input.performConvert();
            temperatureService.saveConversion(input.getTempConversion());
            return "redirect:/temperature/input-with-history";
        }
    }

    // Обработка запроса на удаление отдельной записи истории
    @GetMapping("/delete")
    protected String deleteInputFromHistory(@RequestParam("inputId") int id) {
        temperatureService.deleteInput(id);
        return "redirect:/temperature/input-with-history";
    }

    // Обработка запроса на полную очистку истории
    @GetMapping("/clear")
    protected String clearHistory() {
        temperatureService.clearHistory();
        return "redirect:/temperature/input-with-history";
    }



}
