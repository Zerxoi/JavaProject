package xyz.zerxoi.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo {
    public static void main(String[] args) {
        // Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo", "root", "6019");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from t_user;");

            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getInt("id"));
                System.out.println("username: " + resultSet.getString("username"));
                System.out.println("password: " + resultSet.getString("password"));
                System.out.println("email: " + resultSet.getString("email"));
                System.out.println("==============================================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
