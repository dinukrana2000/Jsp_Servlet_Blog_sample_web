package com.example.demo.service;

import com.example.demo.dao.PostDAO;
import java.sql.Timestamp;
import java.sql.SQLException;

public class PostService {
    private PostDAO postDAO = new PostDAO();

    public void createPost(String title, String description, String author, Timestamp datePosted, String imageUrl) throws SQLException {
        postDAO.createPost(title, description, author, datePosted, imageUrl);
    }
}
