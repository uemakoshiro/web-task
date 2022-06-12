package service;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CategoryDao;
import entity.Category;
import util.DbUtil;

public class CategoryService {
	
	public ArrayList<Category> SelectAll() {
		
		ArrayList<Category> list = new ArrayList<>();
		try (Connection conn = DbUtil.getConnection()) {
			CategoryDao categoryDao = new CategoryDao(conn);
            
            list = categoryDao.SearchAll();
            
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
	}
	
}