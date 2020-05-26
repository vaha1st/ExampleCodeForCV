package com.vaha1st.converter.temperature;

import com.vaha1st.converter.temperature.types.Temperature;

/**
 * {@code Conversion} получает входные данные при инициализации. Метод convert() производит конвертацию этих данных.
 *
 * @author Руслан Вахитов
 * @version 1.00 7 Apr 2020
 */
public class Conversion {

    // В этой переменной будет определяться правильный тип температуры в конструкторе.
    Temperature temperature;

    // Температура на входе
    double value;

    // Тип температуры на входе (from)
    TemperatureUnits inUnit;

    // Тип температуры на выходе (to)
    TemperatureUnits outUnit;

    public Conversion(double value, TemperatureUnits inUnit, TemperatureUnits outUnit) {
        // Тип начальной температуры определяется температурой на входе inUnit.
        this.temperature = inUnit.getTemperature();
        this.value = value;
        this.inUnit = inUnit;
        this.outUnit = outUnit;
    }

    /**
     * {@code convert} метод выполняет конвертацию и вывод на консоль
     *
     * @return возвращает значение температуры после конвертации.
     */
    public double convert() {
        // Каждый тип температуры имеет свою реализацию конвертаций методом process(), принимающий на вход
        // тип температуры в которую нужно произвести преобразование
        temperature.process(outUnit.getId(), value);
        System.out.println(value + "" + inUnit.getUnit() + " = " +
                temperature.getTemperature() + "" + outUnit.getUnit());
        return temperature.getTemperature();
    }
}
