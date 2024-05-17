package com.example.demo.servlet;

import com.example.demo.model.SignupRequest;
import com.example.demo.model.SignupResponse;
import com.example.demo.requestmapper.SignupRequestMapper;
import com.example.demo.responsemapper.SignupResponseMapper;
import com.example.demo.service.SignupService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Signupservlet")
public class Signupservlet extends HttpServlet {
    private final SignupService signupService = new SignupService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SignupRequest signupRequest = SignupRequestMapper.map(request);

        SignupResponse signupResponse = signupService.register(signupRequest);

        if ("success".equals(signupResponse.getAlertType()) && signupResponse.isOtpSent()) {
            request.getSession().setAttribute("mail", signupRequest.getMail());
            response.sendRedirect("emailverify.jsp");
        } else {
            SignupResponseMapper.map(signupResponse, request);
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
        }
    }
}
