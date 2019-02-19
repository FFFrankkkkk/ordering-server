package dao;

import bean.Order;

import java.sql.SQLException;

public class OrderDao {
    public Integer bulidOrder(Order order,DatabaseDao databaseDao) throws SQLException {

        String sql="insert into `order`(userId,productsId,ProductsNum) values("+
                +order.getUserId()+",'"
                +order.getProductsId()+"','"
                +order.getProductsNum()+"')";
        System.out.println(sql);
         return databaseDao.update(sql);
    }
}

