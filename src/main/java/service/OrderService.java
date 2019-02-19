package service;

import bean.Order;
import dao.DatabaseDao;
import dao.OrderDao;

public class OrderService {
    public Integer bulidOrder(Order order){
        try {
            DatabaseDao databaseDao=new DatabaseDao();
            OrderDao orderDao=new OrderDao();
            return orderDao.bulidOrder(order,databaseDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
