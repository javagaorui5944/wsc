package com.gaorui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.gaorui.model.CommodityBean;
import com.gaorui109.util.Dbcp;


public class shopcarcl {
	PreparedStatement pstm=null;
	ResultSet rs =null;
	Connection conn=null;
	/*
	 * 根据用户Username找出对应id*/
	public String getu_idByu_name(String Username)
	{
		
		Dbcp cd=new Dbcp();
		String id1 = null;
		try {
			System.out.println("Username"+Username);
			conn=cd.getConn();
			String sql="select u_id from w_user where u_name='"+Username+"'";
			//pstm.setString(1,Username);
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next())
			{
				 id1 = rs.getString("u_id");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			}
		finally{
			cd.close(rs, pstm, conn);;
		}
		return id1;		
	}
	/*
	 * 将用户所订购商品放入购物车*/
	public boolean insertshopCommodity(String u_id,String c_id,String title)
	{
		boolean b =false;
		Dbcp cd=new Dbcp();
		try {
			conn=cd.getConn();
			String sql="insert into w_cu(u_id,c_id,title,c_num) values('"+u_id+"','"+c_id+"','"+title+"','-1')";
			pstm=conn.prepareStatement(sql);
			int a =pstm.executeUpdate();
			if(a>0){
				b=true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			}
		finally{
			cd.close(rs, pstm, conn);;
		}
		return b;		
	}
	/*
	 * 根据商品id查找出商品的title*/
	
	public String get_titleByc_id(String c_id)
	{
		String title=null;
		Dbcp cd=new Dbcp();
		try {
			
			conn=cd.getConn();
			String sql="select title from w_commodity where c_id="+c_id;
			//pstm.setString(1,Username);
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next())
			{
				title = rs.getString("title");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			}
		finally{
			cd.close(rs, pstm, conn);;
		}
		return title;		
	}
}
