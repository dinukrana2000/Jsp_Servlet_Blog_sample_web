package com.example.demo.dao;

import com.example.demo.model.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeDAO {
    public List<Post> getAllPosts() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Post> posts = new ArrayList<>();
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM Post ORDER BY date_posted DESC";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Post post = new Post();
                post.setTitle(rs.getString("title"));
                post.setDescription(rs.getString("description"));
                post.setImageUrl(rs.getString("image_url"));
                post.setAuthor(rs.getString("author"));
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return posts;
    }
}
