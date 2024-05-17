package com.example.demo.service;

import com.example.demo.dao.LoginDAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;
import java.sql.SQLException;

public class LoginService {
    private LoginDAO loginDAO = new LoginDAO();

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return String.format("%064x", new BigInteger(1, hashedPassword));
    }

    public boolean isUserExist(String username) throws SQLException {

        return loginDAO.isUserExist(username);
    }

    public boolean isPasswordCorrect(String username, String hashedPwd) throws SQLException {

        return loginDAO.isPasswordCorrect(username, hashedPwd);
    }

    public boolean isUserVerified(String username) throws SQLException {

        return loginDAO.isUserVerified(username);
    }
}
