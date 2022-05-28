package com.group6.chemicalstoragemanagement.utils;

import com.group6.chemicalstoragemanagement.entity.Chemical;
import com.group6.chemicalstoragemanagement.entity.Name;
import com.group6.chemicalstoragemanagement.repository.ChemicalRepository;
import com.group6.chemicalstoragemanagement.repository.NameRepository;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

public class Helper {
    public static File pickLocation() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.setInitialFileName("Chemicals.xlsx");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.xlsx*"));

        return fileChooser.showSaveDialog(new Stage());

    }

    public static void exportNameData() {

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet("sheet1");// creating a blank sheet
            int rownum = 0;

            Row rowTemp = sheet.createRow(rownum++);
            rowTemp.createCell(0).setCellValue("ID");
            rowTemp.createCell(1).setCellValue("Name");
//            rowTemp.createCell(0).setCellValue("ID");
//            rowTemp.createCell(1).setCellValue("Name");
//            rowTemp.createCell(2).setCellValue("Weight");
//            rowTemp.createCell(3).setCellValue("Status");
//            rowTemp.createCell(4).setCellValue("Note");
//            rowTemp.createCell(5).setCellValue("Expiration");
//            rowTemp.createCell(6).setCellValue("MinTemp");
//            rowTemp.createCell(7).setCellValue("MaxTemp");

            for (Name name : NameRepository.getInstance().getAll()) {
                Row row = sheet.createRow(rownum++);

                row.createCell(0).setCellValue(name.getID());
                row.createCell(1).setCellValue(name.getName());
//                row.createCell(0).setCellValue(chemical.getID());
//                row.createCell(1).setCellValue(chemical.getName().getID());
//                row.createCell(2).setCellValue(chemical.getWeight());
//                row.createCell(3).setCellValue(chemical.getStatus());
//                row.createCell(4).setCellValue(chemical.getNote());
//                row.createCell(5).setCellValue(chemical.getExpiration());
//                row.createCell(6).setCellValue(chemical.getMinTemp());
//                row.createCell(7).setCellValue(chemical.getMaxTemp());

            }

            FileOutputStream out = new FileOutputStream(pickLocation()); // file name with path
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

    public static void importNameData() {

        try {
            FileInputStream file = new FileInputStream(pickFile());

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            sheet.removeRow(sheet.getRow(0));
            for (Row row : sheet) {
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType()) {
                        case NUMERIC -> System.out.print(cell.getNumericCellValue() + " - ");
                        case STRING -> System.out.print(cell.getStringCellValue() + " - ");
                    }
                }
                System.out.println("");
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
