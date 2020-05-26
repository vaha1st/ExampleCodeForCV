package com.vaha1st.converter.service;

import com.vaha1st.converter.dao.TemperatureDAO;
import com.vaha1st.converter.entity.TempConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * {@code TemperatureServiceImpl} имплементация интерфейса сервиса для обработки запросов конвертера температур.
 * Делегирование исполенения методов к DAO согласно паттерну проектирования.
 *
 * @author Руслан Вахитов
 * @version 1.00 4 May 2020
 */
@Service
public class TemperatureServiceImpl implements TemperatureService {

    @Autowired
    TemperatureDAO temperatureDAO;

    @Override
    @Transactional
    public List<TempConversion> getTempEntity() {

        return temperatureDAO.getTempEntity();
    }

    @Override
    @Transactional
    public void saveConversion(TempConversion tempConversion) {
        temperatureDAO.saveConversion(tempConversion);
    }

    @Override
    @Transactional
    public void deleteInput(int id) {
        temperatureDAO.deleteInput(id);
    }

    @Override
    @Transactional
    public void clearHistory() {
        temperatureDAO.clearHistory();
    }
}
