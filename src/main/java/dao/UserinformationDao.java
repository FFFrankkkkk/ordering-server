package dao;

import bean.Userinformation;

import java.sql.SQLException;

public class UserinformationDao {
	public Userinformation getByUserId(Integer userId, DatabaseDao databaseDao) throws SQLException{
		Userinformation userinformation=null;
		String sql="select * from userinformation where userId="+userId;
		databaseDao.query(sql);
		while(databaseDao.next()){
			userinformation=new Userinformation();
			userinformation.setSex(databaseDao.getString("sex"));
			userinformation.setAddress(databaseDao.getString("address"));
		}
		return userinformation;
	}
	
	public Integer update(Userinformation userinformation, DatabaseDao databaseDao) throws SQLException{
		String sql="update userinformation set sex='"+userinformation.getSex()
					+"',hobby='"+userinformation.getAddress()
					+"' where userId="+userinformation.getUserId();
		return databaseDao.update(sql);
	}	
	
	public Integer insert(Userinformation userinformation, DatabaseDao databaseDao) throws SQLException{
		String sql="insert into userinformation(userId,sex,hobby) values("
						+userinformation.getUserId()+",'"
						+userinformation.getSex()+"','"
						+userinformation.getAddress()+"')";
		return databaseDao.update(sql);
	}	
	
	public boolean hasUserId(Integer userId, DatabaseDao databaseDao) throws SQLException{
		String sql="select * from userinformation where userId="+userId.toString();
		databaseDao.query(sql);
		while(databaseDao.next()){
			return true;
		}
		return false;
	}
}
