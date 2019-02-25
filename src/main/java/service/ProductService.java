package service;

import bean.Product;
import dao.DatabaseDao;
import dao.ProductDao;

import javax.persistence.criteria.CriteriaBuilder;
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
    public Integer addProdcut(Product product){
        try {
            DatabaseDao databaseDao=new DatabaseDao();
            ProductDao productDao=new ProductDao();
            return productDao.addProduct(product,databaseDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public List<Product> showAllProduct(){
        try {
            DatabaseDao databaseDao = new DatabaseDao();
            ProductDao productDao=new ProductDao();
            return productDao.getByTypesTop("all",databaseDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Integer editProduct(Product product){
        try {
            DatabaseDao databaseDao=new DatabaseDao();
            ProductDao productDao=new ProductDao();
            return productDao.editProduct(product,databaseDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public Integer deleteProduct(int productId){
        try {
            DatabaseDao databaseDao=new DatabaseDao();
            ProductDao productDao=new ProductDao();
            return productDao.deleteProduct(productId,databaseDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public Integer deleteProductByProductTypeName(String  productTypeName){
        try {
            DatabaseDao databaseDao=new DatabaseDao();
            ProductDao productDao=new ProductDao();
            return productDao.deleteProductByProductTypeName(productTypeName,databaseDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
