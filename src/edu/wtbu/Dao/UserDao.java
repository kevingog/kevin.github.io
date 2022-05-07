package edu.wtbu.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.wtbu.Helper.MysqlHelper;
import edu.wtbu.pojo.User;

public class UserDao {
      public static User findEmailAndPassword(String email,String password) {
    	    User user=null;
    	    String sql="select * from user where email=? and password=?";
    	    ResultSet rs=MysqlHelper.executeQuery(sql, new Object[] {email,password});
    	    try {
				while(rs.next()) {
					user =new User();
					user.setId(rs.getInt(1));
					user.setName(rs.getString(2));
					user.setEmail(rs.getString(3));
					user.setPassword(rs.getString(4));
					user.setAddress(rs.getString(5));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	    MysqlHelper.CloseAll();
    	    return user;
    	    
      }
      public static User findEmail(String email) {
  	    User user=null;
  	    String sql="select * from user where email=?";
  	    ResultSet rs=MysqlHelper.executeQuery(sql, new Object[] {email});
  	    try {
				while(rs.next()) {
					user =new User();
					user.setId(rs.getInt(1));
					user.setName(rs.getString(2));
					user.setEmail(rs.getString(3));
					user.setPassword(rs.getString(4));
					user.setAddress(rs.getString(5));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  	    MysqlHelper.CloseAll();
  	    return user;
  	    
    }
}
