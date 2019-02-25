package dao;

import bean.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public Integer addProduct(Product product,DatabaseDao databaseDao) throws SQLException {
        String sql="insert into product(productType,price,productName,content,imgUrl) values("
                +"'"+product.getProductType()+"','"
                +product.getPrice()+"','"
                +product.getProductName()+"','"
                +product.getContent()+"','"
                +product.getImgUrl()+"')";
        return databaseDao.update(sql);
    }
    public Integer editProduct(Product product,DatabaseDao databaseDao) throws SQLException {
        String sql=" update product set productName='"+product.getProductName()
                +"',productType='"+product.getProductType()
                +"',content='"+product.getContent()
                +"',imgUrl='"+product.getImgUrl()
                +"',price='"+product.getPrice()
                +"' where productId="+product.getProductId().toString();
         return databaseDao.update(sql);
    }
//    public News getById(Integer newsId) throws SQLException,Exception{
//        DatabaseDao databaseDao=new DatabaseDao();
//        News news=new News();
//
//        databaseDao.getById("news", newsId);
//        while (databaseDao.next()) {
//            news.setNewsId(databaseDao.getInt("newsId"));
//            news.setCaption(databaseDao.getString("caption"));
//            news.setAuthor(databaseDao.getString("author"));
//            news.setNewsType(databaseDao.getString("newsType"));
//            news.setContent(databaseDao.getString("content"));
//            news.setNewsTime(databaseDao.getLocalDateTime("newsTime"));
//            news.setPublishTime(databaseDao.getTimestamp("publishTime"));
//        }
//        return news;
//    }
//    public Integer deletes(String tableName,String ids,DatabaseDao databaseDao)throws SQLException,Exception{
//        return databaseDao.deletes(tableName, ids, databaseDao);
//    }
    public List<Product> getByTypesTop(String type, DatabaseDao databaseDao)throws SQLException,Exception{
        List<Product> products=new ArrayList<Product>();
        String sql;
        if(type.equals("all"))
        sql="select  productId,productType,price,productName,content,url,imgUrl,staticHtml from product";
        else
            sql="select  productId,productType,price,productName,content,url,imgUrl,staticHtml from product where productType='" + type +"'";
        databaseDao.query(sql);
        while (databaseDao.next()) {
            Product product=new Product();
            product.setProductId(databaseDao.getInt("productId"));
            product.setProductName(databaseDao.getString("productName"));
            product.setImgUrl(databaseDao.getString("imgUrl"));
            product.setContent(databaseDao.getString("content"));
            product.setProductType(databaseDao.getString("productType"));
            product.setPrice(databaseDao.getString("price"));
            product.setUrl(databaseDao.getString("url"));
            products.add(product);
        }
        return products;
    }
    public Integer deleteProduct(int productId,DatabaseDao databaseDao) throws SQLException {
        String sql="delete from product where productId="+productId;
        return databaseDao.update(sql);
    }
    public Integer deleteProductByProductTypeName(String  productTypeName,DatabaseDao databaseDao) throws SQLException {
        String sql="delete from product where productType="+productTypeName;
        return databaseDao.update(sql);
    }
}
