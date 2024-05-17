package com.example.demo.service;

import com.example.demo.dao.EmailVerifyDAO;

import java.sql.SQLException;

public class EmailVerifyService {
    private EmailVerifyDAO emailVerifyDAO = new EmailVerifyDAO();

    public int getStoredOtp(String mail) throws SQLException {
        return emailVerifyDAO.getStoredOtp(mail);
    }

    public int updateUserVerification(String mail) throws SQLException {
        return emailVerifyDAO.updateUserVerification(mail);
    }
}
