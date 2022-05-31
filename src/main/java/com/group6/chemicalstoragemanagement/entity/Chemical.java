package com.group6.chemicalstoragemanagement.entity;


import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Chemical extends Entity {
    private Name name;
    private Cabinet cabinet;
    private float weight;
    private float minTemp;
    private float maxTemp;
    private LocalDate expiration;

    public Chemical(Name name, Cabinet cabinet, float weight, float minTemp, float maxTemp, LocalDate expiration) {
        super();
        this.name = name;
        this.cabinet = cabinet;
        this.weight = weight;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.expiration = expiration;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Cabinet getCabinet() {
        return cabinet;
    }

    public void setCabinet(Cabinet cabinet) {
        this.cabinet = cabinet;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(float minTemp) {
        this.minTemp = minTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(float maxTemp) {
        this.maxTemp = maxTemp;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public boolean isExpiration() {
        return (this.expiration.isBefore(LocalDate.now()));
    }
    public boolean isOutOfTempRange(){
        return cabinet.getTemp() > maxTemp || cabinet.getTemp() < minTemp;
    }

    public String getStatus(){
        int length = Period.between(LocalDate.now(), getExpiration()).getDays();
        if(getExpiration().isBefore(LocalDate.now())){
            return "Out of date " + length;
        }else{
            return "In use " + length;
        }
    }

    public String getNote(){
        return isOutOfTempRange() ? "Out Range" : "None";
    }
}
