package com.vaha1st.temperature.storage_types;

import com.vaha1st.temperature.Input;
import com.vaha1st.temperature.TemperatureUnits;
import com.vaha1st.temperature.types.Temperature;

public interface Storage {
    public void write(double value, TemperatureUnits inUnit, TemperatureUnits outUnit, double result);
    public void exit();
    public void clear();
    public void history();
}
