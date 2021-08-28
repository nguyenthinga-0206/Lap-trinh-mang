package controllers;

import service.EmployeeService;
import service.impl.EmployeeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    private EmployeeService employeeService = new EmployeeServiceImpl();

    protected void doPost(HttpServletRequest request,HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                try {
                    this.employeeService.createEmployee(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "edit": {
                try {
                    this.employeeService.editEmployee(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            }
            case "delete": {
                try {
                    this.employeeService.deleteEmployee(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
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
                this.employeeService.showFormCreate(request, response);
                break;
            }
            case "edit": {
                this.employeeService.showFormEdit(request, response);
                break;
            }
            case "delete": {
                this.employeeService.showFormDelete(request, response);
                break;
            }
            case "viewEmployee": {
                this.employeeService.showInfoEmployee(request,response);
            }

            default:
                this.employeeService.showListEmployee(request, response);
                break;

        }
    }
}
