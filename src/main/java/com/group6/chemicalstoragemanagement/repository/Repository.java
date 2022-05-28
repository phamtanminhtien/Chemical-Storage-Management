package com.group6.chemicalstoragemanagement.repository;

import com.group6.chemicalstoragemanagement.entity.Name;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Repository<T> {
    private final ObservableList<T> list =  FXCollections.observableArrayList();
    private final ArrayList<T> arrayList =  new ArrayList<>();

    public void add(T entity){
        list.add(entity);
        arrayList.add(entity);
    }

    public void delete(T entity){
        list.remove(entity);
        arrayList.remove(entity);
    }
    public void clear(){
        list.clear();
        arrayList.clear();
    }
    public ArrayList<T> getAll(){
        return arrayList;
    }
    public ObservableList<T> getObservableList(){
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
