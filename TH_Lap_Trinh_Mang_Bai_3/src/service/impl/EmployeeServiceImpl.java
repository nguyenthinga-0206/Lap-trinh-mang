package service.impl;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import model.Department;
import model.Employee;
import service.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    final static String STRING_TO_DATE_FORMAT = "yyyy-MM-dd";
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private DepartmentDAO departmentDAO = new DepartmentDAO();

    @Override
    public void showListEmployee(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employees = this.employeeDAO.selectAllEmployee();
        request.setAttribute("employees", employees);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/employee/list.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFormCreate(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/employee/create.jsp");
        List<Department> departments = this.departmentDAO.selectAllDepartments();
        request.setAttribute("departments", departments);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String position = request.getParameter("position");
        String birthday = request.getParameter("birthday");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STRING_TO_DATE_FORMAT);
        LocalDate birthdayEmployee = LocalDate.parse(birthday, formatter);

        int department_id = Integer.parseInt(request.getParameter("departments_id"));
        Department department = this.departmentDAO.selectDepartment(department_id);

        Employee employee = new Employee(name, address, email, phone, position, birthdayEmployee, department);
        this.employeeDAO.insertEmployee(employee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/employee/create.jsp");
        request.setAttribute("message", "Nhân viên mới đã được thêm vào mới đã được thêm");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFormEdit(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.employeeDAO.selectEmployee(id);
        List<Department> departments = this.departmentDAO.selectAllDepartments();
        request.setAttribute("departments", departments);
        RequestDispatcher dispatcher;
        if (employee == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("employee", employee);
            dispatcher = request.getRequestDispatcher("WEB-INF/views/employee/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String position = request.getParameter("position");
        String birthday = request.getParameter("birthday");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STRING_TO_DATE_FORMAT);
        LocalDate birthdayEmployee = LocalDate.parse(birthday, formatter);

        int department_id = Integer.parseInt(request.getParameter("departments_id"));
        Department department = this.departmentDAO.selectDepartment(department_id);

        Employee employee = this.employeeDAO.selectEmployee(id);
        RequestDispatcher dispatcher;
        if (employee == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            employee.setName(name);
            employee.setAddress(address);
            employee.setEmail(email);
            employee.setPhone(phone);
            employee.setPosition(position);
            employee.setBirthday(birthdayEmployee);
            employee.setDepartment(department);
            this.employeeDAO.updateEmployee(employee);
            List<Department> departments = this.departmentDAO.selectAllDepartments();
            request.setAttribute("departments", departments);
            request.setAttribute("employee", employee);
            request.setAttribute("message", "Thông tin của nhân viên đã được cập nhật");
            dispatcher = request.getRequestDispatcher("WEB-INF/views/employee/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFormDelete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.employeeDAO.selectEmployee(id);
        RequestDispatcher dispatcher;
        if (employee == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("employee", employee);
            dispatcher = request.getRequestDispatcher("WEB-INF/views/employee/delete.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.employeeDAO.selectEmployee(id);
        RequestDispatcher dispatcher;
        if (employee == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            this.employeeDAO.deleteEmployee(id);
        }
        try {
            response.sendRedirect("/employees");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectAllEmployeeByDepartmentId(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int department_id = Integer.parseInt(request.getParameter("id"));
        Department department = this.departmentDAO.selectDepartment(department_id);
        request.setAttribute("department", department);
        List<Employee> employees = this.employeeDAO.selectAllByDepartmentId(department_id);
        request.setAttribute("employees", employees);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/employee/view.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllEmployeeByDepartmentId(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        this.employeeDAO.deleteAllEmployeeByDepartmentId(id);
    }

    @Override
    public void showMoveEmployeeForm(HttpServletRequest request, HttpServletResponse response) {
        int departmentId=Integer.parseInt((request.getParameter("id")));
        Department department = this.departmentDAO.selectDepartment(departmentId);
        request.setAttribute("department", department);
        List<Department> departments = this.departmentDAO.selectAllDepartments();
        request.setAttribute("departments", departments);
        List<Employee> employees=this.employeeDAO.selectAllByDepartmentId(departmentId);
        request.setAttribute("employees", employees);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/employee/move.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void moveEmployee(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("idEmployee"));
        System.out.println("id là: "+ id);
        Employee employee=this.employeeDAO.selectEmployee(id);
        int department_id = Integer.parseInt(request.getParameter("departments_id"));
        System.out.println("id departments: "+department_id);
        Department department = this.departmentDAO.selectDepartment(department_id);
        RequestDispatcher dispatcher;
        if (employee == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            Department department1=employee.getDepartment();
            employee.setDepartment(department);
            this.employeeDAO.updateEmployee(employee);
            request.setAttribute("department", department1);
            List<Department> departments = this.departmentDAO.selectAllDepartments();
            request.setAttribute("departments", departments);
            List<Employee> employees=this.employeeDAO.selectAllByDepartmentId(department1.getId());
            request.setAttribute("employees", employees);
            request.setAttribute("message", "Bạn đã chuyển thành công nhân viên " +employee.getName()+
                    " của phòng ban "+department1.getName()+ " sang " +department.getName()+"!");
            dispatcher = request.getRequestDispatcher("WEB-INF/views/employee/move.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showInfoEmployee(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = this.employeeDAO.selectEmployee(id);
        RequestDispatcher dispatcher;
        if (employee == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("employee", employee);
            dispatcher = request.getRequestDispatcher("WEB-INF/views/employee/viewEmployee.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
