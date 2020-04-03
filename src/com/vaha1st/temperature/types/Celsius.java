package com.vaha1st.temperature.types;

import org.springframework.stereotype.Component;

/**
 * {@code Celsius} дочерний тип температур. Представляет реализацию конвертации из градусов Цельсия в другие.
 * В этом классе разновидностей температур методы конвертации представлены в виде математических формул. В остальных,
 * для удобства и уменьшения вероятности ошибок, температура будет переводиться сначала в градусы Цельсиуса, а затем
 * конвертироватся методами этого класса.
 *
 * @author Руслан Вахитов
 * @version 0.01 26 Oct 2019
 */

@Component
public class Celsius extends Temperature {

    /** Результирующее значение температуры */
    private double temperature;

    /**
     * Метод {@code process} задает правило выбора необходимой конвертации. Для каждого идентификатора устанавливается
     * подходящий метод реализации.
     *
     * @param id принимает идентификационный номер температуры "на выходе" (to).
     * @param temperature принимает значение температуры "на входе", которые необходимо конвертировать.
     */
    @Override
    public void process(int id, double temperature) {
        if (id == 1) {
            celsiusToFahrenheit(temperature);
        } else if (id == 2) {
            celsiusToKelvin(temperature);
        } else if (id == 3) {
            celsiusToDelisle(temperature);
        } else if (id == 4) {
            celsiusToRankine(temperature);
        } else if (id == 5) {
            celsiusToNewton(temperature);
        } else this.temperature = temperature;
    }

    /** Методы celsiusTo... выполняют конвертацию заданной температуры.
     *
     * @param celsius принимает градусы Цельсия.
     * @return возвращает double значение сконвертированной температуры.
     */
    double celsiusToFahrenheit(double celsius) {
        return this.temperature = round(celsius * 1.8 + 32);
    }
    double celsiusToKelvin(double celsius) {
        return this.temperature = round(celsius + 273.15);
    }
    double celsiusToRankine(double celsius) {
        return this.temperature = round((celsius + 273.15) * 9 / 5);
    }
    double celsiusToDelisle(double celsius) {
        return this.temperature = round((100 - celsius) * 3 / 2);
    }
    double celsiusToNewton(double celsius) {
        return this.temperature = round(33.0 / 100.0 * celsius);
    }

    @Override
    public double getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "is " + temperature;
    }
}
