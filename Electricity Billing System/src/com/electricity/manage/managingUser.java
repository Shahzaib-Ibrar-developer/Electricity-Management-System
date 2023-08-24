
package com.electricity.manage;
public class managingUser {
    private String name;
    private int id;
    private String city;
    private double meterNumber;
    private String meterType;
    private int tax = 30;
    private int fixedCharges = 100;
    private double consumptionLevel;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(double meterNumber) {
        this.meterNumber = meterNumber;
    }

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

   

  
    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getFixedCharges() {
        return fixedCharges;
    }

    public void setFixedCharges(int fixedCharges) {
        this.fixedCharges = fixedCharges;
    }

    public double getConsumptionLevel() {
        return consumptionLevel;
    }

    public void setConsumptionLevel(double consumptionLevel) {
        this.consumptionLevel = consumptionLevel;
    }

    public managingUser(String name, int id, String city, double meterNumber, String meterType) {
        this.name = name;
        this.id = id;
        this.city = city;
        this.meterNumber = meterNumber;
        this.meterType = meterType;
    }

   

    public managingUser(String name, String city, double meterNumber, String meterType) {
        this.name = name;
        this.city = city;
        this.meterNumber = meterNumber;
        this.meterType = meterType;
    }

    public managingUser() {
    }
    

    @Override
    public String toString() {
        return "managingUser [name=" + name + ", id=" + id + ", city=" + city + ", meterNumber=" + meterNumber
                + ", meterType=" + meterType + "]";
    }

    public static void main(String[] args) {





    }
}
