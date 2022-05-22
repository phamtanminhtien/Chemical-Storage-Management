package com.group6.chemicalstoragemanagement.repository;

import com.group6.chemicalstoragemanagement.entity.Name;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Repository<T> {
    private final ObservableList<Object> list =  FXCollections.observableArrayList();

    public void add(T entity){
        list.add(entity);
    }

    public void delete(T entity){
        list.remove(entity);
    }

    public ArrayList<T> getAll(){
        return (ArrayList<T>) list;
    }
    public ObservableList<Object> getObservableList(){
        return list;
    }

//    public Entity getByID(UUID ID){
//        for (Entity entity : list) {
//            if (ID.equals(entity.getID())) {
//                return entity;
//            }
//        }
//        return null;
//    }
}
