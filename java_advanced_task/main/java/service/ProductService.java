package service;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ProductDao;
import entity.Product;
import util.DbUtil;

public class ProductService {
	
	public ArrayList<Product> Search(String keyword,String order) {
		
		ArrayList<Product> list = new ArrayList<>();
		try (Connection conn = DbUtil.getConnection()) {
            ProductDao searchDao = new ProductDao(conn);
            
            if(keyword.isEmpty()) {
            	list = searchDao.SearchAll(order);
            }else {
            	list = searchDao.Search(keyword,order);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
	}
	
	public Product SelectId(Integer id) {
		try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            
            return productDao.SelectId(id);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
	}
	
	public int Insert(Product pd) {
		try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            
            return productDao.Insert(pd);
		}catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
	}
	
	public int Delete(Integer id) {
		try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            
            return productDao.Delete(id);
		}catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
	}
	
	public int Update(Product pd) {
		try (Connection conn = DbUtil.getConnection()) {
            ProductDao productDao = new ProductDao(conn);
            
            return productDao.Update(pd);
		}catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
	}
}