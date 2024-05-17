package com.example.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailVerifyDAO {
    public int getStoredOtp(String mail) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int storedOtp = -1;
        try {
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement("SELECT otp FROM Users WHERE Email = ?");
            ps.setString(1, mail);
            rs = ps.executeQuery();
            if(rs.next()) {
                storedOtp = rs.getInt("otp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
        return storedOtp;
    }

    public int updateUserVerification(String mail) {
        Connection conn = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        try {
            conn = DatabaseConnection.getConnection();
            ps = conn.prepareStatement("UPDATE Users SET verification = 1 WHERE Email = ?");
            ps.setString(1, mail);
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
