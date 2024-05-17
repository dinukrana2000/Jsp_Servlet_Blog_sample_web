package com.example.demo.service;

import com.example.demo.dao.ResetPsswordDAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
public class ResetPasswordService {
    private ResetPsswordDAO resetPsswordDAO = new ResetPsswordDAO();

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return String.format("%064x", new BigInteger(1, hashedPassword));
    }

    public int resetPassword(String hashedPwd, String token) throws SQLException {
        return resetPsswordDAO.resetPassword(hashedPwd, token);
    }
}
