package com.gaorui.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gaorui109.util.Dbcp;


public class registeredCl {
	/*
	 *用户注册功能 */
	PreparedStatement pstm=null;
	ResultSet rs =null;
	Connection conn=null;
	public boolean registeredcl(String Username,String Password)
	{
		boolean b=false;
		Dbcp cd=new Dbcp();
		try {
			conn=cd.getConn();
		String sql="insert into w_user(u_name,u_passwd) values('"+Username+"','"+Password+"')";
		pstm=conn.prepareStatement(sql);
		int num=pstm.executeUpdate();
		if(num==1)
			{	
				b=true;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			cd.close(rs, pstm, conn);
		}
		return b;
	}
	
	
	public boolean sameUsernamecl(String Username)
	{
		boolean b=false;
		Dbcp cd=new Dbcp();
		try {
			conn=cd.getConn();
		String sql="select u_name from w_user where u_name='"+Username+"'";
		pstm=conn.prepareStatement(sql);
		rs=pstm.executeQuery();
		if(rs.next())
			{	
				b=true;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			cd.close(rs, pstm, conn);
		}
		return b;
	}
}
