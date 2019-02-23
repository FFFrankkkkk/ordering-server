package service;

import bean.Order;
import dao.DatabaseDao;
import dao.OrderDao;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

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
    public List<Order>showOrder(){
        List<Order> orders=new ArrayList<>();
        try {

            DatabaseDao databaseDao=new DatabaseDao();
            OrderDao orderDao=new OrderDao();
           orders=orderDao.showOrder(databaseDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
    public Integer deleteOrder(Integer orderId){
        try {
            DatabaseDao databaseDao=new DatabaseDao();
            OrderDao orderDao=new OrderDao();
            return orderDao.deleteOrder(databaseDao,orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public Integer deleteAllOrder(){
        try {
            DatabaseDao databaseDao=new DatabaseDao();
            OrderDao orderDao=new OrderDao();
            return orderDao.deleteAllOrder(databaseDao);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public Integer makeConfirm(Integer orderId){
        try {
            DatabaseDao databaseDao=new DatabaseDao();
            OrderDao orderDao=new OrderDao();
            return orderDao.makeConfirm(databaseDao,orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
