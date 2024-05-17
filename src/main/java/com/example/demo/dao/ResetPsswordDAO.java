package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ResetPsswordDAO {
    public int resetPassword(String hashedPwd, String token) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        try {
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement("UPDATE Users SET Password = ?, reset_token = NULL WHERE reset_token = ?");
            ps.setString(1, hashedPwd);
            ps.setString(2, token);
            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rowsAffected;
    }
}
