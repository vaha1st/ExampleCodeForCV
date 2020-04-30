package com.vaha1st.service;

import com.vaha1st.entity.TempConversion;

import java.util.List;

public interface TemperatureService {

    List<TempConversion> getTempEntity();

    void saveConversion(TempConversion tempConversion);
}
