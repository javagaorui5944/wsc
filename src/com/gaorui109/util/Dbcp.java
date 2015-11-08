package com.gaorui109.util;
import  java.sql.*;
public class Dbcp
{
	private  Connection ct=null;
	public Connection getConn()
		{
		try
			{
					//连接数据库
		 	/*	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//加载MYSQL JDBC驱动程序   
				ct=DriverManager.getConnection("jdbc:sqlserver://localhost:5051/waibao","sa","yeyuan0110");//连接数据库
				System.out.println("连接成功");*/
			String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  //加载JDBC驱动
			  String dbURL = "jdbc:sqlserver://localhost:5051; DatabaseName=waibao";  //连接服务器和数据库test
			  String userName = "sa";  //默认用户名
			  String userPwd = "yeyuan0110";  //密码
			  Class.forName(driverName);
			   ct = DriverManager.getConnection(dbURL, userName, userPwd);
			 //  System.out.println("Connection Successful!");  //如果连接成功 控制台输出Connection Successful!
			}
catch (Exception ex) {
	ex.printStackTrace();
					 }
			return  ct;
	
	   }
	public static void close(ResultSet rs,PreparedStatement pstm,Connection conn){
		if(rs!=null){
		try{	rs.close();}
		catch(SQLException e){
			e.printStackTrace();
		}
			rs=null;
		}
		if(pstm!=null){
			try{	pstm.close();}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			
			pstm=null;
		}
		if(conn!=null){
			try{	conn.close();}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			conn=null;
		}
	}
	
	
	public static void close(Connection conn,ResultSet rs) {
		if(rs!=null){
		try{	rs.close();}
		catch(SQLException e){
			e.printStackTrace();
		}
			rs=null;
		}
		if(conn!=null){
			try{	conn.close();}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			conn=null;
		}
	}
	
	public static void close(Connection conn,PreparedStatement pstm){
		if(pstm!=null){
			try{	pstm.close();}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			
			pstm=null;
		}
		if(conn!=null){
			try{	conn.close();}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			conn=null;
		}
	}
	
	public static void close(PreparedStatement pstm) {
		if(pstm!=null){
			try{	pstm.close();}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			
			pstm=null;
		}
	}
	
	public static void close(Connection conn,Statement stm,ResultSet rs){

		if(stm!=null){
			try{	stm.close();}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			
			stm=null;
		}
		if(rs!=null){
			try{	rs.close();}
			catch(SQLException e){
				e.printStackTrace();
			}
				rs=null;
			}
			if(conn!=null){
				try{	conn.close();}
				catch(SQLException e){
					e.printStackTrace();
				}
				
				conn=null;
			}
	}
	
	
}
