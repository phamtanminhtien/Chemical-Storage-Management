package com.group6.chemicalstoragemanagement.repository;

import com.group6.chemicalstoragemanagement.entity.Cabinet;
import com.group6.chemicalstoragemanagement.entity.Chemical;
import com.group6.chemicalstoragemanagement.entity.Name;

import java.util.ArrayList;
import java.util.Objects;

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

    public Cabinet getById(Long ID){

        for(Cabinet cabinet : getAll()){
            if(Objects.equals(ID, cabinet.getID())) return cabinet;
        }

        return null;
    }
}
