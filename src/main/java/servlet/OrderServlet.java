package servlet;

import bean.Order;
import service.OrderService;
import tools.ServletTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
