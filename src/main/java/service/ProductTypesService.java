package service;

import bean.ProductType;
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
}
