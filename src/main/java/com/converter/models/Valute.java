package com.converter.models;

public class Valute {
    private String ID;
    private String numCode;
    private String charCode;
    private int nominal;
    private String name;
    private double value;
    private String data;
    private long key;




    public void setKey(long key) {
        this.key = key;
    }

    public long getKey() {
        return key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Valute(String date){
        ID="R01111";
        charCode="RUB";
        nominal=1;
        name="Российский рубль";
        numCode="111";
        value=1;
        data=date;

    }
    public Valute(){

    }
}
