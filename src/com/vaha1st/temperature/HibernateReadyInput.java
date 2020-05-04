package com.vaha1st.temperature;

import com.vaha1st.entity.TempConversion;
import org.springframework.stereotype.Component;

/**
 * {@code HibernateReadyInput} наследует простой прием входных данных из {@code SimpleValuesInput}. Добавлена поддержка
 * hibernate, путем добавления поля для инициализации сущности {@code TempConversion}.
 *
 * @author Руслан Вахитов
 * @version 1.00 4 May 2020
 */

@Component
public class HibernateReadyInput extends SimpleValuesInput {

    private TempConversion tempConversion;

    /**
     * {@code performConvert} метод выполняет конвертацию и вывод на консоль путем вызова класса Conversion и
     * подстановки входных параметров. Инициализирует hibernate сущность.
     */
    @Override
    public void performConvert() {
        super.conversion = new Conversion(super.value, super.inUnit, super.outUnit);
        result = conversion.convert();
        tempConversion = new TempConversion(value, inUnit.getUnit().toUpperCase(), result, outUnit.getUnit().toUpperCase());
    }

    public TempConversion getTempConversion() {
        return tempConversion;
    }

    public void setTempConversion(TempConversion tempConversion) {
        this.tempConversion = tempConversion;
    }

}
