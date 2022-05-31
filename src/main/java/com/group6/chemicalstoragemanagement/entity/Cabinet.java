package com.group6.chemicalstoragemanagement.entity;


import com.group6.chemicalstoragemanagement.repository.ChemicalRepository;

import java.util.ArrayList;

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
        ArrayList<Chemical> chemicals = getAllChemical();
        float totalWeight = 0;
        for (Chemical chemical : chemicals) {
            totalWeight += chemical.getWeight();
        }
        return totalWeight > capacity;
    }

    public ArrayList<Chemical> getAllChemical(){
        ArrayList<Chemical> chemicals = ChemicalRepository.getInstance().getAll();
        ArrayList<Chemical> result = new ArrayList<>();

        for (Chemical chemical : chemicals) {
            if (chemical.getCabinet() == (this)) result.add(chemical);
        }
        return  result;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getStatus(){
        return  isOverLoad() ? "OverLoad" : "none";
    }
}
