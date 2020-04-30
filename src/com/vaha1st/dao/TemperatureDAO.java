package com.vaha1st.dao;

import com.vaha1st.entity.TempConversion;

import java.util.List;

public interface TemperatureDAO {

    List<TempConversion> getTempEntity();

    void saveConversion(TempConversion tempConversion);
}
