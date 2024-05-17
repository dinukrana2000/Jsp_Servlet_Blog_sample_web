package com.example.demo.service;

import com.example.demo.dao.SignupDAO;
import com.example.demo.responsemapper.SignupResponseMapper;
import com.example.demo.model.SignupRequest;
import com.example.demo.model.SignupResponse;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class SignupService {
    private final SignupDAO signupDAO = new SignupDAO();
    private final EmailService emailService=new EmailService();

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        return String.format("%064x", new BigInteger(1, hashedPassword));
    }

    public boolean isUserExist(String email, String username)  {
        return signupDAO.isUserExist(email, username);
    }

    public int registerUser(String fname, String mail, String num, String uname, String hashedPwd)  {
        return signupDAO.insertUser(fname, mail, num, uname, hashedPwd);
    }

    public int updateUserOTP(String mail)  {
        int otp = new Random().nextInt(999999);
        signupDAO.updateUserOTP(mail, otp);
        return otp;
    }

    public boolean isInputValid(SignupRequest signupRequest) {
        return signupRequest.getFname().length() <= 50 &&
                signupRequest.getMail().length() <= 40 &&
                signupRequest.getNum().length() <= 10 &&
                signupRequest.getUname().length() <= 20 &&
                signupRequest.getPwd().length() <= 20 &&
                signupRequest.getCpwd().length() <= 20;
    }

    public SignupResponse register(SignupRequest signupRequest) {
        if (!isInputValid(signupRequest)) {
            return SignupResponseMapper.createErrorResponse("Input exceeds maximum allowed length");
        }

        if (!signupRequest.getHashedPwdFromJSP().equals(signupRequest.getHashedCPwd())) {
            return SignupResponseMapper.createErrorResponse("Passwords do not match");
        }

        try {
            String hashedPwd = hashPassword(signupRequest.getHashedPwdFromJSP());

            if (isUserExist(signupRequest.getMail(), signupRequest.getUname())) {
                return SignupResponseMapper.createErrorResponse("Email or username already exists");
            } else {
                int result = registerUser(signupRequest.getFname(), signupRequest.getMail(), signupRequest.getNum(), signupRequest.getUname(), hashedPwd);

                if (result > 0) {
                    int otp = updateUserOTP(signupRequest.getMail());
                    boolean emailSent = emailService.sendEmail(signupRequest.getMail(), "OTP for email verification", "Your OTP for email verification is: " + otp);

                    if (emailSent) {
                        return SignupResponseMapper.createSuccessResponse("Successfully registered", true);
                    } else {
                        return SignupResponseMapper.createErrorResponse("Failed to send verification email. Please try again.");
                    }
                } else {
                    return SignupResponseMapper.createErrorResponse("Registration failed");
                }
            }
        } catch (Exception e) {

            return SignupResponseMapper.createErrorResponse("Registration failed due to an error: " + e.getMessage());
        }
    }
}
