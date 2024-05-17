package com.example.demo.servlet;

import com.example.demo.service.DeletePostService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import jakarta.servlet.ServletException;

@WebServlet("/DeletePostServlet")
public class DeletePostServlet extends HttpServlet {
    private DeletePostService deletePostService = new DeletePostService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postId = request.getParameter("postId");
        if (postId == null || postId.isEmpty()) {
            System.out.println("error null");
        } else {
            int id = Integer.parseInt(postId);
            try {
                deletePostService.deletePost(id);
                HttpSession session = request.getSession();
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("success");
            } catch (Exception e) {
                e.printStackTrace();
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("An error occurred");
            }
        }
    }
}
