package com.gaorui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gaorui109.util.Dbcp;

public class deleteshopcarcommoditycl {
	PreparedStatement pstm=null;
	ResultSet rs =null;
	Connection conn=null;
	/*
	 * 根据用户id和商品id删除放入购物车的商品*/
	public boolean deleteshopcarCommodity(String c_id,String u_id)
	{
		boolean b = false;
		Dbcp cd=new Dbcp();
		try {
			conn=cd.getConn();
			String sql="delete from w_cu where u_id='"+u_id+"' and c_id='"+c_id+"'";
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
