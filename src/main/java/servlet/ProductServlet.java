package servlet;

import bean.Product;
import bean.ProductType;
import com.google.gson.Gson;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.ProductService;
import service.ProductTypesService;
import tools.FileTool;
import tools.Message;
import tools.Tool;
import tools.WebProperties;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
            Integer result;
            try {
            Product product=new Product();
            DiskFileItemFactory factory = new DiskFileItemFactory();
            String fullPath=request.getServletContext()
                                   .getRealPath("\\\\upload\\\\temp");//获取相对路径的绝对路径
            File repository = new File(fullPath);
            factory.setRepository(repository);//设置临时文件存放的文件夹
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解析request，将其中各表单元素和上传文件提取出来
            List<FileItem> items = null;//items存放各表单元素
            items = upload.parseRequest(request);
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {//遍历表单元素
                FileItem item = iter.next();
                if (item.isFormField()) {//非上传文件表单元素
                    if("productName".equals(item.getFieldName()))//获取表单元素的name属性
                        product.setProductName(item.getString("UTF-8"));//获取表单元素的值（一般是value属性）
                    else if("text".equals(item.getFieldName()))//获取表单元素的name属性
                        product.setContent(item.getString("UTF-8"));//获取表单元素的值（一般是value属性）
                    else if("price".equals(item.getFieldName()))
                        product.setPrice(item.getString("UTF-8"));
                    else if("type".equals(item.getFieldName()))
                        product.setProductType(item.getString("UTF-8"));
                } else {//上传文件
                    File uploadedFile ;
                    String randomFileName;
                    do{
                        randomFileName= FileTool.getRandomFileNameByCurrentTime(item.getName());
                        String full=request.getServletContext()
                                           .getRealPath("\\\\upload\\\\images\\\\products"+"\\"
                                                   +randomFileName);
                        uploadedFile=new File(full);
                    }while(uploadedFile.exists());//确保文件未存在

                    item.write(uploadedFile);//将临时文件转存为新文件保存
                    result=1;//表示上传文件成功
                    //item.delete();//删除临时文件
                    result=2;//表示上传文件成功，且临时文件删除
                    product.setImgUrl("http://localhost:8080/ordering/upload/images/products/"+randomFileName);
                    System.out.println(product.getContent());
                    System.out.println(product.getImgUrl());
                    System.out.println(product.getPrice());
                    System.out.println(product.getProductName());
                }
            }
                result= produtctService.addProdcut(product);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("showAllProduct".equals(type)){
            ProductService productService=new ProductService();
            List<Product> products=productService.showAllProduct();
            Gson gson = new Gson();
            String jsonString= gson.toJson(products);//将对象转换成json格式的字符串
            Tool.returnJsonString(response, jsonString);//返回客户端
        }else if("editProduct".equals(type)){
            Integer result;
            try {
                Product product=new Product();
                DiskFileItemFactory factory = new DiskFileItemFactory();
                String fullPath=request.getServletContext()
                                       .getRealPath("\\\\upload\\\\temp");
                File repository = new File(fullPath);
                factory.setRepository(repository);//设置临时文件存放的文件夹
                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                // 解析request，将其中各表单元素和上传文件提取出来
                List<FileItem> items = null;//items存放各表单元素
                items = upload.parseRequest(request);
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {//遍历表单元素
                    FileItem item = iter.next();
                    if (item.isFormField()) {//非上传文件表单元素
                        if("productName".equals(item.getFieldName()))
                            product.setProductName(item.getString("UTF-8"));
                        else if("productId".equals(item.getFieldName())){
                              product.setProductId(Integer.valueOf(item.getString("UTF-8")));
                        }
                        else if("oldImgUrl".equals(item.getFieldName())){
                            String oldImgUrl=request.getServletContext()
                                                    .getRealPath("\\\\upload\\\\images\\\\products"+"\\"+item.getString("UTF-8"));
                            File oldFile=new File(oldImgUrl);
                            if(oldFile.exists()){
                                oldFile.delete();
                                System.out.println("success delete oldImg ");
                            }
                        }
                        else if("text".equals(item.getFieldName())) {
                            product.setContent(item.getString("UTF-8"));
                        }
                        else if("price".equals(item.getFieldName())) {
                            product.setPrice(item.getString("UTF-8"));
                        }
                        else if("type".equals(item.getFieldName())) {
                            product.setProductType(item.getString("UTF-8"));
                        }
                    } else {//上传文件
                        File uploadedFile ;
                        String randomFileName;
                        do{
                            randomFileName= FileTool.getRandomFileNameByCurrentTime(item.getName());
                            String full=request.getServletContext()
                                               .getRealPath("\\\\upload\\\\images\\\\products"+"\\"
                                                       +randomFileName);
                            uploadedFile=new File(full);
                        }while(uploadedFile.exists());//确保文件未存在

                        item.write(uploadedFile);//将临时文件转存为新文件保存
                        result=1;//表示上传文件成功
                        //item.delete();//删除临时文件
                        result=2;//表示上传文件成功，且临时文件删除
                        product.setImgUrl("http://localhost:8080/ordering/upload/images/products/"+randomFileName);
                        System.out.println(product.getContent());
                        System.out.println(product.getImgUrl());
                        System.out.println(product.getPrice());
                        System.out.println(product.getProductName());
                    }
                }
                result= produtctService.editProduct(product);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else  if("deleteProduct".equals(type)){
            Integer prodcutId=Integer.valueOf(request.getParameter("productId"));
            Integer result=produtctService.deleteProduct(prodcutId);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doPost(request,response);
    }
}
