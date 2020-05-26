package com.vaha1st.converter.temperature;

import com.vaha1st.converter.temperature.types.*;

/**
 * {@code TemperatureUnits} это enum, представляющий еденицы измерения температуры. Для простоты обращения каждому
 * типу присвоен конечный идентификационный номер.
 *
 * @author Руслан Вахитов
 * @version 1.00 26 Oct 2019
 */

public enum TemperatureUnits {

    /**
     * Градусы Цельсия. Идентификацонный номер - 0
     */
    CELSIUS(0, "C", new Celsius()),

    /**
     * Градусы Фаренгейта. Идентификацонный номер - 1
     */
    FAHRENHEIT(1, "F", new Fahrenheit()),

    /**
     * Кельвин. Идентификацонный номер - 2
     */
    KELVIN(2, "K", new Kelvin()),

    /**
     * Градусы Делиля. Идентификацонный номер - 3
     */
    DELISLE(3, "D", new Delisle()),

    /**
     * Градусы Ранкина. Идентификацонный номер - 4
     */
    RANKINE(4, "Ra", new Rankine()),

    /**
     * Градусы Ньютона. Идентификацонный номер - 5
     */
    NEWTON(5, "N", new Newton());

    /**
     * Хранит идентификационный номер для созданного эксземпляра температуры.
     * Необходим для реализации связывания едениц измерения температуры "до" и "после"
     */
    final private int id;

    /**
     * Текстовое сокращение еденицы измерения для удобства вывода
     */
    final private String unit;

    /**
     * Интсанс выбранной температуры
     */
    private Temperature temperature;

    TemperatureUnits(int id, String unit, Temperature temperature) {
        this.id = id;
        this.unit = unit;
        this.temperature = temperature;
    }

    public int getId() {
        return id;
    }

    public String getUnit() {
        return unit;
    }

    public Temperature getTemperature() {
        return temperature;
    }

}
