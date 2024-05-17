package com.example.demo.responsemapper;

import com.example.demo.model.SignupResponse;
import jakarta.servlet.http.HttpServletRequest;

public class SignupResponseMapper {
    public static SignupResponse createErrorResponse(String message) {
        SignupResponse response = new SignupResponse();
        response.setAlertType("danger");
        response.setAlertMessage(message);
        response.setOtpSent(false);
        return response;
    }

    public static SignupResponse createSuccessResponse(String message, boolean otpSent) {
        SignupResponse response = new SignupResponse();
        response.setAlertType("success");
        response.setAlertMessage(message);
        response.setOtpSent(otpSent);
        return response;
    }

    public static void map(SignupResponse signupResponse, HttpServletRequest request) {
        request.setAttribute("alertType", signupResponse.getAlertType());
        request.setAttribute("alertMessage", signupResponse.getAlertMessage());
    }
}
