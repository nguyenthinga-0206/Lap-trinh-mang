package service.impl;

import dao.DepartmentDAO;
import dao.EmployeeDAO;
import model.Department;
import model.Employee;
import service.DepartmentService;
import service.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    private EmployeeService employeeService = new EmployeeServiceImpl();
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    public static final String SAVE_DIRECTORY = "uploadExcel";


    public void listDepartments(HttpServletRequest request, HttpServletResponse response) {
        List<Department> departments = departmentDAO.selectAllDepartments();
        request.setAttribute("departments", departments);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/department/list.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/department/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDepartment(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String name = request.getParameter("name");
        Department department = new Department(name);
        this.departmentDAO.insertDepartment(department);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/department/create.jsp");
        request.setAttribute("message", "Phòng ban mới đã được thêm");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Department department = this.departmentDAO.selectDepartment(id);
        RequestDispatcher dispatcher;
        if (department == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("department", department);
            dispatcher = request.getRequestDispatcher("WEB-INF/views/department/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateDepartment(HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Department department = this.departmentDAO.selectDepartment(id);
        RequestDispatcher dispatcher;
        if (department == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            department.setName(name);
            this.departmentDAO.updateDepartment(department);
            request.setAttribute("department", department);
            request.setAttribute("message", "Thông tin của phòng ban đã được cập nhật");
            dispatcher = request.getRequestDispatcher("WEB-INF/views/department/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Department department = this.departmentDAO.selectDepartment(id);
        RequestDispatcher dispatcher;
        if (department == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("department", department);
            dispatcher = request.getRequestDispatcher("WEB-INF/views/department/delete.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Department department = this.departmentDAO.selectDepartment(id);
        RequestDispatcher dispatcher;
        if (department == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            this.departmentDAO.deleteDepartment(id);
        }
        try {
            response.sendRedirect("/departments");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showEmployeeByDepartmentId(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        this.employeeService.selectAllEmployeeByDepartmentId(request, response);
    }

    @Override
    public void showMoveEmployeeForm(HttpServletRequest request, HttpServletResponse response) {
        this.employeeService.showMoveEmployeeForm(request, response);
    }

    @Override
    public void showUploadForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Department department = this.departmentDAO.selectDepartment(id);
        RequestDispatcher dispatcher;
        if (department == null) {
            dispatcher = request.getRequestDispatcher("error-404.jsp");
        } else {
            request.setAttribute("department", department);
            dispatcher = request.getRequestDispatcher("WEB-INF/views/department/upload.jsp");
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
    public void uploadFileExcel(HttpServletRequest request, HttpServletResponse response) {
        int department_id = Integer.parseInt(request.getParameter("id"));
        Department department = this.departmentDAO.selectDepartment(department_id);
        ReadExcelServiceImpl readExcelService = new ReadExcelServiceImpl();
        String filePath = "";
        try {
            String appPath = request.getServletContext().getRealPath("");
            appPath = appPath.replace('\\', '/');

            String fullSavePath = null;
            if (appPath.endsWith("/")) {
                fullSavePath = appPath + SAVE_DIRECTORY;
            } else {
                fullSavePath = appPath + "/" + SAVE_DIRECTORY;
            }

            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);
                if (fileName != null && fileName.length() > 0) {
                    filePath = fullSavePath + File.separator + fileName;

                    try {
                        part.write(filePath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            response.sendRedirect(request.getContextPath() + "/departments");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/WEB-INF/views/department/list.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        try {
            List<Employee> employees = readExcelService.readBooksFromExcelFile(filePath);
            for (Employee employee : employees) {
                employee.setDepartment(department);
                this.employeeDAO.insertEmployee(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
        int department_id = Integer.parseInt(request.getParameter("id"));
        Department department = this.departmentDAO.selectDepartment(department_id);
        WriteExcelServiceImpl writeExcelService = new WriteExcelServiceImpl();
        List<Employee> employees = this.employeeDAO.selectAllByDepartmentId(department_id);
        try {
            String excelFilePath = "E:\\TH_Lap_Trinh_Mang_Bai_3/export/" + department.getName() + ".xls";

            writeExcelService.writeExcel(employees, excelFilePath);
            List<Department> departments = departmentDAO.selectAllDepartments();
            request.setAttribute("departments", departments);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/department/list.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }


    static void validate(int age) {
        if (age < 18)
            throw new ArithmeticException("not valid");
        else
            System.out.println("welcome to vote");
    }



}

