package xyz.zerxoi.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtilsC3p0Demo {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtilsC3p0.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from t_user;");
            while (rs.next()) {
                System.out.println("id: " + rs.getInt("id"));
                System.out.println("username: " + rs.getString("username"));
                System.out.println("password: " + rs.getString("password"));
                System.out.println("email: " + rs.getString("email"));
                System.out.println("==============================================");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                JdbcUtilsC3p0.release(conn, st, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
