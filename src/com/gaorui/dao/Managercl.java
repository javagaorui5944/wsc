package com.gaorui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.gaorui.model.CommodityBean;
import com.gaorui109.util.Dbcp;

public class Managercl {
	PreparedStatement pstm=null;
	ResultSet rs =null;
	Connection conn=null;
/*
 * 微商城后台管理系统dao层代码块*/
	
	/*
	 * 增加商品功能*/
	public boolean insertCommodity(String img,String desc,String title,String price)
	{
		boolean b = false;
		Dbcp cd=new Dbcp();
		try {
			/*
			 * 转码，防止插入数据库乱码*/
			String title1=new String(title.getBytes("ISO-8859-1"),"UTF-8");
			String desc1=new String(desc.getBytes("ISO-8859-1"),"UTF-8");
			String img1=new String(img.getBytes("ISO-8859-1"),"UTF-8");
			conn=cd.getConn();
			String sql="insert into w_commodity(title,[desc],img,price) values('"+title1+"','"+desc1+"','"+img1+"',"+price+")";
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
	 * 根据前台传过来的商品id删除商品*/
	
	public boolean deleteCommodity(int id)
	{
		boolean b = false;
		Dbcp cd=new Dbcp();
		try {
			conn=cd.getConn();
			String sql="delete from w_commodity where c_id="+id;
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
	
}
