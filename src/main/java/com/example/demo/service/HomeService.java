package com.example.demo.service;

import com.example.demo.dao.HomeDAO;
import com.example.demo.model.Post;
import java.sql.SQLException;
import java.util.List;

public class HomeService {
    private HomeDAO homeDAO = new HomeDAO();

    public List<Post> getAllPosts() throws SQLException {
        return homeDAO.getAllPosts();
    }
}
