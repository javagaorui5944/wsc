package com.gaorui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.gaorui.model.CommodityBean;
import com.gaorui109.util.Dbcp;

/*
 * 从数据里取出所有商品，放入到集合ArrayList里，范型为CommodityBean*/
public class showcommoditycl {
	PreparedStatement pstm=null;
	ResultSet rs =null;
	Connection conn=null;
	/*public boolean messagecl(String me_node,String user_name)
	{
		boolean b=false;
		Dbcp cd= new Dbcp();
		try {
			conn=cd.getConn();	
			String sql="insert into message(me_node,user_name) value('"+me_node+"','"+user_name+"')";
			pstm=conn.prepareStatement(sql);
			int num=pstm.executeUpdate();
			if(num==1)
				{
					b=true;
				}
		} catch (Exception e) {
			e.printStackTrace();
		}finally
			{
				cd.close(conn,pstm, rs);
			}
		return b;
	}*/
	public ArrayList<CommodityBean> getCommodity()
	{
		ArrayList<CommodityBean> al= new ArrayList<CommodityBean>();
		Dbcp cd=new Dbcp();
		try {
			conn=cd.getConn();
			String sql="select * from w_commodity";
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next())
			{
				CommodityBean cb = new CommodityBean();
				cb.setC_id(rs.getString("c_id"));
				cb.setDesc(rs.getString("desc"));
				cb.setImg(rs.getString("img"));
				cb.setLink(rs.getString("link"));
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
	 * 根据商品id从数据里取出对应商品，放入到集合ArrayList里，范型为CommodityBean*/
	
	public ArrayList<CommodityBean> getCommodity1(String id)
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
				cb.setLink(rs.getString("link"));
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
}
