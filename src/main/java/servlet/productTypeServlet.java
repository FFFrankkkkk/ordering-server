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
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "productTypeServlet")
public class productTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String type=request.getParameter("type");
        ProductService produtctService=new ProductService();
        Message message=new Message();
        ProductTypesService productTypesService=new ProductTypesService();
        PrintWriter out=response.getWriter();
        if("getProductType".equals(type)){
            List<ProductType> productTypes=productTypesService.getAll();
            Gson gson = new Gson();
            String jsonString= gson.toJson(productTypes);//将对象转换成json格式的字符串
            Tool.returnJsonString(response, jsonString);//返回客户端
        }else if("addProductType".equals(type)){
            ProductType productType=new ProductType();
            String addProductTypename = new String(request.getParameter("addProductTypename").getBytes("iso-8859-1"), "utf-8");
            productType.setName(addProductTypename);
            System.out.println(productType.getName());
            Integer result=productTypesService.addProductType(productType);
            out.println(result);
        }else if("deleteProductType".equals(type)){
            int productTypeId=Integer.valueOf(request.getParameter("productTypeId"));
            String prodcutTypeName=productTypesService.getProductTypeNameById(productTypeId);
            int result=productTypesService.deleteProductType(productTypeId);
            result=produtctService.deleteProductByProductTypeName(prodcutTypeName);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
