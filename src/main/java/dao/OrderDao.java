package dao;

import bean.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    public Integer bulidOrder(Order order,DatabaseDao databaseDao) throws SQLException {

        String sql="insert into `order`(userId,productsId,ProductsNum) values("+
                +order.getUserId()+",'"
                +order.getProductsId()+"','"
                +order.getProductsNum()+"')";
        System.out.println(sql);
         return databaseDao.update(sql);
    }
    public List<Order>showOrder(DatabaseDao databaseDao) throws SQLException {
      String sql="select * from `order`";
      List<Order> orders=new ArrayList<>();
      databaseDao.query(sql);
      while(databaseDao.next()){
          Order order=new Order();
          order.setOrderId(databaseDao.getInt("orderId"));
          order.setOrderTime(databaseDao.getTimestamp("orderTime"));
          order.setUserId(databaseDao.getInt("userId"));
          order.setStatus(databaseDao.getString("status"));
          orders.add(order);
      }
      return orders;
}
     public Integer deleteOrder(DatabaseDao databaseDao,Integer orderId) throws SQLException {
        String sql="delete from  `order` where orderId="+orderId;
        return databaseDao.update(sql);
     }
     public Integer deleteAllOrder(DatabaseDao databaseDao) throws SQLException {
        String sql="delete from  `order`";
        return databaseDao.update(sql);
     }
     public Integer  makeConfirm(DatabaseDao databaseDao,Integer orderId) throws SQLException {
        String sql="update `order`  set status='confirmed' where orderId="+orderId;
        return databaseDao.update(sql);
     }
}

