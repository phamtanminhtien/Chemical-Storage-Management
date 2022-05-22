package com.group6.chemicalstoragemanagement.repository;

import com.group6.chemicalstoragemanagement.entity.Chemical;

public class ChemicalRepository extends Repository<Chemical>{
    private static ChemicalRepository instance;

    ChemicalRepository() {
    }

    public static synchronized ChemicalRepository getInstance() {
        if (instance == null) {
            instance = new ChemicalRepository();
        }
        return instance;
    }
}
