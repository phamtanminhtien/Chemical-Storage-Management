package com.group6.chemicalstoragemanagement.entity;


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
}
