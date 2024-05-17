package com.example.demo.service;

import com.example.demo.dao.UpdateDAO;
import com.example.demo.model.Post;
import java.sql.SQLException;
import java.util.List;

public class UpdateService {
    private UpdateDAO updateDAO = new UpdateDAO();

    public List<Post> getPostsByAuthor(String author) throws SQLException {
        return updateDAO.getPostsByAuthor(author);
    }
}
