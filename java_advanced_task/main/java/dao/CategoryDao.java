package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Category;

public class CategoryDao {
	private static final String SEARCH_ALL = "SELECT id, name FROM categories ORDER BY id";
	
	Connection con;

    public CategoryDao(Connection con) {
        this.con = con;
    }
    
    
    public ArrayList<Category> SearchAll() {

    	ArrayList<Category> list = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(SEARCH_ALL)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Category(rs.getInt("id"), rs.getString("name")));
            }
            return list;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
}