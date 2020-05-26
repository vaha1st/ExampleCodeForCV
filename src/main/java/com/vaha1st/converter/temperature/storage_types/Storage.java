package com.vaha1st.converter.temperature.storage_types;

import com.vaha1st.converter.temperature.TemperatureUnits;

/**
 * {@code Storage} интерфейс для разных типов хранилищ данных, для работы с историей.
 *
 * @author Руслан Вахитов
 * @version 1.00 7 Apr 2020
 */
public interface Storage {

    /**
     * Методы write должен выполнять специфичную для имплементирующих классов запись данных конвертации.
     *
     * @param value   значение температуры до конвертации.
     * @param inUnit  тип температуры до конвертации.
     * @param outUnit тип температуры после конвертации.
     * @param result  значение температуры после конвертации.
     */
    public void write(double value, TemperatureUnits inUnit, TemperatureUnits outUnit, double result);

    /**
     * Методы exit должен выполнять специфичную для имплементирующих классов очистку ресурсов и выход.
     */
    public void exit();

    /**
     * Методы history должен выполнять специфичную для имплементирующих классов вывод истории на консоль.
     */
    public void history();

    /**
     * Методы clear должен выполнять специфичную для имплементирующих классов очистку истории.
     */
    public void clear();

}
