package com.vaha1st.temperature.types;

/**
 * {@code Temperature} родительский класс типов температур.
 *
 * @author Руслан Вахитов
 * @version 0.01 26 Oct 2019
 */

public class Temperature {

    /** Результирующее значение температуры */
    private double temperature;

    /**
     * Метод {@code process} определяет поведение конвертации и должен быть переопределен для
     * каждого дочернего типа температуры
     *
     * @param id принимает идентификационный номер температуры "на выходе" (to).
     * @param temperature принимает значение температуры "на входе", которые необходимо конвертировать.
     */
    public void process(int id, double temperature) {
        System.err.println("Ошибка. Не переопределен метод process() для этого типа температуры.");
    }

    /**
     * Метод {@code round()} округляет деление даблов, что бы избежать погрешности.
     * Макс количество цифр после после знака разделителя - 9.
     *
     * @param rawTemperature принимает double значение температуры с погрешностью после выполнения
     * математических операций.
     * @return возвращает округленное double значение до 9 знаков после разделителя.
     */
    double round(double rawTemperature) {
        return Math.round(rawTemperature * 1000000000) / 1000000000D;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "is " + temperature;
    }
}
