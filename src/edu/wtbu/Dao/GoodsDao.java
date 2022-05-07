package edu.wtbu.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.wtbu.Helper.MysqlHelper;
import edu.wtbu.pojo.Goods;

public class GoodsDao {
    public static ArrayList<Goods> findGoodsList() {
  	    ArrayList<Goods> goodslist=new ArrayList<Goods>();
  	    String sql="select * from goods";
  	    ResultSet rs=MysqlHelper.executeQuery(sql, null);
  	    try {
				while(rs.next()) {
					Goods goods =new Goods();
					goods.setId(rs.getInt(1));
					goods.setName(rs.getString(2));
					goods.setPrice(rs.getFloat(3));
					goods.setImgUrl(rs.getString(4));
					goods.setSellerAddress(rs.getString(5));
					goodslist.add(goods);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  	    MysqlHelper.CloseAll();
  	    return goodslist;
  	    
    }
}
