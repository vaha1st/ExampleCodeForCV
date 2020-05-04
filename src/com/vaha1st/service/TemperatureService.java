package com.vaha1st.service;

import com.vaha1st.entity.TempConversion;

import java.util.List;

/**
 * {@code TemperatureService} интерфейс сервиса для обработки запросов конвертера температур.
 *
 * @author Руслан Вахитов
 * @version 1.00 4 May 2020
 */
public interface TemperatureService {

    // Получать список конвертаций из БД
    List<TempConversion> getTempEntity();

    // Сохранять конвертацию в БД
    void saveConversion(TempConversion tempConversion);

    // Удалять отдельную запись из таблицы истории
    void deleteInput(int id);

    // Очищать таблицу истории
    void clearHistory();
}
