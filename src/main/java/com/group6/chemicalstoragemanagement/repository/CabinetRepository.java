package com.group6.chemicalstoragemanagement.repository;

import com.group6.chemicalstoragemanagement.entity.Cabinet;

public class CabinetRepository extends Repository<Cabinet>{

    private static CabinetRepository instance;

    CabinetRepository() {
    }

    public static synchronized CabinetRepository getInstance() {
        if (instance == null) {
            instance = new CabinetRepository();
        }
        return instance;
    }




}
