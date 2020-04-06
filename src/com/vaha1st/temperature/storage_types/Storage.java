package com.vaha1st.temperature.storage_types;

import com.vaha1st.temperature.TemperatureUnits;

public interface Storage {
    public void write(double value, TemperatureUnits inUnit, TemperatureUnits outUnit, double result);
    public void exit();
    public void clear();
    public void history();
}
