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
import java.time.LocalDate;
import java.time.Period;
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
    private TableView<Name> tableName;
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
    private TableView<Cabinet> cabinetTable;
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
    private Button chemicalAddBtn;
    @FXML
    private Button chemicalDeleteBtn;
    @FXML
    private ChoiceBox<Cabinet> chemicalCabinet;
    @FXML
    private ChoiceBox<Name> chemicalName;
    @FXML
    private DatePicker chemicalDate;
    @FXML
    private Slider chemicalMinTemp;
    @FXML
    private Slider chemicalMaxTemp;
    @FXML
    private Slider chemicalWeight;
    @FXML
    private TableView<Chemical> chemicalTable;
    @FXML
    private TableColumn<Chemical, Object> chemicalIDCol;
    @FXML
    private TableColumn<Chemical, Object> chemicalNameCol;
    @FXML
    private TableColumn<Chemical, Object> chemicalCabinetCol;
    @FXML
    private TableColumn<Chemical, Object> chemicalMinTempCol;
    @FXML
    private TableColumn<Chemical, Object> chemicalMaxTempCol;
    @FXML
    private TableColumn<Chemical, Object> chemicalExpirationCol;
    @FXML
    private TableColumn<Chemical, Object> chemicalStatusCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameInitialize();
        cabinetInitialize();
        chemicalInitialize();
    }
    private void chemicalInitialize(){

        chemicalCabinet.setItems(CabinetRepository.getInstance().getObservableList());
        chemicalName.setItems(NameRepository.getInstance().getObservableList());

        chemicalIDCol.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getID();
            }
        });
        chemicalNameCol.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getName().getName();
            }
        });

        chemicalCabinetCol.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getCabinet().getName();
            }
        });

        chemicalMinTempCol.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getMinTemp();
            }
        });

        chemicalMaxTempCol.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getMaxTemp();
            }
        });
        chemicalExpirationCol.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getExpiration().toString();
            }
        });


        chemicalStatusCol.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getStatus();
            }
        });


        chemicalTable.setItems(ChemicalRepository.getInstance().getObservableList());

        chemicalAddBtn.setOnAction(actionEvent -> {
            Chemical newChemical = new Chemical(chemicalName.getValue(), chemicalCabinet.getValue(), (float) chemicalWeight.getValue(), (float) chemicalMinTemp.getValue(), (float) chemicalMaxTemp.getValue(), chemicalDate.getValue());
            ChemicalRepository.getInstance().add(newChemical);
            cabinetName.setText("");
        });
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