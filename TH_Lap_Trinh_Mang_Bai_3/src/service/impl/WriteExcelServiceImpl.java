package service.impl;

import dao.EmployeeDAO;
import model.Employee;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WriteExcelServiceImpl {
    private Workbook getWorkbook(String excelFilePath)
            throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    public void writeExcel(List<Employee> employees, String excelFilePath) throws IOException {
        Workbook workbook = getWorkbook(excelFilePath);
        Sheet sheet = workbook.createSheet("employee");
        createHeaderRow(sheet);
        int rowCount = 0;

        for (Employee employee : employees) {
            Row row = sheet.createRow(++rowCount);
            writeBook(employee, row);
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
            workbook.write(outputStream);
        }
    }

    private void writeBook(Employee employee, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(employee.getId());

        cell = row.createCell(1);
        cell.setCellValue(employee.getName());

        cell = row.createCell(2);
        cell.setCellValue(employee.getAddress());

        cell = row.createCell(3);
        cell.setCellValue(employee.getEmail());

        cell = row.createCell(4);
        cell.setCellValue(employee.getPhone());

        cell = row.createCell(5);
        cell.setCellValue(employee.getPosition());

        cell = row.createCell(6);
        cell.setCellValue(String.valueOf(employee.getBirthday()));

        cell = row.createCell(7);
        cell.setCellValue(employee.getDepartment().getName());
    }

    private void createHeaderRow(Sheet sheet) {

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 11);
        cellStyle.setFont(font);

        Row row = sheet.createRow(0);

        Cell cellID = row.createCell(0);
        cellID.setCellStyle(cellStyle);
        cellID.setCellValue("ID");

        Cell cellName = row.createCell(1);
        cellName.setCellStyle(cellStyle);
        cellName.setCellValue("Name");

        Cell cellAddress = row.createCell(2);
        cellAddress.setCellStyle(cellStyle);
        cellAddress.setCellValue("Address");

        Cell cellEmail = row.createCell(3);
        cellEmail.setCellStyle(cellStyle);
        cellEmail.setCellValue("Email");

        Cell cellPhone = row.createCell(4);
        cellPhone.setCellStyle(cellStyle);
        cellPhone.setCellValue("Phone");

        Cell cellPosition = row.createCell(5);
        cellPosition.setCellStyle(cellStyle);
        cellPosition.setCellValue("Position");

        Cell cellBirthday = row.createCell(6);
        cellBirthday.setCellStyle(cellStyle);
        cellBirthday.setCellValue("Birthday");

        Cell cellDepartment = row.createCell(7);
        cellDepartment.setCellStyle(cellStyle);
        cellDepartment.setCellValue("Department");
    }
}
