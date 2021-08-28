package controllers;


import model.Employee;
import service.DepartmentService;
import service.EmployeeService;
import service.impl.DepartmentServiceImpl;
import service.impl.EmployeeServiceImpl;
import service.impl.ReadExcelServiceImpl;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/departments")
@MultipartConfig
public class DepartmentServlet extends javax.servlet.http.HttpServlet {
    public DepartmentService departmentService = new DepartmentServiceImpl();
    public EmployeeService employeeService = new EmployeeServiceImpl();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                try {
                    this.departmentService.createDepartment(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "edit": {
                try {
                    this.departmentService.updateDepartment(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "delete": {
                try {
                    this.employeeService.deleteAllEmployeeByDepartmentId(request, response);
                    this.departmentService.deleteDepartment(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "move": {
                try {
                    this.employeeService.moveEmployee(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case "upload": {
                this.departmentService.uploadFileExcel(request,response);
            }
            default:
                break;
            }

    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                this.departmentService.showCreateForm(request, response);
                break;
            }
            case "edit": {
                this.departmentService.showEditForm(request, response);
                break;
            }
            case "delete": {
                this.departmentService.showDeleteForm(request, response);
                break;
            }
            case "view": {
                try {
                    this.departmentService.showEmployeeByDepartmentId(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            case "move": {
                this.employeeService.showMoveEmployeeForm(request, response);
                break;
            }
            case "upload": {
                this.departmentService.showUploadForm(request, response);
                break;
            }
            case "export": {
                this.departmentService.exportExcel(request, response);
                break;
            }
            default:
                this.departmentService.listDepartments(request, response);
                break;

        }
    }
}
