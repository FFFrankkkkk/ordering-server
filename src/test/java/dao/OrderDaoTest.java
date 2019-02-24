package dao;

import bean.Order;
import bean.Product;
import bean.ProductType;
import javafx.collections.ObservableList;
import org.junit.Test;
import service.ProductService;
import service.ProductTypesService;

import java.util.ArrayList;
import java.util.List;

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
    @Test
      public void testgetOrder() {
        List<Order> orders = new ArrayList<>();
        try {
            DatabaseDao databaseDao = new DatabaseDao();
            OrderDao orderDao = new OrderDao();
            orderDao.makeConfirm(databaseDao, 148);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   @Test
           public void testgetAllProductType(){
            ProductTypesService productTypesService=new ProductTypesService();
            List<ProductType> productTypes=productTypesService.getAll();
            for(ProductType productType:productTypes){
                System.out.println(productType.getName());
            }
        }
   @Test
     public void testAddProduct(){
        Product product=new Product();
       try {
           DatabaseDao databaseDao = new DatabaseDao();
           ProductDao productDao=new ProductDao();
           ProductService productService=new ProductService();
           product.setPrice("1");
           product.setImgUrl("http://localhost:8080/ordering/upload/images/products/1000.png-.jpg");
           product.setProductType("促销产品和热卖产品");
           product.setProductName("吉士炒双蛋堡");
           product.setContent("aaaaaa");
           productService.addProdcut(product);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

}