package service;

import bean.Product;
import dao.DatabaseDao;
import dao.ProductDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public List<List<Product>> getByTypesTop(List<String> productTypesName) {
        List<List<Product>> productList = new ArrayList<List<Product>>();
        try {
            DatabaseDao databaseDao = new DatabaseDao();
            ProductDao productDao = new ProductDao();
            for (String type : productTypesName) {//遍历newsTypes
                List<Product> products = productDao.getByTypesTop(type,databaseDao);  //获取相对应的类别的新闻
                productList.add(products);
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return productList;
    }
}
