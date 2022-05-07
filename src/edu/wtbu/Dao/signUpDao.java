package edu.wtbu.Dao;

import edu.wtbu.Helper.MysqlHelper;
import edu.wtbu.geth.geth;

public class signUpDao {
       public static int signUpUser(String name,String email,String password,String address) {
   
    	   String sql="insert into user(name,email,password,address) values(?,?,?,?)";
    	   int rs=MysqlHelper.executeUpdate(sql, new Object[] {name,email,password,address});
    	   return rs;
       }
}
