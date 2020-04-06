package com.vaha1st.temperature;

import com.vaha1st.temperature.types.Temperature;

public class Conversion {

    // В этой переменной будет определяться правильный тип температуры в конструкторе
    Temperature temperature;

    // Температура на входе
    double value;

    // Тип температуры на входе (from)
    TemperatureUnits inUnit;

    // Тип температуры на выходе (to)
    TemperatureUnits outUnit;

    public Conversion(double value, TemperatureUnits inUnit, TemperatureUnits outUnit) {
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
        temperature.process(outUnit.getId(), value);
        System.out.println(value + "" + inUnit.getUnit() + " = " +
                temperature.getTemperature() + "" + outUnit.getUnit());
        return temperature.getTemperature();
    }
}
