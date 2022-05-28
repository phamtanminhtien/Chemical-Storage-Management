package com.group6.chemicalstoragemanagement.entity;


import com.group6.chemicalstoragemanagement.repository.ChemicalRepository;

import java.util.ArrayList;

public class Name extends Entity {
    private String name;

    public Name(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public ArrayList<Chemical> getAllChemical(){
        ArrayList<Chemical> chemicals = ChemicalRepository.getInstance().getAll();
        ArrayList<Chemical> result = new ArrayList<>();

        for (Chemical chemical : chemicals) {
            if (chemical.getName() == (this)) result.add(chemical);
        }
        return  result;
    }
}
