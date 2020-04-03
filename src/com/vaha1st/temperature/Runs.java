package com.vaha1st.temperature;

import org.springframework.stereotype.Component;

/**
 * {@code Runs} используется для обобщения входных данных и результата конвертера за один проход (run). Объекты этого
 * класса будут использоваться для изъятия значений при логировании или вносе в БД.
 *
 * @version 0.01 10 Feb 2020
 * @author Руслан Вахитов
 */

class Runs {
    ConvertTemperature.Input input;
    Double result;

    public Runs(ConvertTemperature.Input input, Double result) {
        this.input = input;
        this.result = result;
    }

    public ConvertTemperature.Input getInput() {
        return input;
    }

    public void setInput(ConvertTemperature.Input input) {
        this.input = input;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return " "+input.value+""+input.getInUnit().getUnit()+" | "+result+""+input.getOutUnit().getUnit()+" ";
    }
}
