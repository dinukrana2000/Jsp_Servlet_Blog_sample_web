package com.example.demo.servlet;

import com.example.demo.service.UpdatePostService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/UpdatePostServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2,
        maxFileSize=1024*1024*10,
        maxRequestSize=1024*1024*50)
public class UpdatePostServlet extends HttpServlet {
    private static final String SAVE_DIR = "C:/upload";
    private UpdatePostService updatePostService = new UpdatePostService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        int id=Integer.parseInt(request.getParameter("postId"));
        HttpSession session = request.getSession();
        String author = (String) session.getAttribute("username");

        if(title.length() > 100 || description.length() > 2000 ) {
            request.setAttribute("alertType", "danger");
            request.setAttribute("alertMessage", "Input exceeds maximum allowed length");
            request.getRequestDispatcher("update").forward(request, response);
            return;
        }

        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        String imageUrl = null;
        if (!fileName.isEmpty()) {
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            if (!fileExtension.equals("jpg") && !fileExtension.equals("png")&&!fileExtension.equals("jpeg")) {
                System.out.println("Invalid file format detected");
                session.setAttribute("error", "File must be a .jpg , .png or .jpeg  file");
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("error");
                return;
            }

            String savePath = SAVE_DIR;
            File fileSaveDir = new File(savePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }

            String filePath = savePath + File.separator + fileName;
            filePart.write(filePath);
            System.out.println("Image saved at: " + filePath);

            imageUrl = "/upload/" + fileName;
        }

        try {
            updatePostService.updatePost(title, description, imageUrl, id);
            session.setAttribute("message", "Reposted successfully");
            session.setAttribute("messageType", "success");

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("success");
        } catch (Exception e) {
            e.printStackTrace();

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("error");
        }
    }
}
