package com.gaorui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gaorui109.util.Dbcp;

public class sendordercommoditycl {
		PreparedStatement pstm=null;
		ResultSet rs =null;
		Connection conn=null;
		/*
		 * 后台管理员发送货物的功能*/
	public boolean updatec_num(int c_num,String c_id,String u_id)
	{
		boolean b =false;
		Dbcp cd=new Dbcp();
		try {
			conn=cd.getConn();
			String sql="UPDATE w_cu SET c_num = "+c_num+" where u_id='"+u_id+"' and c_id='"+c_id+"'";
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
