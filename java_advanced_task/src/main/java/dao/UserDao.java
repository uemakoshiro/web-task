package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;

public class UserDao {
	private static final String SELECT_ID_PASS = "SELECT * FROM users WHERE login_id = ? AND password = ?";
	
	Connection con;

    public UserDao(Connection con) {
        this.con = con;
    }
    
    public User loginCheck(String id, String pass) {
    	
    	User user;
        try (PreparedStatement stmt = con.prepareStatement(SELECT_ID_PASS)) {
        	stmt.setString(1, id);
        	stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(rs.getString("login_id"), rs.getString("name"), rs.getString("role"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}