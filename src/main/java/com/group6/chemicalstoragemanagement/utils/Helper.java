package com.group6.chemicalstoragemanagement.utils;

import com.group6.chemicalstoragemanagement.entity.Cabinet;
import com.group6.chemicalstoragemanagement.entity.Chemical;
import com.group6.chemicalstoragemanagement.entity.Name;
import com.group6.chemicalstoragemanagement.repository.CabinetRepository;
import com.group6.chemicalstoragemanagement.repository.ChemicalRepository;
import com.group6.chemicalstoragemanagement.repository.NameRepository;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.time.LocalDate;

public class Helper {
    public static File pickLocation() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.setInitialFileName("Chemicals.xlsx");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.xlsx*"));

        return fileChooser.showSaveDialog(new Stage());

    }

    private static void exportName(XSSFSheet sheetName) {
        int rownum = 0;

        Row rowTemp = sheetName.createRow(rownum++);
        rowTemp.createCell(0).setCellValue("ID");
        rowTemp.createCell(1).setCellValue("Name");
        rowTemp.createCell(2).setCellValue("Create At");

        for (Name name : NameRepository.getInstance().getAll()) {
            Row row = sheetName.createRow(rownum++);

            row.createCell(0).setCellValue(name.getID());
            row.createCell(1).setCellValue(name.getName());
            row.createCell(2).setCellValue(name.getCreateAt().toString());
        }
    }

    private static void exportChemical(XSSFSheet sheetName) {
        int rownum = 0;

        Row rowTemp = sheetName.createRow(rownum++);
        rowTemp.createCell(0).setCellValue("ID");
        rowTemp.createCell(1).setCellValue("Name");
        rowTemp.createCell(2).setCellValue("Cabinet");
        rowTemp.createCell(3).setCellValue("Weight");
        rowTemp.createCell(4).setCellValue("Expiration");
        rowTemp.createCell(5).setCellValue("MinTemp");
        rowTemp.createCell(6).setCellValue("MaxTemp");
        rowTemp.createCell(7).setCellValue("Create At");
        rowTemp.createCell(8).setCellValue("Status");
        rowTemp.createCell(9).setCellValue("Note");

        for (Chemical chemical : ChemicalRepository.getInstance().getAll()) {
            Row row = sheetName.createRow(rownum++);

            row.createCell(0).setCellValue(chemical.getID());
            row.createCell(1).setCellValue(chemical.getName().getID());
            row.createCell(2).setCellValue(chemical.getCabinet().getID());
            row.createCell(3).setCellValue(chemical.getWeight());
            row.createCell(4).setCellValue(chemical.getExpiration().toString());
            row.createCell(5).setCellValue(chemical.getMinTemp());
            row.createCell(6).setCellValue(chemical.getMaxTemp());
            row.createCell(7).setCellValue(chemical.getCreateAt().toString());
            row.createCell(8).setCellValue(chemical.getStatus());
            row.createCell(9).setCellValue(chemical.getNote());

        }
    }

    private static void exportCabinet(XSSFSheet sheetName) {
        int rownum = 0;

        Row rowTemp = sheetName.createRow(rownum++);
        rowTemp.createCell(0).setCellValue("ID");
        rowTemp.createCell(1).setCellValue("Name");
        rowTemp.createCell(2).setCellValue("Temp");
        rowTemp.createCell(3).setCellValue("Capacity");
        rowTemp.createCell(4).setCellValue("Create At");
        rowTemp.createCell(5).setCellValue("Status");

        for (Cabinet name : CabinetRepository.getInstance().getAll()) {
            Row row = sheetName.createRow(rownum++);

            row.createCell(0).setCellValue(name.getID());
            row.createCell(1).setCellValue(name.getName());
            row.createCell(2).setCellValue(name.getTemp());
            row.createCell(3).setCellValue(name.getCapacity());
            row.createCell(4).setCellValue(name.getCreateAt().toString());
            row.createCell(5).setCellValue(name.getStatus());

        }
    }

    public static void exportData() {

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheetName = workbook.createSheet("name");
            XSSFSheet sheetCabinet = workbook.createSheet("cabinet");
            XSSFSheet sheetChemical = workbook.createSheet("chemical");

            exportName(sheetName);
            exportCabinet(sheetCabinet);
            exportChemical(sheetChemical);

            FileOutputStream out = new FileOutputStream(pickLocation());
            workbook.write(out);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File pickFile() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.xlsx*"));

        return fileChooser.showOpenDialog(new Stage());
    }

    private static void importName(XSSFSheet sheet) throws ParseException {
        NameRepository.getInstance().clear();
        sheet.removeRow(sheet.getRow(0));
        for (Row row : sheet) {

            long id = (long) row.getCell(0).getNumericCellValue();
            String name = row.getCell(1).getStringCellValue();
            LocalDate date = LocalDate.parse(row.getCell(2).getStringCellValue());

            Name nameScene = new Name(name);
            nameScene.setID(id);
            nameScene.setCreateAt(date);
            NameRepository.getInstance().add(nameScene);
        }
    }

    private static void importCabinet(XSSFSheet sheet) {
        CabinetRepository.getInstance().clear();
        sheet.removeRow(sheet.getRow(0));
        for (Row row : sheet) {

            long id = (long) row.getCell(0).getNumericCellValue();
            String name = row.getCell(1).getStringCellValue();
            float temp = (float) row.getCell(2).getNumericCellValue();
            float capacity = (float) row.getCell(3).getNumericCellValue();
            LocalDate date = LocalDate.parse(row.getCell(4).getStringCellValue());

            Cabinet cabinetScene = new Cabinet(name, temp, capacity);
            cabinetScene.setID(id);
            cabinetScene.setCreateAt(date);
            CabinetRepository.getInstance().add(cabinetScene);
        }
    }

    private static void importChemical(XSSFSheet sheet) {
        ChemicalRepository.getInstance().clear();
        sheet.removeRow(sheet.getRow(0));
        for (Row row : sheet) {

            long id = (long) row.getCell(0).getNumericCellValue();
            Name name = NameRepository.getInstance().getById((long) (row.getCell(1).getNumericCellValue()));
            Cabinet cabinet = CabinetRepository.getInstance().getById((long) (row.getCell(2).getNumericCellValue()));
            float weight = (float) row.getCell(3).getNumericCellValue();
            LocalDate expiration = LocalDate.parse(row.getCell(4).getStringCellValue());
            float min = (float) row.getCell(5).getNumericCellValue();
            float max = (float) row.getCell(6).getNumericCellValue();
            LocalDate date = LocalDate.parse(row.getCell(7).getStringCellValue());


            Chemical chemical = new Chemical(name, cabinet, weight, min, max, expiration);
            chemical.setID(id);
            chemical.setCreateAt(date);
            ChemicalRepository.getInstance().add(chemical);
        }
    }

    public static void importData() {

        try {
            FileInputStream file = new FileInputStream(pickFile());

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheetName = workbook.getSheetAt(0);
            XSSFSheet sheetCabinet = workbook.getSheetAt(1);
            XSSFSheet sheetChemical = workbook.getSheetAt(2);

            importName(sheetName);
            importCabinet(sheetCabinet);
            importChemical(sheetChemical);

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
