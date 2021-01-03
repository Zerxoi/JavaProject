package xyz.zerxoi.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDemo {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();
            conn.setAutoCommit(false);
            st = conn.prepareStatement("update account set money = money - 100 where name = 'A';");
            st.executeUpdate();
            st.close();
            st = conn.prepareStatement("update account set money = money + 100 where name = 'B';");
            st.executeUpdate();

            conn.commit();
            System.out.println("成功");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                System.out.println("失败");
            }
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
