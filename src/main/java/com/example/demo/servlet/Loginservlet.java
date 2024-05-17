package com.example.demo.servlet;

import com.example.demo.service.LoginService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Loginservlet")
public class Loginservlet extends HttpServlet {
    private LoginService loginService = new LoginService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        String hashedPwdjsp=request.getParameter("hashedPwd");
        String mail = (String) request.getSession().getAttribute("mail");


        if(uname == null || uname.isEmpty() || hashedPwdjsp == null || hashedPwdjsp.isEmpty()) {
            request.setAttribute("alertType", "danger");
            request.setAttribute("alertMessage", "Username and password must not be empty");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if(uname.length() > 20 || pwd.length() > 20 ) {
            request.setAttribute("alertType", "danger");
            request.setAttribute("alertMessage", "Input exceeds maximum allowed length");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        try {
            String hashedPwd = loginService.hashPassword(hashedPwdjsp);

            if (loginService.isUserExist(uname)) {
                if (loginService.isPasswordCorrect(uname, hashedPwd)) {
                    if (loginService.isUserVerified(uname)) {
                        HttpSession session = request.getSession();
                        session.setAttribute("username", uname);
                        request.setAttribute("alertType", "success");
                        request.setAttribute("alertMessage", "Login successful");
                        request.getRequestDispatcher("new.jsp").forward(request, response);
                    } else {
                        request.setAttribute("alertType", "danger");
                        request.getSession().setAttribute("mail", mail);
                        request.setAttribute("alertMessage", "Please verify your email first");
                        request.getRequestDispatcher("emailverify.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("alertType", "danger");
                    request.setAttribute("alertMessage", "Invalid password");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("alertType", "danger");
                request.setAttribute("alertMessage", "User does not exist");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch(Exception e) {
            e.printStackTrace();
            request.setAttribute("alertType", "danger");
            request.setAttribute("alertMessage", "Login failed due to an error: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }


}


