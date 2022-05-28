module com.group6.chemicalstoragemanagement {
    requires javafx.controls;
    requires javafx.fxml;
//    requires com.fasterxml.uuid;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens com.group6.chemicalstoragemanagement to javafx.fxml;
    exports com.group6.chemicalstoragemanagement;
    exports com.group6.chemicalstoragemanagement.controller;
    opens com.group6.chemicalstoragemanagement.controller to javafx.fxml;
}