package com.group6.chemicalstoragemanagement.repository;

import com.group6.chemicalstoragemanagement.entity.Name;

public class NameRepository extends Repository<Name>{
    private static NameRepository instance;

    NameRepository() {
    }

    public static synchronized NameRepository getInstance() {
        if (instance == null) {
            instance = new NameRepository();
        }
        return instance;
    }
}
