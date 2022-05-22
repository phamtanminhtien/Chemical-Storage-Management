package com.group6.chemicalstoragemanagement.entity;


import java.util.Date;

public class Chemical extends Entity {
    private Name name;
    private Cabinet cabinet;
    private float weight;
    private float minTemp;
    private float maxTemp;
    private Date expiration;

    public Chemical(Name name, Cabinet cabinet, Float weight, Float minTemp, Float maxTemp, Date expiration) {
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

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public boolean isExpiration() {
        return (this.expiration.before(new Date()));
    }

    public boolean isOutOfTempRange(){
        return true;
    }


}
