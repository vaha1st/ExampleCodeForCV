package com.vaha1st.temperature.types;

import org.springframework.stereotype.Component;

/**
 * {@code Rankine} дочерний тип температур. Представляет реализацию конвертации из градусов Ранкина в другие.
 *
 * @author Руслан Вахитов
 * @version 0.01 26 Oct 2019
 */

@Component
public class Rankine extends Temperature {

    /**
     * Инстанс класса {@code Celsius} для перевода температуры сначала в градусы Цельсиуса, а затем
     * необходимой конвертации
     */
    private Celsius celsius = new Celsius();
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
        this.temperature = temperature;
        if (id == 0) {
            rankineToCelsius(temperature);
        } else if (id == 1) {
            rankineToFahrenheit(temperature);
        } else if (id == 2) {
            rankineToKelvin(temperature);
        } else if (id == 3) {
            rankineToDelisle(temperature);
        } else if (id == 5) {
            rankineToNewton(temperature);
        } else this.temperature = temperature;
    }

    /** Методы rankineTo... выполняют конвертацию заданной температуры.
     *
     * @param rankine принимает градусы Ранкина.
     * @return возвращает double значение сконвертированной температуры.
     */
    double rankineToCelsius(double rankine) {
        return this.temperature = round((rankine - 491.67) * 5 / 9);
    }
    double rankineToFahrenheit(double rankine) {
//        return this.temperature = round(rankine-459.67);
        return this.temperature = celsius.celsiusToFahrenheit(rankineToCelsius(rankine));
    }
    double rankineToKelvin(double rankine) {
//        return this.temperature = round(rankine*5/9);
        return this.temperature = celsius.celsiusToKelvin(rankineToCelsius(rankine));
    }
    double rankineToDelisle(double rankine) {
        return this.temperature = celsius.celsiusToDelisle(rankineToCelsius(rankine));
    }
    double rankineToNewton(double rankine) {
        return this.temperature = celsius.celsiusToNewton(rankineToCelsius(rankine));
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
