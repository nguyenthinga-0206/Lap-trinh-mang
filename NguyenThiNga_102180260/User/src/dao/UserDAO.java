package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/data";
    private String jdbcUsername = "root";
    private String jdbcPassword = "02062000";

    private static final String INSERT_USERS_SQL = "INSERT INTO test1" + "  (roles, username, password, firsname, lastname) VALUES " + " (?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "select id,roles,username,password, firsname, lastname from test1 where id =?";
    private static final String SELECT_USER_BY_USER = "select * from test1 where username =? and password =?";
    private static final String SELECT_ALL_USERS = "select * from test1";
    private static final String DELETE_USERS_SQL = "delete from test1 where id = ?;";
    private static final String UPDATE_USERS_SQL = "update test1 set roles = ?,username= ?, password =?, firsname =?, lastname=? where id = ?;";

    public UserDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean checkLogin(String username, String password) throws SQLException {
        boolean check = false;
        User user = null;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_USER);
            statement.setString(1, username);
            statement.setString(2, password);
//            Thuc thi cau lenh truy van
            ResultSet resultSet = statement.executeQuery();
//            Truong hop dang nhap thanh cong
            if (resultSet.next()) {
                check = true;
            } else {
                check = false ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
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
