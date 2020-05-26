package com.vaha1st.converter.dao;

import com.vaha1st.converter.entity.TempConversion;

import java.util.List;

/**
 * {@code TemperatureDAO} интерфейс DAO для для взаимодействия сервиса и БД.
 *
 * @author Руслан Вахитов
 * @version 1.00 4 May 2020
 */
public interface TemperatureDAO {

    // Получать список конвертаций из БД
    List<TempConversion> getTempEntity();

    // Сохранять конвертацию в БД
    void saveConversion(TempConversion tempConversion);

    // Удалять отдельную запись из таблицы истории
    void deleteInput(int id);

    // Очищать таблицу истории
    void clearHistory();
}
