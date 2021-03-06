package service;

import bean.ProductType;
import dao.DatabaseDao;
import dao.ProductTypeDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductTypesService {
    public List<ProductType> getAll() {
        List<ProductType> productTypes=new ArrayList<ProductType>();
        try {
            ProductTypeDao productTypeDao=new ProductTypeDao();
            productTypes= productTypeDao.getAll();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return productTypes;
    }
    public Integer addProductType(ProductType productType){
        try {
            DatabaseDao databaseDao=new DatabaseDao();
            ProductTypeDao productTypeDao=new ProductTypeDao();
            return productTypeDao.addProductType(productType,databaseDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public Integer deleteProductType(int productTypeId){
        try {
            DatabaseDao databaseDao=new DatabaseDao();
            ProductTypeDao productTypeDao=new ProductTypeDao();
            return productTypeDao.deleteProductType(productTypeId,databaseDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public String getProductTypeNameById(int productTypeId){
        try {
            DatabaseDao databaseDao=new DatabaseDao();
            ProductTypeDao productTypeDao=new ProductTypeDao();
            return productTypeDao.getProductTypeNameById(productTypeId,databaseDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
