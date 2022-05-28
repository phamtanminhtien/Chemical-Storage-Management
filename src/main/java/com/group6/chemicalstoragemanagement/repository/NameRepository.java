package com.group6.chemicalstoragemanagement.repository;

import com.group6.chemicalstoragemanagement.entity.Cabinet;
import com.group6.chemicalstoragemanagement.entity.Chemical;
import com.group6.chemicalstoragemanagement.entity.Name;

import java.util.ArrayList;

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

    @Override
    public void delete(Name entity) {
        for(Chemical chemical : entity.getAllChemical()){
            ChemicalRepository.getInstance().delete(chemical);
        }

        super.delete(entity);
    }
}
