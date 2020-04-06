package com.vaha1st.temperature;

import org.springframework.stereotype.Component;

@Component
public class SimpleValuesInput implements Input {
    // Переменная класса, осуществляющего конвертацию
    Conversion conversion;

    // Температура на входе
    double value = 557.55;

    // Тип температуры на входе (from)
    TemperatureUnits inUnit = TemperatureUnits.RANKINE;

    // Тип температуры на выходе (to)
    TemperatureUnits outUnit = TemperatureUnits.CELSIUS;

    double result;

    @Override
    public void performConvert() {
        conversion = new Conversion(value, inUnit, outUnit);
        result = conversion.convert();
    }


}
