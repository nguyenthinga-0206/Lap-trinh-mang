package dao;

import model.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/th3?useUnicode=true&characterEncoding=UTF-8";
    private String jdbcUsername = "root";
    private String jdbcPassword = "12345678";

    private static final String INSERT_DEPARTMENT_SQL = "INSERT INTO departments" + "  (name) VALUES " +
            " (?);";

    private static final String SELECT_DEPARTMENT_BY_ID = "select id,name from departments where id =?";
    private static final String SELECT_ALL_DEPARTMENT = "select * from departments";
    private static final String DELETE_DEPARTMENT_SQL = "delete from departments where id = ?;";
    private static final String UPDATE_DEPARTMENT_SQL = "update departments set name = ? where id = ?;";

    public DepartmentDAO() {
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

    public void insertDepartment(Department department) throws SQLException {
        System.out.println(INSERT_DEPARTMENT_SQL);
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEPARTMENT_SQL)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Department selectDepartment(int id) {
        Department department = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEPARTMENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                department = new Department(id, name);
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return department;
    }

    public List<Department> selectAllDepartments() {

        List<Department> departments = new ArrayList<>();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DEPARTMENT)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                departments.add(new Department(id, name));
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return departments;
    }

    public boolean deleteDepartment(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_DEPARTMENT_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
            connection.close();
        }
        return rowDeleted;
    }

    public boolean updateDepartment(Department department) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_DEPARTMENT_SQL);) {
            statement.executeQuery("SET NAMES 'UTF8'");
            statement.executeQuery("SET CHARACTER SET 'UTF8'");
            statement.setString(1, department.getName());
            statement.setInt(2, department.getId());
            rowUpdated = statement.executeUpdate() > 0;
            connection.close();
        }
        return rowUpdated;
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
