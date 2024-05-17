package com.example.demo.servlet;

import com.example.demo.service.UpdateService;
import com.example.demo.model.Post;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.io.FileInputStream;
import org.apache.commons.io.IOUtils;
import java.io.File;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    private UpdateService updateService = new UpdateService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String author = (String) session.getAttribute("username");

        try {
            List<Post> posts = updateService.getPostsByAuthor(author);

            for (Post post : posts) {
                String imageUrl = post.getImageUrl();
                File file = new File("C:" + imageUrl);
                FileInputStream fis = new FileInputStream(file);
                byte[] bytes = IOUtils.toByteArray(fis);
                String encoded = Base64.getEncoder().encodeToString(bytes);
                post.setImageUrl("data:image/jpeg;base64," + encoded);
            }

            request.setAttribute("posts", posts);
            RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
