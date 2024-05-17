package com.example.demo.servlet;

import com.example.demo.service.ForgotPasswordService;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;

@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {
    private ForgotPasswordService forgotPasswordService = new ForgotPasswordService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();

        try {
            forgotPasswordService.updateResetToken(email, token);
            forgotPasswordService.sendResetLink(email, token);
            request.setAttribute("alertType", "success");
            request.setAttribute("alertMessage", "Reset link sent to email successfully!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
