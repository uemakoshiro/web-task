package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Product;

public class ProductDao {
	private static String SEARCH_ALL = "SELECT p.product_id, p.name AS product_name, p.price, c.name category FROM products p JOIN categories c ON p.category_id = c.id ";
	private static String SEARCH = "SELECT p.product_id, p.name AS product_name, p.price, c.name category FROM products p JOIN categories c ON p.category_id = c.id WHERE p.name LIKE ? OR c.name LIKE ? ";
	private static final String SELECT_ID = "SELECT p.product_id, p.name AS product_name, p.price, c.name category, p.description AS description, p.image_path AS image_path, c.id AS category_id FROM products p JOIN categories c ON p.category_id = c.id WHERE product_id = ?";
	private static final String INSERT = "INSERT INTO products(product_id, category_id, name, price, image_path, description) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String DELETE = "DELETE FROM products WHERE product_id = ?";
	private static final String UPDATE = "UPDATE products SET category_id = ?, price = ?, name = ? WHERE product_id = ?";
	
	Connection con;

    public ProductDao(Connection con) {
        this.con = con;
    }
    
    public ArrayList<Product> Search(String keyword,String order) {
    	if("product_id".equals(order) || "c.name".equals(order) || "price".equals(order) || "price DESC".equals(order) || "p.created_at".equals(order) || "p.created_at DESC".equals(order)) {
    		order = "ORDER BY "+order;
    	}else {
    		order = "";
    	}
    	
    	ArrayList<Product> list = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(SEARCH+order)) {
        	stmt.setString(1, "%"+keyword+"%");
        	stmt.setString(2, "%"+keyword+"%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getInt("price"), rs.getString("category")));
            }
            return list;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    
    public ArrayList<Product> SearchAll(String order) {
    	if("product_id".equals(order) || "c.name".equals(order) || "price".equals(order) || "price DESC".equals(order) || "p.created_at".equals(order) || "p.created_at DESC".equals(order)) {
    		order = "ORDER BY "+order;
    	}else {
    		order = "";
    	}System.out.println(order);
    	ArrayList<Product> list = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement(SEARCH_ALL+order)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getInt("price"), rs.getString("category")));
            }
            return list;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public Product SelectId(Integer id) {

        try (PreparedStatement stmt = con.prepareStatement(SELECT_ID)) {
        	stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(rs.getInt("product_id"), rs.getString("product_name"), rs.getInt("price"), rs.getString("category"), rs.getString("description"), rs.getString("image_path"), rs.getInt("category_id"));
            }
            return null;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public int Insert(Product pd) {
    	
    	try (PreparedStatement stmt = con.prepareStatement(INSERT)) {
    		stmt.setInt(1, pd.getId());
    		stmt.setInt(2, pd.getCategoryId());
    		stmt.setString(3, pd.getName());
    		stmt.setInt(4, pd.getPrice());
    		stmt.setString(5, pd.getFile());
    		stmt.setString(6, pd.getDescription());
    		return stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	return 0;
    }
    
    public int Delete(Integer id) {
    	try (PreparedStatement stmt = con.prepareStatement(DELETE)) {
    		stmt.setInt(1, id);
    		return stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	return 0;
    }
    
    public int Update(Product pd) {
    	try (PreparedStatement stmt = con.prepareStatement(UPDATE)) {
    		stmt.setInt(1, pd.getCategoryId());
    		stmt.setInt(2, pd.getPrice());
    		stmt.setString(3, pd.getName());
    		stmt.setInt(4, pd.getId());
    		
    		return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	return 0;
    }
}