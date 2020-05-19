package com.vaha1st.temperature.types;

/**
 * {@code Delisle} дочерний тип температур. Представляет реализацию конвертации из градусов Делиля в другие.
 *
 * @author Руслан Вахитов
 * @version 1.00 26 Oct 2019
 */
public class Delisle extends Temperature {

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
            delisleToCelsius(temperature);
        } else if (id == 1) {
            delisleToFahrenheit(temperature);
        } else if (id == 2) {
            delisleToKelvin(temperature);
        } else if (id == 4) {
            delisleToRankine(temperature);
        } else if (id == 5) {
            delisleToNewton(temperature);
        } else this.temperature = temperature;
    }

    /**
     * Методы delisleTo... выполняют конвертацию заданной температуры.
     *
     * @param delisle принимает градусы Делиля.
     * @return возвращает double значение сконвертированной температуры.
     */
    double delisleToCelsius(double delisle) {
        return this.temperature = round(100 - delisle * 2 / 3);
    }

    double delisleToFahrenheit(double delisle) {
        return this.temperature = celsius.celsiusToFahrenheit(delisleToCelsius(delisle));
    }

    double delisleToKelvin(double delisle) {
        return this.temperature = celsius.celsiusToKelvin(delisleToCelsius(delisle));
    }

    double delisleToRankine(double delisle) {
        return this.temperature = celsius.celsiusToRankine(delisleToCelsius(delisle));
    }

    double delisleToNewton(double delisle) {
        return this.temperature = celsius.celsiusToNewton(delisleToCelsius(delisle));
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
