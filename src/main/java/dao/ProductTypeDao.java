package dao;

import bean.ProductType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductTypeDao {
    public List<ProductType> getAll() throws SQLException, Exception{
        List<ProductType> productTypes=new ArrayList<ProductType>();
        String sql="select * from producttype";
        DatabaseDao databaseDao=new DatabaseDao();
        databaseDao.query(sql);
        while (databaseDao.next()) {
            ProductType productType=new ProductType();
            productType.setName(databaseDao.getString("name"));
            productType.setProductTypeId(databaseDao.getInt("productTypeId"));
            productTypes.add(productType);
        }
        return productTypes;
    }
    public Integer addProductType(ProductType productType,DatabaseDao databaseDao) throws SQLException {
        String sql="insert into producttype(name) values('"+productType.getName()+"')";
        return databaseDao.update(sql);
    }
    public Integer deleteProductType(int productTypeId,DatabaseDao databaseDao) throws SQLException {
        String sql="delete from producttype where productTypeId="+productTypeId;
        return databaseDao.update(sql);
    }
    public String getProductTypeNameById(int productTypeId,DatabaseDao databaseDao) throws SQLException {
        String sql="select  name from producttype where productTypeId="+productTypeId;
        databaseDao.query(sql);
        while(databaseDao.next()){
            return databaseDao.getString("name");
        }
        return  null;
    }
}
