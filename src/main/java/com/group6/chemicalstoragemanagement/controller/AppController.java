package com.group6.chemicalstoragemanagement.controller;

import com.group6.chemicalstoragemanagement.entity.Cabinet;
import com.group6.chemicalstoragemanagement.entity.Chemical;
import com.group6.chemicalstoragemanagement.entity.Name;
import com.group6.chemicalstoragemanagement.repository.CabinetRepository;
import com.group6.chemicalstoragemanagement.repository.ChemicalRepository;
import com.group6.chemicalstoragemanagement.repository.NameRepository;
import com.group6.chemicalstoragemanagement.utils.Helper;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
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
    private TableColumn<Cabinet, Object> cabinetIDCol;
    @FXML
    private TableColumn<Cabinet, Object> cabinetTempCol;
    @FXML
    private TableColumn<Cabinet, Object> cabinetCapacityCol;
    @FXML
    private TableColumn<Cabinet, Object> cabinetNameCol;
    @FXML
    private TableColumn<Cabinet, Object> cabinetOverLoadCol;

    //Chemical
    @FXML
    private Button chemicalExportBtn;
    @FXML
    private Button chemicalImportBtn;
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
    @FXML
    private TableColumn<Chemical, Object> chemicalWeightCol;
    @FXML
    private TableColumn<Chemical, Object> chemicalNoteCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        nameInitialize();
        cabinetInitialize();
        chemicalInitialize();
        action();
    }

    private void action() {
        chemicalExportBtn.setOnAction(actionEvent -> {
            Helper.exportNameData();
        });

        chemicalImportBtn.setOnAction(actionEvent -> {
            Helper.importNameData();
        });
    }



    private void initData() {
        Name name1 = new Name("CO2");
        Name name2 = new Name("CaCo3");
        NameRepository.getInstance().add(name1);
        NameRepository.getInstance().add(name2);

        Cabinet cabinet1 = new Cabinet("A1", 25, 10000);
        Cabinet cabinet2 = new Cabinet("A2", 25, 20000);
        CabinetRepository.getInstance().add(cabinet1);
        CabinetRepository.getInstance().add(cabinet2);


        Chemical chemical1 = new Chemical(name1, cabinet1, 500, 10, 40, LocalDate.now());
        Chemical chemical2 = new Chemical(name2, cabinet2, 1000, 5, 30, LocalDate.now());

        ChemicalRepository.getInstance().add(chemical1);
        ChemicalRepository.getInstance().add(chemical2);
    }

    private void chemicalInitialize() {

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
        chemicalNoteCol.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getNote();
            }
        });

        chemicalStatusCol.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getStatus();
            }
        });

        chemicalWeightCol.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getWeight();
            }
        });


        chemicalTable.setItems(ChemicalRepository.getInstance().getObservableList());

        chemicalAddBtn.setOnAction(actionEvent -> {
            Chemical newChemical = new Chemical(chemicalName.getValue(), chemicalCabinet.getValue(), (float) chemicalWeight.getValue(), (float) chemicalMinTemp.getValue(), (float) chemicalMaxTemp.getValue(), chemicalDate.getValue());
            ChemicalRepository.getInstance().add(newChemical);

            cabinetTable.refresh();
        });

        chemicalDeleteBtn.setOnAction(actionEvent -> {
            Chemical selected = (Chemical) chemicalTable.getSelectionModel().getSelectedItem();
            ChemicalRepository.getInstance().delete(selected);
        });

    }

    private void cabinetInitialize() {
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

        cabinetOverLoadCol.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getStatus();
            }
        });

        cabinetTable.setItems(CabinetRepository.getInstance().getObservableList());

        cabinetAddBtn.setOnAction(actionEvent -> {
            CabinetRepository.getInstance().add(new Cabinet(cabinetName.getText(), (float) cabinetTemp.getValue(), (float) cabinetCapacity.getValue()));
            cabinetName.setText("");
            cabinetTable.refresh();
        });

        cabinetDeleteBtn.setOnAction(actionEvent -> {
            Cabinet selected = (Cabinet) cabinetTable.getSelectionModel().getSelectedItem();
            CabinetRepository.getInstance().delete(selected);
        });
    }

    private void nameInitialize() {
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