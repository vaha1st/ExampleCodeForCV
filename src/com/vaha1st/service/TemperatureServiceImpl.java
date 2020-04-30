package com.vaha1st.service;

import com.vaha1st.dao.TemperatureDAO;
import com.vaha1st.entity.TempConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
