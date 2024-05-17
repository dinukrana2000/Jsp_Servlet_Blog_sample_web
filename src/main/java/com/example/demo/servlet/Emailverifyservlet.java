package com.example.demo.servlet;

import com.example.demo.service.EmailVerifyService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Emailverifyservlet")
public class Emailverifyservlet extends HttpServlet {
    private EmailVerifyService emailVerifyService = new EmailVerifyService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enteredOtp = request.getParameter("otp");
        String mail = (String) request.getSession().getAttribute("mail");


        try {
            int storedOtp = emailVerifyService.getStoredOtp(mail);

            if (Integer.parseInt(enteredOtp) == storedOtp) {
                request.setAttribute("alertType", "success");
                request.setAttribute("alertMessage", "OTP verification successful!");
                request.getRequestDispatcher("login.jsp").forward(request, response);

                emailVerifyService.updateUserVerification(mail);
            } else {
                request.setAttribute("alertType", "danger");
                request.setAttribute("alertMessage", "OTP verification failed!");
                request.getRequestDispatcher("emailverify.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("alertType", "danger");
            request.setAttribute("alertMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("emailverify.jsp").forward(request, response);
            e.printStackTrace();
        }
    }
}
