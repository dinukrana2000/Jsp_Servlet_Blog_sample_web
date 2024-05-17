package com.example.demo.service;

import com.example.demo.dao.UpdatePostDAO;
import java.sql.SQLException;

public class UpdatePostService {
    private UpdatePostDAO updatePostDAO = new UpdatePostDAO();

    public void updatePost(String title, String description, String imageUrl, int id) throws SQLException {
        updatePostDAO.updatePost(title, description, imageUrl, id);
    }
}
