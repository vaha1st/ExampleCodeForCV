package com.vaha1st.temperature.types;

/**
 * {@code Kelvin} дочерний тип температур. Представляет реализацию конвертации из Кельвинов в другие.
 *
 * @author Руслан Вахитов
 * @version 0.01 26 Oct 2019
 */
public class Kelvin extends Temperature {

    /**
     * Результирующее значение температуры
     */
    private double temperature;
    /**
     * Инстанс класса {@code Celsius} для перевода температуры сначала в градусы Цельсиуса, а затем
     * необходимой конвертации
     */
    private Celsius celsius = new Celsius();

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
            kelvinToCelsius(temperature);
        } else if (id == 1) {
            kelvinToFahrenheit(temperature);
        } else if (id == 3) {
            kelvinToDelisle(temperature);
        } else if (id == 4) {
            kelvinToRankine(temperature);
        } else if (id == 5) {
            kelvinToNewton(temperature);
        } else this.temperature = temperature;
    }

    /**
     * Методы kelvinTo... выполняют конвертацию заданной температуры.
     *
     * @param kelvin принимает Кельвины.
     * @return возвращает double значение сконвертированной температуры.
     */
    double kelvinToCelsius(double kelvin) {
        return this.temperature = round(kelvin - 273.15);
    }

    double kelvinToFahrenheit(double kelvin) {
//        return this.temperature = round(kelvin*9/5-459.67);
        return this.temperature = celsius.celsiusToFahrenheit(kelvinToCelsius(kelvin));
    }

    double kelvinToRankine(double kelvin) {
//        return this.temperature = round(kelvin*9/5);
        return this.temperature = celsius.celsiusToRankine(kelvinToCelsius(kelvin));
    }

    double kelvinToDelisle(double kelvin) {
        return this.temperature = celsius.celsiusToDelisle(kelvinToCelsius(kelvin));
    }

    double kelvinToNewton(double kelvin) {
        return this.temperature = celsius.celsiusToNewton(kelvinToCelsius(kelvin));
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
