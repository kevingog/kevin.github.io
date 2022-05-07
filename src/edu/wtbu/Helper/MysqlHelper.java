package edu.wtbu.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class MysqlHelper {
	private static String url = "jdbc:mysql://localhost:3306/geth?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
	private static String user = "root";
	private static String password = "123456";
	private static PreparedStatement pstmt = null;
	private static Connection conn = null;
	private static ResultSet rs = null;
	static{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static int executeUpdate(String sql,Object[] parma) {
		int rs = 0;
		try {
			conn=DriverManager.getConnection(url, user, password);
			pstmt=conn.prepareStatement(sql);
			if(parma!=null) {
				for (int i = 0; i < parma.length; i++) {
					String ClassName=parma[i].getClass().getName();
					if(ClassName.contains("String")) {
						pstmt.setString(i+1,parma[i].toString());
					}else if(ClassName.contains("Integer")) {
						pstmt.setInt(i+1,Integer.parseInt(parma[i].toString()));
					}
				}
			}
	
		rs=	pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CloseAll();
		return rs;
	}
	public static ResultSet executeQuery(String sql,Object[] parma) {
		ResultSet rs =null;
		try {
			conn=DriverManager.getConnection(url, user, password);
			pstmt=conn.prepareStatement(sql);
			if(parma!=null) {
				for (int i = 0; i < parma.length; i++) {
					String ClassName=parma[i].getClass().getName();
					if(ClassName.contains("String")) {
						pstmt.setString(i+1,parma[i].toString());
					}else if(ClassName.contains("Integer")) {
						pstmt.setInt(i+1,Integer.parseInt(parma[i].toString()));
					}
				}
			}
		rs=	pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public static void CloseAll() {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}