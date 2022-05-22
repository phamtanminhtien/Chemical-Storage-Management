package com.group6.chemicalstoragemanagement.controller;

import com.group6.chemicalstoragemanagement.entity.Cabinet;
import com.group6.chemicalstoragemanagement.entity.Chemical;
import com.group6.chemicalstoragemanagement.entity.Name;
import com.group6.chemicalstoragemanagement.repository.CabinetRepository;
import com.group6.chemicalstoragemanagement.repository.ChemicalRepository;
import com.group6.chemicalstoragemanagement.repository.NameRepository;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;

public class AppController implements Initializable {


    //Name
    @FXML
    private TextField nameInput;
    @FXML
    private Button nameAddNameBtn;
    @FXML
    private Button nameDeleteBtn;
    @FXML
    private TableView<Object> tableName;
    @FXML
    private TableColumn<Name, Object> nameID;
    @FXML
    private TableColumn<Name, Object> nameName;

    //Cabinet
    @FXML
    private TextField cabinetName;
    @FXML
    private Button cabinetAddBtn;
    @FXML
    private Button cabinetDeleteBtn;
    @FXML
    private Slider cabinetTemp;
    @FXML
    private Slider cabinetCapacity;
    @FXML
    private TableView<Object> cabinetTable;
    @FXML
    private  TableColumn<Cabinet, Object> cabinetIDCol;
    @FXML
    private TableColumn<Cabinet, Object> cabinetTempCol;
    @FXML
    private TableColumn<Cabinet, Object> cabinetCapacityCol;
    @FXML
    private TableColumn<Cabinet, Object> cabinetNameCol;

    //Chemical
    @FXML
    private ChoiceBox<Object> chemicalCabinet;
    @FXML
    private ChoiceBox<Object> chemicalName;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameInitialize();
        cabinetInitialize();
        chemicalInitialize();
    }
    private void chemicalInitialize(){
        chemicalCabinet.setItems(CabinetRepository.getInstance().getObservableList());
        chemicalName.setItems(NameRepository.getInstance().getObservableList());
    }
    private void cabinetInitialize(){
        cabinetIDCol.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getID();
            }
        });
        cabinetNameCol.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getName();
            }
        });
        cabinetTempCol.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getTemp();
            }
        });
        cabinetCapacityCol.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getCapacity();
            }
        });

        cabinetTable.setItems(CabinetRepository.getInstance().getObservableList());

        cabinetAddBtn.setOnAction(actionEvent -> {
            CabinetRepository.getInstance().add(new Cabinet(cabinetName.getText(), (float) cabinetTemp.getValue(), (float) cabinetCapacity.getValue()) );
            cabinetName.setText("");
        });

        cabinetDeleteBtn.setOnAction(actionEvent -> {
            Cabinet selected = (Cabinet) cabinetTable.getSelectionModel().getSelectedItem();
            CabinetRepository.getInstance().delete(selected);
        });
    }
    private void nameInitialize(){
        nameID.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getID();
            }
        });

        nameName.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getName();
            }
        });

        tableName.setItems(NameRepository.getInstance().getObservableList());

        nameAddNameBtn.setOnAction(actionEvent -> {
            Name newName = new Name(nameInput.getText());
            NameRepository.getInstance().add(newName);
            nameInput.setText("");
        });
        nameDeleteBtn.setOnAction(actionEvent -> {
            Name selected = (Name) tableName.getSelectionModel().getSelectedItem();
            NameRepository.getInstance().delete(selected);
        });

    }


    //    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//        //Name Table
//
//        addNameBtn.setOnAction(actionEvent -> {
//            NameRepository.getInstance().add(new Name(nameInput.getText()));
//            reLoadNameTable();
//        });
//
//        nameID.setCellValueFactory(new MapValueFactory<>("ID"));
//        nameName.setCellValueFactory(new MapValueFactory<>("name"));
//
//        NameRepository.getInstance().getAll().forEach(item -> {
//            listName.add(Entity.parameters(item));
//        });
//
//        tableName.setItems(listName);
//        //Chemical
//        chemicalName.setItems(listName);
//    }
//
//    private void reLoadNameTable() {
//
//        listName.clear();
//        NameRepository.getInstance().getAll().forEach(item -> {
//            listName.add(Entity.parameters(item));
//        });
//
//    }
}