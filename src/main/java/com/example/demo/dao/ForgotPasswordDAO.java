package com.example.demo.dao;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ForgotPasswordDAO {
    public void updateResetToken(String email, String token) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement("UPDATE Users SET reset_token = ? WHERE Email = ?");
            ps.setString(1, token);
            ps.setString(2, email);
            ps.executeUpdate();
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
    }
}
