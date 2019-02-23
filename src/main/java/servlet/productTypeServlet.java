package servlet;

import bean.ProductType;
import com.google.gson.Gson;
import service.ProductService;
import service.ProductTypesService;
import tools.Message;
import tools.Tool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "productTypeServlet")
public class productTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        ProductService produtctService=new ProductService();
        Message message=new Message();
        if("getProductType".equals(type)){
            ProductTypesService productTypesService=new ProductTypesService();
            List<ProductType> productTypes=productTypesService.getAll();
            Gson gson = new Gson();
            String jsonString= gson.toJson(productTypes);//将对象转换成json格式的字符串
            Tool.returnJsonString(response, jsonString);//返回客户端
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
