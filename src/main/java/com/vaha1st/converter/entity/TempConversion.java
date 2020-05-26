package com.vaha1st.converter.entity;

import javax.persistence.*;

@Entity
@Table(name="converting_history")
public class TempConversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    private double value;

    @Column(name = "in_unit")
    private String inUnit;

    @Column(name = "result")
    private double result;

    @Column(name = "out_unit")
    private String outUnit;

    public TempConversion() {
    }

    public TempConversion(double value, String inUnit, double result, String outUnit) {
        this.value=value;
        this.inUnit=inUnit;
        this.result=result;
        this.outUnit=outUnit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getInUnit() {
        return inUnit;
    }

    public void setInUnit(String inUnit) {
        this.inUnit = inUnit;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getOutUnit() {
        return outUnit;
    }

    public void setOutUnit(String outUnit) {
        this.outUnit = outUnit;
    }

    @Override
    public String toString() {
        return "TempEntity{" +
                "id=" + id +
                ", value=" + value +
                ", inUnit='" + inUnit + '\'' +
                ", result=" + result +
                ", outUnit='" + outUnit + '\'' +
                '}';
    }
}
