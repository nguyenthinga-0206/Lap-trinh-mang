package service;

import model.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface EmployeeService {
    void showListEmployee(HttpServletRequest request, HttpServletResponse response);

    void showFormCreate(HttpServletRequest request, HttpServletResponse response);

    void createEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException;

    void showFormEdit(HttpServletRequest request, HttpServletResponse response);

    void editEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    void showFormDelete(HttpServletRequest request, HttpServletResponse response);

    void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    void selectAllEmployeeByDepartmentId(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    void deleteAllEmployeeByDepartmentId(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    void showMoveEmployeeForm(HttpServletRequest request, HttpServletResponse response);

    void moveEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    void showInfoEmployee(HttpServletRequest request, HttpServletResponse response);
}
