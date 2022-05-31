package com.group6.chemicalstoragemanagement.controller;

import com.group6.chemicalstoragemanagement.entity.Cabinet;
import com.group6.chemicalstoragemanagement.entity.Chemical;
import com.group6.chemicalstoragemanagement.entity.Name;
import com.group6.chemicalstoragemanagement.repository.CabinetRepository;
import com.group6.chemicalstoragemanagement.repository.ChemicalRepository;
import com.group6.chemicalstoragemanagement.repository.NameRepository;
import com.group6.chemicalstoragemanagement.utils.Helper;
import javafx.beans.value.ObservableValueBase;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class AppController implements Initializable {
    @FXML
    public VBox panel;

    //    Menu
    @FXML
    public MenuItem closeMenuBar;
    @FXML
    public MenuItem importMenuBar;
    @FXML
    public MenuItem exportMenuBar;
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
    @FXML
    private TableColumn<Name, Object> nameCreateAtCol;

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
    @FXML
    private TableColumn<Cabinet, Object> cabinetCreateAtCol;

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
    @FXML
    private TableColumn<Chemical, Object> chemicalWeightCol;
    @FXML
    private TableColumn<Chemical, Object> chemicalNoteCol;
    @FXML
    private TableColumn<Chemical, Object> chemicalCreateAtCol;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        initData();
        menu();
        nameInitialize();
        cabinetInitialize();
        chemicalInitialize();
    }
    private void menu(){
        closeMenuBar.setOnAction(actionEvent -> {
            Stage stage = (Stage) panel.getScene().getWindow();
            stage.close();
        });

        exportMenuBar.setOnAction(actionEvent -> {
            Helper.exportData();
        });

        importMenuBar.setOnAction(actionEvent -> {
            Helper.importData();
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
        chemicalCreateAtCol.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getCreateAt().toString();
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

        cabinetCreateAtCol.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getCreateAt().toString();
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

        nameCreateAtCol.setCellValueFactory(nameObjectCellDataFeatures -> new ObservableValueBase<>() {
            @Override
            public Object getValue() {
                return nameObjectCellDataFeatures.getValue().getCreateAt().toString();
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

}