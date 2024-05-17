package com.example.demo.service;

import com.example.demo.dao.DeletePostDAO;
import java.sql.SQLException;

public class DeletePostService {
    private DeletePostDAO deletePostDAO = new DeletePostDAO();

    public void deletePost(int id) throws SQLException {
        deletePostDAO.deletePost(id);
    }
}
