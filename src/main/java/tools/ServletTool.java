package tools;

import bean.Order;

import javax.servlet.http.HttpServletRequest;
public class ServletTool {
    static public Order order(HttpServletRequest request){

        Order order=new Order();
        String userId=request.getParameter("userId");
        if(userId!=null && !userId.isEmpty())
         order.setUserId(Integer.parseInt(request.getParameter("userId")));

         order.setAddress(request.getParameter("address"));
         order.setProductsId(request.getParameter("productsId"));
         order.setProductsNum(request.getParameter("ProductsNum"));
         return order;
    }
}
