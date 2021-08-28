package service.impl;

import model.Employee;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadExcelServiceImpl {
    public List<Employee> readBooksFromExcelFile(String excelFilePath) throws IOException {
        System.out.println(excelFilePath);
        List<Employee> employees = new ArrayList<>();
        FileInputStream inputStream = new FileInputStream(excelFilePath);

        Workbook workBook = getWorkbook(inputStream, excelFilePath);
        Sheet firstSheet = workBook.getSheetAt(0);
        Iterator<Row> rows = firstSheet.iterator();

        while (rows.hasNext()) {
            Row row = rows.next();
            Iterator<Cell> cells = row.cellIterator();
            Employee employee=new Employee();

            while (cells.hasNext()) {
                Cell cell = cells.next();
                int columnIndex = cell.getColumnIndex();

                switch (columnIndex) {
                    case 0:
                        employee.setName((String) getCellValue(cell));
                        break;
                    case 1:
                        employee.setAddress((String) getCellValue(cell));
                        break;
                    case 2:
                        employee.setEmail((String) getCellValue(cell));
                        break;
                    case 3:

                        employee.setPhone((String)getCellValue(cell));
                        break;
                    case 4:
                        employee.setPosition((String) getCellValue(cell));
                        break;
                    case 5:
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate tempDay = LocalDate.parse((String)getCellValue(cell), formatter);
                        employee.setBirthday(tempDay);
                        break;
                }
            }
            employees.add(employee);
        }

        workBook.close();
        inputStream.close();

        return employees;
    }

    public Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();

            case BOOLEAN:
                return cell.getBooleanCellValue();

            case NUMERIC:
                return cell.getNumericCellValue();
        }

        return null;
    }

    public Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;

        if(excelFilePath.endsWith("xlsx")) {
            workbook=new XSSFWorkbook(inputStream);
        }
        else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    public static void main(String[] args) {
        ReadExcelServiceImpl readExcelService=new ReadExcelServiceImpl();
        try {
            List<Employee> employees=readExcelService.readBooksFromExcelFile("D:\\test1.xlsx");
            System.out.println("hello");
            System.out.println(employees);
            for(Employee employee:employees) {
                System.out.println(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
