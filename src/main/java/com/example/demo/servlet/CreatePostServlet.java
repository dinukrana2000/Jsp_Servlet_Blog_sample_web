package com.example.demo.servlet;

import com.example.demo.service.PostService;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.nio.file.Paths;
import java.io.File;
import java.sql.Timestamp;

@WebServlet("/CreatePostServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
        maxFileSize=1024*1024*10, // 10MB
        maxRequestSize=1024*1024*50) // 50MB
public class CreatePostServlet extends HttpServlet {
    private static final String UPLOAD_DIRECTORY = "C:/upload";
    private PostService postService = new PostService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        if(title.length() > 100 || description.length() > 2000 ) {
            request.setAttribute("alertType", "danger");
            request.setAttribute("alertMessage", "Input exceeds maximum allowed length");
            request.getRequestDispatcher("new.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession();
        String author = (String) session.getAttribute("username");

        Timestamp datePosted = new Timestamp(System.currentTimeMillis());

        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!fileExtension.equals("jpg") && !fileExtension.equals("png") && !fileExtension.equals("jpeg")) {
            request.setAttribute("alertType", "danger");
            request.setAttribute("alertMessage", "File must be a .jpg, .png, or .jpeg file");
            request.getRequestDispatcher("new.jsp").forward(request, response);
            return;
        }

        String uploadPath = UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        String imageUrl = "/upload/" + fileName;

        try {
            postService.createPost(title, description, author, datePosted, imageUrl);
            request.setAttribute("alertType", "success");
            request.setAttribute("alertMessage", "Posted successfully");
            request.getRequestDispatcher("new.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("alertType", "danger");
            request.setAttribute("alertMessage", "Failed to post: " + e.getMessage());
            request.getRequestDispatcher("new.jsp").forward(request, response);
        }
    }
}
