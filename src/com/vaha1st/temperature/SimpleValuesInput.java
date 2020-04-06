package com.vaha1st.temperature;

import org.springframework.stereotype.Component;

@Component
public class SimpleValuesInput implements Input {
    // Переменная класса, осуществляющего конвертацию
    private Conversion conversion;

    // Температура на входе
    private double value = 557.55;
    // Тип температуры на входе (from)
    private TemperatureUnits inUnit = TemperatureUnits.RANKINE;
    // Тип температуры на выходе (to)
    private TemperatureUnits outUnit = TemperatureUnits.CELSIUS;

    // Хранение результата
    private double result;

    @Override
    public void performConvert() {
        conversion = new Conversion(value, inUnit, outUnit);
        result = conversion.convert();
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setInUnit(TemperatureUnits inUnit) {
        this.inUnit = inUnit;
    }

    public void setOutUnit(TemperatureUnits outUnit) {
        this.outUnit = outUnit;
    }
}
