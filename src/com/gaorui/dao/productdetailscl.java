package com.gaorui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.gaorui.model.CommodityBean;
import com.gaorui109.util.Dbcp;


public class productdetailscl {
	PreparedStatement pstm=null;
	ResultSet rs =null;
	Connection conn=null;
	/*
	 *取出数据库w_commodity商品表中的所有数据 ，放入到集合ArrayList里，范型为CommodityBean
	 * */
	public ArrayList<CommodityBean> getCommodity(String id)
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
				//cb.setLink(rs.getString("link"));
				cb.setTitle(rs.getString("title"));
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
