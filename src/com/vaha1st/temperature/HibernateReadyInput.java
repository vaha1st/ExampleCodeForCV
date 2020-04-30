package com.vaha1st.temperature;

import com.vaha1st.entity.TempConversion;
import org.springframework.stereotype.Component;

/**
 * {@code SimpleValuesInput} осуществляет простой прем входных данных в соответствующие поля. С помощью сеттеров можно
 * подставлять из любых источников. Является синглтон-компонентом Spring.
 *
 * @author Руслан Вахитов
 * @version 1.00 7 Apr 2020
 */

@Component
public class HibernateReadyInput implements Input {
    // Переменная класса, осуществляющего конвертацию
    private Conversion conversion;

    private TempConversion tempConversion;

    // Температура на входе
    private double value = 0;
    // Тип температуры на входе (from)
    private TemperatureUnits inUnit = TemperatureUnits.CELSIUS;
    // Тип температуры на выходе (to)
    private TemperatureUnits outUnit = TemperatureUnits.CELSIUS;

    // Хранение результата
    private double result;

    /**
     * {@code performConvert} метод выполняет конвертацию и вывод на консоль путем вызова класса Conversion и
     * подстановки входных параметров.
     */
    @Override
    public void performConvert() {
        conversion = new Conversion(value, inUnit, outUnit);
        result = conversion.convert();
        tempConversion = new TempConversion(value, inUnit.getUnit().toUpperCase(), result, outUnit.getUnit().toUpperCase());
    }

    public double getValue() {
        return value;
    }

    public TemperatureUnits getInUnit() {
        return inUnit;
    }

    public TemperatureUnits getOutUnit() {
        return outUnit;
    }

    public double getResult() {
        return result;
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

    public TempConversion getTempConversion() {
        return tempConversion;
    }

    public void setTempConversion(TempConversion tempConversion) {
        this.tempConversion = tempConversion;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
