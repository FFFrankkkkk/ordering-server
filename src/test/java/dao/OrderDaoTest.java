package dao;

import bean.Order;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderDaoTest {
      @Test
    public void Test(){
          Order order=new Order();
          order.setUserId(1);
          order.setProductsId("47,48");
          order.setProductsNum("1,1");
          try {
          DatabaseDao databaseDao=new DatabaseDao();
          OrderDao orderDao=new OrderDao();
          orderDao.bulidOrder(order,databaseDao);
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
}