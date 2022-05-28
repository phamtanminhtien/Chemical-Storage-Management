package com.group6.chemicalstoragemanagement.repository;

import com.group6.chemicalstoragemanagement.entity.Cabinet;
import com.group6.chemicalstoragemanagement.entity.Chemical;

import java.util.ArrayList;

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


    @Override
    public void delete(Cabinet entity) {
        for(Chemical chemical : entity.getAllChemical()){
            ChemicalRepository.getInstance().delete(chemical);
        }

        super.delete(entity);
    }
}
