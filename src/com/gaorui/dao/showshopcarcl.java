package com.gaorui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.gaorui.model.CommodityBean;
import com.gaorui109.util.Dbcp;

public class showshopcarcl {
	PreparedStatement pstm=null;
	ResultSet rs =null;
	Connection conn=null;
	Statement st=null;
	
	/*
	 * 根据用户id从数据库w_cu商品，用户关联表里取出对应商品，放入到集合ArrayList里，范型为CommodityBean*/
	public ArrayList<CommodityBean> getshopcarCommodityids(String u_id)
	{
		ArrayList<CommodityBean> al= new ArrayList<CommodityBean>();
		Dbcp cd=new Dbcp();
		try {
			conn=cd.getConn();
			//这里sql语句做了处理，防止取出c_id,u_id,c_num 字段值相同的数据
			String sql="select c_id,u_id,c_num from w_cu  where u_id='"+u_id+"' group by c_id,u_id,c_num ";
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next())
			{
				CommodityBean cb = new CommodityBean();
				cb.setC_id(rs.getString("c_id"));
				cb.setU_id(rs.getString("u_id"));
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
	 * */
	
	
	public ArrayList<CommodityBean> getshopcarCommodity(String id)
	{
		ArrayList<CommodityBean> al= new ArrayList<CommodityBean>();
		Dbcp cd=new Dbcp();
		try {
			conn=cd.getConn();
			String sql="select * from w_commodity where c_id="+id;
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next())
			{
				CommodityBean cb = new CommodityBean();
				cb.setC_id(rs.getString("c_id"));
				cb.setDesc(rs.getString("desc"));
				cb.setImg(rs.getString("img"));
				cb.setTitle(rs.getString("title"));
				cb.setPrice(rs.getFloat("price"));
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
	 * 取出用户放入到购物车相应商品的数量*/
	public int getshopcount(String c_id,String u_id)
	{
		int num=0;
		Dbcp cd=new Dbcp();
		try {
			
			
			conn=cd.getConn();
			String sql="select count(*) s from w_cu where c_id='"+c_id+"'and u_id='"+u_id+"' and c_num='-1' group by u_id,c_id";
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next())
			{
				 num= rs.getInt("s");
			
			}
			
			
		System.out.println("高瑞测试num"+num);
			
		} catch (Exception e) {
			e.printStackTrace();
			}
		finally{
			cd.close(rs, pstm, conn);;
		}
		return num;
		
		
		
	}
	/*
	 * 取出用户付款后购买相应商品的数量*/
	public int getshopcount1(String c_id,String u_id)
	{
		int num=0;
		Dbcp cd=new Dbcp();
		try {
			
			
			conn=cd.getConn();
			String sql="select count(*) s from w_cu where c_id='"+c_id+"'and u_id='"+u_id+"' and c_num='0' group by u_id,c_id";
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next())
			{
				 num= rs.getInt("s");
			
			}
			
			
		System.out.println("高瑞测试num"+num);
			
		} catch (Exception e) {
			e.printStackTrace();
			}
		finally{
			cd.close(rs, pstm, conn);;
		}
		return num;}
	/*
	 * 取出后台管理员发货后相应商品的数量*/
	public int getshopcount2(String c_id,String u_id)
	{
		int num=0;
		Dbcp cd=new Dbcp();
		try {
			
			
			conn=cd.getConn();
			String sql="select count(*) s from w_cu where c_id='"+c_id+"'and u_id='"+u_id+"' and  c_num!='-1' and c_num!='0' group by u_id,c_id";
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next())
			{
				 num= rs.getInt("s");
			
			}
			
			
		System.out.println("高瑞测试num"+num);
			
		} catch (Exception e) {
			e.printStackTrace();
			}
		finally{
			cd.close(rs, pstm, conn);;
		}
		return num;}
}
