package com.gaorui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.gaorui.model.CommodityBean;
import com.gaorui109.util.Dbcp;

public class showordercommoditycl {

	PreparedStatement pstm=null;
	ResultSet rs =null;
	Connection conn=null;
	/*
	 * 从数据库w_cu商品，用户关联表中取出相应数据*/
	public ArrayList<CommodityBean> getc_idfromw_cu()
	{
		ArrayList<CommodityBean> al= new ArrayList<CommodityBean>();
		Dbcp cd=new Dbcp();
		try {
			conn=cd.getConn();
			String sql="select distinct u_id,c_id,title,c_num,u_adress from w_cu";
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			String u_adress1 = null; 
			while(rs.next())
			{
				//u_adress1=new String(rs.getString("u_adress").getBytes("ISO-8859-1"),"UTF-8");
				CommodityBean cb = new CommodityBean();
				cb.setC_id(rs.getString("c_id"));
				cb.setU_id(rs.getString("u_id"));
				cb.setTitle(rs.getString("title"));
				cb.setC_num(rs.getString("c_num"));
				cb.setU_adress(rs.getString("u_adress"));
				al.add(cb);
				System.out.println("u_adress1"+u_adress1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			}
		finally{
			cd.close(rs, pstm, conn);;
		}
		return al;		
	}
	
	/*
	 * 根据用户id从数据库w_cu商品，用户关联表中取出相应数据*/
	
	public ArrayList<CommodityBean> getuserorderbyu_id(String u_id)
	{
		ArrayList<CommodityBean> al= new ArrayList<CommodityBean>();
		Dbcp cd=new Dbcp();
		try {
			conn=cd.getConn();
			String sql="select distinct u_id,c_id,title,c_num from w_cu where u_id='"+u_id+"'";
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next())
			{
				CommodityBean cb = new CommodityBean();
				cb.setC_id(rs.getString("c_id"));
				cb.setU_id(rs.getString("u_id"));
				cb.setTitle(rs.getString("title"));
				cb.setC_num(rs.getString("c_num"));
				al.add(cb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			}
		finally{
			cd.close(rs, pstm, conn);;
		}
		return al;		
	}
	
	/*
	 * 根据用户id从数据库w_cu商品，用户关联表取出已经发货的物品对应数据*/
	
	public ArrayList<CommodityBean> getlogistics(String u_id)
	{
		ArrayList<CommodityBean> al= new ArrayList<CommodityBean>();
		Dbcp cd=new Dbcp();
		try {
			conn=cd.getConn();
			String sql="select distinct u_id,c_id,title,c_num from w_cu where u_id='"+u_id+"' and c_num!=-1 and c_num!=0";
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next())
			{
				CommodityBean cb = new CommodityBean();
				cb.setC_id(rs.getString("c_id"));
		/*		cb.setU_id(rs.getString("u_id"));
				cb.setTitle(rs.getString("title"));
				cb.setC_num(rs.getString("c_num"));*/
				al.add(cb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			}
		finally{
			cd.close(rs, pstm, conn);;
		}
		return al;		
	}
	
	/*
	 * 根据商品id从商品表中取出对应的图片*/
	
	
	public ArrayList<CommodityBean> getlogisticsimg(String c_id)
	{
		ArrayList<CommodityBean> al= new ArrayList<CommodityBean>();
		Dbcp cd=new Dbcp();
		try {
			conn=cd.getConn();
			String sql="select img from w_commodity where c_id='"+c_id+"'";
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next())
			{
				CommodityBean cb = new CommodityBean();
				cb.setImg(rs.getString("img"));
		/*		cb.setU_id(rs.getString("u_id"));
				cb.setTitle(rs.getString("title"));
				cb.setC_num(rs.getString("c_num"));*/
				al.add(cb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			}
		finally{
			cd.close(rs, pstm, conn);;
		}
		return al;		
	}
}
