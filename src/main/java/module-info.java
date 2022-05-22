module com.group6.chemicalstoragemanagement {
    requires javafx.controls;
    requires javafx.fxml;
//    requires com.fasterxml.uuid;


    opens com.group6.chemicalstoragemanagement to javafx.fxml;
    exports com.group6.chemicalstoragemanagement;
    exports com.group6.chemicalstoragemanagement.controller;
    opens com.group6.chemicalstoragemanagement.controller to javafx.fxml;
}