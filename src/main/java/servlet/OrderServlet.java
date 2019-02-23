package servlet;

import bean.Order;
import com.google.gson.Gson;
import service.OrderService;
import service.UserService;
import tools.ServletTool;
import tools.Tool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "OrderServlet")
public class OrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        OrderService orderService=new OrderService();
        if("addOrder".equals(type)){
            Order order= ServletTool.order(request);
            int result=orderService.bulidOrder(order);
            PrintWriter out = response.getWriter();
            out.write(result);
        }else if("showOrder".equals(type)){
             UserService  userService=new UserService();
             List<Order> orders=orderService.showOrder();
             for(int i=0;i<orders.size();i++){
                 String name=userService.getNameByUserId(orders.get(i).getUserId());
                 orders.get(i).setUserName(name);
             }
            Gson gson = new Gson();
            String jsonString= gson.toJson(orders);//将对象转换成json格式的字符串
            Tool.returnJsonString(response, jsonString);//返回客户端
        }else if("deleteOrder".equals(type)){
            int orderId=Integer.valueOf(request.getParameter("orderId"));
            orderService.deleteOrder(orderId);
        }else if("deleteAllOrder".equals(type)){
            orderService.deleteAllOrder();
        }else if("makeConfirm".equals(type)){
            int orderId=Integer.valueOf(request.getParameter("orderId"));
            orderService.makeConfirm(orderId);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
