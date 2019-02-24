package dao;

import bean.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public Integer addProduct(Product product,DatabaseDao databaseDao) throws SQLException {
        String sql="insert into product(productType,price,productName,content,imgUrl) values("
                +"'"+product.getProductType()+"','"
                +product.getPrice()+"','"
                +product.getProductName()+"','"
                +product.getContent()+"','"
                +product.getImgUrl()+"')";
        return databaseDao.update(sql);
    }
//    public List<Integer> articleNumberByMonthInAYear(String year) throws SQLException,Exception{
//        List<Integer> articleNumberByMonthList=Tool.getListWithLengthInitIntValue(12, 0);
//        String sql="select count(*) as articleNumber , MONTH(newsTime) as month1 from news where YEAR(newsTime)="+year+"  group by month1 order by  month1";
//        DatabaseDao databaseDao=new DatabaseDao();
//        databaseDao.query(sql);
//        while (databaseDao.next()) {
//            int month1=databaseDao.getInt("month1");
//            //修改数组元素的值
//            articleNumberByMonthList.set(month1-1,databaseDao.getInt("articleNumber"));
//        }
//        return articleNumberByMonthList;
//    }
//    public Integer update(News news,DatabaseDao databaseDao) throws SQLException{
//        String sql=" update news set caption='"+news.getCaption()
//                +"',author='"+news.getAuthor()
//                +"',newsType='"+news.getNewsType()
//                +"',content='"+news.getContent()
//                +"',newsTime='"+news.getNewsTime()
//                +"' where newsId="+news.getNewsId().toString();
//        return databaseDao.update(sql);
//    }
//    public List<News> getOnePage(PageInformation pageInformation,DatabaseDao databaseDao) throws SQLException{
//        List<News> newses=new ArrayList<News>();
//        String sqlCount=Tool.getSql(pageInformation,"count");
//        Integer allRecordCount=databaseDao.getCount(sqlCount);//符合条件的总记录数
//        Tool.setPageInformation(allRecordCount, pageInformation);//更新pageInformation的总页数等
//
//        String sqlSelect=Tool.getSql(pageInformation,"select");
//
//        //不取出新闻内容
//        sqlSelect=sqlSelect.replace("*", " newsId,caption,author,newsType,newsTime,publishTime,url ");
//
//        databaseDao.query(sqlSelect);
//        while (databaseDao.next()) {
//            News news=new News();
//            news.setNewsId(databaseDao.getInt("newsId"));
//            news.setCaption(databaseDao.getString("caption"));
//            news.setAuthor(databaseDao.getString("author"));
//            news.setNewsType(databaseDao.getString("newsType"));
//            news.setNewsTime(databaseDao.getLocalDateTime("newsTime"));
//            news.setPublishTime(databaseDao.getTimestamp("publishTime"));
//            news.setUrl(databaseDao.getString("url"));
//            newses.add(news);
//        }
//        return newses;
//    }
//    public News getById(Integer newsId) throws SQLException,Exception{
//        DatabaseDao databaseDao=new DatabaseDao();
//        News news=new News();
//
//        databaseDao.getById("news", newsId);
//        while (databaseDao.next()) {
//            news.setNewsId(databaseDao.getInt("newsId"));
//            news.setCaption(databaseDao.getString("caption"));
//            news.setAuthor(databaseDao.getString("author"));
//            news.setNewsType(databaseDao.getString("newsType"));
//            news.setContent(databaseDao.getString("content"));
//            news.setNewsTime(databaseDao.getLocalDateTime("newsTime"));
//            news.setPublishTime(databaseDao.getTimestamp("publishTime"));
//        }
//        return news;
//    }
//    public Integer deletes(String tableName,String ids,DatabaseDao databaseDao)throws SQLException,Exception{
//        return databaseDao.deletes(tableName, ids, databaseDao);
//    }
    public List<Product> getByTypesTop(String type, DatabaseDao databaseDao)throws SQLException,Exception{
        List<Product> products=new ArrayList<Product>();
        String sql;
        if(type.equals("all"))
        sql="select  productId,productType,price,productName,content,url,imgUrl,staticHtml from product";
        else
            sql="select  productId,productType,price,productName,content,url,imgUrl,staticHtml from product where productType='" + type +"'";
        databaseDao.query(sql);
        while (databaseDao.next()) {
            Product product=new Product();
            product.setProductId(databaseDao.getInt("productId"));
            product.setProductName(databaseDao.getString("productName"));
            product.setImgUrl(databaseDao.getString("imgUrl"));
            product.setContent(databaseDao.getString("content"));
            product.setProductType(databaseDao.getString("productType"));
            product.setPrice(databaseDao.getString("price"));
            product.setUrl(databaseDao.getString("url"));
            products.add(product);
        }
        return products;
    }
