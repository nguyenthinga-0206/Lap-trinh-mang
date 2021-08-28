package dao;

import model.Department;
import model.Employee;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    final static String STRING_TO_DATE_FORMAT = "yyyy-MM-dd";

    private DepartmentDAO departmentDAO = new DepartmentDAO();

    private String jdbcURL = "jdbc:mysql://localhost:3306/th3";
    private String jdbcUsername = "root";
    private String jdbcPassword = "12345678";

    private static final String INSERT_EMPLOYEES_SQL = "INSERT INTO employees" + "  (name,address,email,phone,position,birthday,departments_id) VALUES " +
            " (?,?,?,?,?,?,?);";

    private static final String SELECT_EMPLOYEES_BY_ID = "select id,name,address,email,phone,position,birthday,departments_id from employees where id =?";
    private static final String SELECT_ALL_EMPLOYEES = "select * from employees inner join departments where employees.departments_id=departments.id;";
    private static final String DELETE_EMPLOYEES_SQL = "delete from employees where id = ?;";
    private static final String UPDATE_EMPLOYEES_SQL = "update employees set name = ?,address = ?,email = ?,phone = ?,position = ?,birthday = ?,departments_id=? where id = ?;";
    private static final String SELECT_EMPLOYEES_BY_DEPARTMENT_ID = "select id,name,address,email,phone,position,birthday,departments_id from employees where employees.departments_id=?;";
    private static final String DELETE_EMPLOYEE_SQL_BY_DEPARTMENT_ID = "delete from employees where departments_id = ?;";

    public EmployeeDAO() {
    }

    protected Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertEmployee(Employee employee) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEES_SQL)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getAddress());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setString(4, employee.getPhone());
            preparedStatement.setString(5, employee.getPosition());
            preparedStatement.setDate(6, Date.valueOf(employee.getBirthday()));
            preparedStatement.setInt(7, employee.getDepartment().getId());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }

    }

    public Employee selectEmployee(int id) {
        Employee employee = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEES_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String position = rs.getString("position");
                String birthday = rs.getString("birthday");
                int department_id = rs.getInt("departments_id");
                Department department = departmentDAO.selectDepartment(department_id);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STRING_TO_DATE_FORMAT);
                LocalDate birthdayEmployee = LocalDate.parse(birthday, formatter);

                employee = new Employee(id, name, address, email, phone, position, birthdayEmployee, department);

            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employee;
    }

    public List<Employee> selectAllEmployee() {

        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String position = rs.getString("position");
                String birthday = rs.getString("birthday");

                int departmentId = rs.getInt("departments_id");
                Department department = departmentDAO.selectDepartment(departmentId);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STRING_TO_DATE_FORMAT);
                LocalDate birthdayEmployee = LocalDate.parse(birthday, formatter);
                employees.add(new Employee(id, name, address, email, phone, position, birthdayEmployee, department));
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employees;
    }

    public boolean deleteEmployee(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEES_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
            connection.close();
        }
        return rowDeleted;
    }

    public boolean updateEmployee(Employee employee) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEES_SQL)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getAddress());
            statement.setString(3, employee.getEmail());
            statement.setString(4, employee.getPhone());
            statement.setString(5, employee.getPosition());
            statement.setDate(6, Date.valueOf(employee.getBirthday()));
            statement.setInt(7, employee.getDepartment().getId());
            statement.setInt(8, employee.getId());

            rowUpdated = statement.executeUpdate() > 0;
            connection.close();
        }
        return rowUpdated;
    }

    public List<Employee> selectAllByDepartmentId(int department_id) {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEES_BY_DEPARTMENT_ID)) {
            preparedStatement.setInt(1, department_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String position = rs.getString("position");
                String birthday = rs.getString("birthday");

                int departmentId = rs.getInt("departments_id");
                Department department = departmentDAO.selectDepartment(departmentId);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STRING_TO_DATE_FORMAT);
                LocalDate birthdayEmployee = LocalDate.parse(birthday, formatter);
                employees.add(new Employee(id, name, address, email, phone, position, birthdayEmployee, department));

            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return employees;
    }

    public boolean deleteAllEmployeeByDepartmentId(int departmentsId) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE_SQL_BY_DEPARTMENT_ID);) {
            statement.setInt(1, departmentsId);
            rowDeleted = statement.executeUpdate() > 0;
            connection.close();
            return rowDeleted;
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
class InvalidAgeException extends Exception {
    InvalidAgeException(String s) {
        super(s);
    }
}
class CustomExceptionExample {
    static void validate(int age) throws InvalidAgeException {
        if (age < 10) {
            throw new InvalidAgeException("Không đủ tuổi");
        } else {
            System.out.println("Chào mừng bạn đến với website");
        }
    }
    public static void main(String args[]) {
        try {
            validate(9);
        } catch (Exception m) {
            System.out.println("Lỗi: " + m);
        }

        System.out.println("Tiếp tục.");
    }
}
