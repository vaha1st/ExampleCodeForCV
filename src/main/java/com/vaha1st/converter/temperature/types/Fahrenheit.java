package com.vaha1st.converter.temperature.types;

/**
 * {@code Fahrenheit} дочерний тип температур. Представляет реализацию конвертации из градусов Фаренгейта в другие.
 *
 * @author Руслан Вахитов
 * @version 1.00 26 Oct 2019
 */
public class Fahrenheit extends Temperature {

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
            fahrenheitToCelsius(temperature);
        } else if (id == 2) {
            fahrenheitToKelvin(temperature);
        } else if (id == 3) {
            fahrenheitToDelisle(temperature);
        } else if (id == 4) {
            fahrenheitToRankine(temperature);
        } else if (id == 5) {
            fahrenheitToNewton(temperature);
        } else this.temperature = temperature;
    }

    /**
     * Методы fahrenheitTo... выполняют конвертацию заданной температуры.
     *
     * @param fahrenheit принимает градусы Фаренгейта.
     * @return возвращает double значение сконвертированной температуры.
     */
    double fahrenheitToCelsius(double fahrenheit) {
        return this.temperature = round((fahrenheit - 32) * 5 / 9);
    }

    double fahrenheitToKelvin(double fahrenheit) {
//        return this.temperature = round((fahrenheit + 459.67) * 5 / 9);
        return this.temperature = celsius.celsiusToKelvin(fahrenheitToCelsius(fahrenheit));
    }

    double fahrenheitToRankine(double fahrenheit) {
//        return this.temperature = round(fahrenheit+459.67);
        return this.temperature = celsius.celsiusToRankine(fahrenheitToCelsius(fahrenheit));
    }

    double fahrenheitToDelisle(double fahrenheit) {
        return this.temperature = celsius.celsiusToDelisle(fahrenheitToCelsius(fahrenheit));
    }

    double fahrenheitToNewton(double fahrenheit) {
        return this.temperature = celsius.celsiusToNewton(fahrenheitToCelsius(fahrenheit));
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
