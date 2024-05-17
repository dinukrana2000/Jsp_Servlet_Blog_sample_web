package com.example.demo.servlet;

import com.example.demo.service.ResetPasswordService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.security.NoSuchAlgorithmException;

@WebServlet("/Resetpasswordservlet")
public class Resetpasswordservlet extends HttpServlet {
    private ResetPasswordService resetPasswordService = new ResetPasswordService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String token = request.getParameter("reset_token");
        String hashedPwdFromJSP = request.getParameter("hashedPwd");
        String conhashPwdFromJSP=request.getParameter("conhashPwd");

        if (hashedPwdFromJSP != null && conhashPwdFromJSP != null && hashedPwdFromJSP.equals(conhashPwdFromJSP)) {
            try {
                String hashedPwd = resetPasswordService.hashPassword(hashedPwdFromJSP);
                int rowsUpdated = resetPasswordService.resetPassword(hashedPwd, token);

                if (rowsUpdated > 0) {
                    request.setAttribute("alertType", "success");
                    request.setAttribute("alertMessage", "Password has been reset successfully!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    request.setAttribute("alertType", "danger");
                    request.setAttribute("alertMessage", "expired the link try again");
                    request.getRequestDispatcher("fogot.jsp").forward(request, response);
                }
            } catch (NoSuchAlgorithmException | SQLException e) {
                e.printStackTrace();
                request.setAttribute("alertType", "danger");
                request.setAttribute("alertMessage", "something went wrong please try again");
                request.getRequestDispatcher("fogot.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("alertType", "danger");
            request.setAttribute("alertMessage", "password do not match");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
        }
    }
}
