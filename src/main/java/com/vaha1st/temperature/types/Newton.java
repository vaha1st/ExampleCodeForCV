package com.vaha1st.temperature.types;

/**
 * {@code Newton} дочерний тип температур. Представляет реализацию конвертации из градусов Ньютона в другие.
 *
 * @author Руслан Вахитов
 * @version 1.00 26 Oct 2019
 */

public class Newton extends Temperature {

    /**
     * Инстанс класса {@code Celsius} для перевода температуры сначала в градусы Цельсиуса, а затем
     * необходимой конвертации
     */
    private Celsius celsius = new Celsius();
    /**
     * Результирующее значение температуры
     */
    private double temperature;

    /**
     * Метод {@code process} задает правило выбора необходимой конвертации. Для каждого идентификатора устанавливается
     * подходящий метод реализации.
     *
     * @param id          принимает идентификационный номер температуры "на выходе" (to).
     * @param temperature принимает значение температуры "на входе", которые необходимо конвертировать.
     */
    @Override
    public void process(int id, double temperature) {
        this.temperature = temperature;
        if (id == 0) {
            newtonToCelsius(temperature);
        } else if (id == 1) {
            newtonToFahrenheit(temperature);
        } else if (id == 2) {
            newtonToKelvin(temperature);
        } else if (id == 3) {
            newtonToDelisle(temperature);
        } else if (id == 4) {
            newtonToRankine(temperature);
        } else this.temperature = temperature;
    }

    /**
     * Методы newtonTo... выполняют конвертацию заданной температуры.
     *
     * @param newton принимает градусы Ньютона.
     * @return возвращает double значение сконвертированной температуры.
     */
    double newtonToCelsius(double newton) {
        return this.temperature = round(100.0 / 33.0 * newton);
    }

    double newtonToFahrenheit(double newton) {
        return this.temperature = celsius.celsiusToFahrenheit(newtonToCelsius(newton));
    }

    double newtonToKelvin(double newton) {
        return this.temperature = celsius.celsiusToKelvin(newtonToCelsius(newton));
    }

    double newtonToRankine(double newton) {
        return this.temperature = celsius.celsiusToRankine(newtonToCelsius(newton));
    }

    double newtonToDelisle(double newton) {
        return this.temperature = celsius.celsiusToDelisle(newtonToCelsius(newton));
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