//    public List<ArticleNumberByMonthInAYear> articleNumberByMonthInAYearEveryYear() throws SQLException,Exception{
//        List<ArticleNumberByMonthInAYear> articleNumberByMonthInAYearEveryYearList =new ArrayList<ArticleNumberByMonthInAYear>();
//        String sql="select count(*) as articleNumber , YEAR(newsTime) as year1, MONTH(newsTime) as month1 from news   group by year1, month1 order by  year1,month1";
//        DatabaseDao databaseDao=new DatabaseDao();
//        databaseDao.query(sql);
//        Integer nowYear=0;
//        ArticleNumberByMonthInAYear articleNumberByMonthInAYear=null;
//        while (databaseDao.next()) {
//            Integer nowDatabaseYear=databaseDao.getInt("year1") ;
//
//            if(  !nowYear.equals(nowDatabaseYear) ){//新的一年  //注意：Integer相等必须用equals方法进行比较,不能直接用==比较是否相等
//                nowYear=nowDatabaseYear;
//                if(articleNumberByMonthInAYear!=null)
//                    articleNumberByMonthInAYearEveryYearList.add(articleNumberByMonthInAYear);//将旧的一年的数据加入数组
//                //新的一年的数据
//                articleNumberByMonthInAYear=new ArticleNumberByMonthInAYear();
//                articleNumberByMonthInAYear.setYear(nowYear);
//            }
//            //加入该月的数据
//            articleNumberByMonthInAYear.getArticleNumberByMonthList().set(databaseDao.getInt("month1")-1, databaseDao.getInt("articleNumber"));
//            articleNumberByMonthInAYear.setTotalNewsNumber(articleNumberByMonthInAYear.getTotalNewsNumber()+databaseDao.getInt("articleNumber"));
//        }
//        if(articleNumberByMonthInAYear!=null)
//            articleNumberByMonthInAYearEveryYearList.add(articleNumberByMonthInAYear);//将最后一年的数据加入数组
//
//        return articleNumberByMonthInAYearEveryYearList;
//    }
//    public List<News> getAll(DatabaseDao databaseDao)throws SQLException,Exception{
//        List<News> newses=new ArrayList<News>();
//        String sql="select * from news where staticHtml=0";//检索没有生成过静态网页的新闻
//
//        databaseDao.query(sql);
//        while (databaseDao.next()) {
//            News news=new News();
//            news.setNewsId(databaseDao.getInt("newsId"));
//            news.setNewsTime(databaseDao.getLocalDateTime("newsTime"));
//            news.setAuthor(databaseDao.getString("author"));
//            news.setNewsType(databaseDao.getString("newsType"));
//            news.setPublishTime(databaseDao.getTimestamp("publishTime"));
//            news.setUrl(databaseDao.getString("url"));
//            news.setCaption(databaseDao.getString("caption"));
//            news.setContent(databaseDao.getString("content"));
//
//            newses.add(news);
//        }
//        return newses;
//    }
//    public Integer setStaticHtml(DatabaseDao databaseDao)throws SQLException,Exception{
//        return databaseDao.update("update news set staticHtml=1 where staticHtml=0");
//    }
//    public Integer resetStaticHtml(DatabaseDao databaseDao)throws SQLException,Exception{
//        return databaseDao.update("update news set staticHtml=0");
//    }
//    public int batchUpdateUrl(List<News> newses, DatabaseDao databaseDao) throws SQLException, Exception{
//        String sql="update news set url=? where newsId=?";
//        databaseDao.createPreparedStatement(sql);
//        for( News news: newses){
//            databaseDao.setString(1, news.getUrl());
//            databaseDao.setInt(2, news.getNewsId());
//            databaseDao.addBatch();
//        }
//
//        databaseDao.executeBatch();
//        return 1;
//    }
}
