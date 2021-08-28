package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public interface DepartmentService {
    void showCreateForm(HttpServletRequest request, HttpServletResponse response);

    void createDepartment(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    void listDepartments(HttpServletRequest request, HttpServletResponse response);

    void showEditForm(HttpServletRequest request, HttpServletResponse response);

    void updateDepartment(HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException;

    void showDeleteForm(HttpServletRequest request, HttpServletResponse response);

    void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    void showEmployeeByDepartmentId(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    void showMoveEmployeeForm(HttpServletRequest request, HttpServletResponse response);

    void showUploadForm(HttpServletRequest request, HttpServletResponse response);

    void uploadFileExcel(HttpServletRequest request, HttpServletResponse response);

    void exportExcel(HttpServletRequest request, HttpServletResponse response);
}
