package servlet;

import bean.Product;
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
import java.util.ArrayList;
import java.util.List;

//@WebServlet(name = "ProductServlet")
public class ProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        ProductService produtctService=new ProductService();
        Message message=new Message();
        if("showProducts".equals(type)){
            ProductTypesService productTypesService=new ProductTypesService();
            List<ProductType> productTypes=productTypesService.getAll();
            List<String> productTypesName=new ArrayList<String>();
            for(ProductType p:productTypes){
                productTypesName.add(p.getName());
            }
            List<List<Product>>  productsList=produtctService.getByTypesTop(productTypesName);
            int productTypesNumber=productTypesName.size();
            List<Object> list = new ArrayList<Object>();
            list.add(productTypesNumber);
            list.add(productTypesName);
            list.add(productsList);

            Gson gson = new Gson();
            String jsonString= gson.toJson(list);//将对象转换成json格式的字符串
            Tool.returnJsonString(response, jsonString);//返回客户端
        }else if("addProduct".equals(type)){
             Product product=new Product();
             product.setProductName(request.getParameter("productName"));
             product.setContent(request.getParameter("text"));
             product.setPrice(request.getParameter("price"));
             product.setProductType(request.getParameter("type"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doPost(request,response);
    }
}
