package com.example.demo.servlet;

import com.example.demo.service.HomeService;
import com.example.demo.model.Post;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.io.FileInputStream;
import org.apache.commons.io.IOUtils;
import java.io.File;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
    private HomeService homeService = new HomeService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Post> posts = homeService.getAllPosts();

            for (Post post : posts) {
                String imageUrl = post.getImageUrl();
                File file = new File("C:" + imageUrl);
                FileInputStream fis = new FileInputStream(file);
                byte[] bytes = IOUtils.toByteArray(fis);
                String encoded = Base64.getEncoder().encodeToString(bytes);
                post.setImageUrl("data:image/jpeg;base64," + encoded);
            }

            request.setAttribute("posts", posts);
            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
