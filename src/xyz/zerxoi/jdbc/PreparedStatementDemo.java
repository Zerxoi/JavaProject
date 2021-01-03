package xyz.zerxoi.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatementDemo {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try {
            conn = JdbcUtils.getConnection();

            // st = conn.prepareStatement("insert into t_user(`username`, `password`, `email`) values(?, ?, ?);");

            // st.setString(1, "dio");
            // st.setString(2, "konodioda");
            // st.setString(3, "dioxxoo@gmail.com");
            // int num = st.executeUpdate();
            // if (num > 0) {
            //     System.out.println("插入成功");
            // }
            st = conn.prepareStatement("select id, username, password, email from t_user where id > ?");
            st.setInt(1, 1);
            rs = st.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPasswrod(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
            System.out.println(users);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
