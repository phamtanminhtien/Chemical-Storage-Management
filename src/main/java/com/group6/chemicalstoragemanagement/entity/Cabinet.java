package com.group6.chemicalstoragemanagement.entity;



public class Cabinet extends Entity {
    private String name;
    private float temp;
    private float capacity;

    public Cabinet(String name, float temp, float capacity) {
        super();
        this.name = name;
        this.temp = temp;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    public boolean isOverLoad(){
        return true;
    }

    @Override
    public String toString() {
        return name;
    }
}
