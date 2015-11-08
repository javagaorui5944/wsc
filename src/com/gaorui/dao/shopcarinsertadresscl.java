package com.gaorui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gaorui109.util.Dbcp;

public class shopcarinsertadresscl {
	PreparedStatement pstm=null;
	ResultSet rs =null;
	Connection conn=null;
	/*
	 * 用户确输入地址确认付款功能*/
	public boolean insertshopcaradress(String u_adress,String u_id)
	{
		boolean b = false;
		Dbcp cd=new Dbcp();
		String u_adress1=null;
		try {
			if(u_adress!=null){
				u_adress1=new String(u_adress.getBytes("ISO-8859-1"),"UTF-8");
				}
			System.out.println("增加商品加入sql测试u_adress:"+u_adress1);
			conn=cd.getConn();
			String sql="UPDATE w_cu SET u_adress ='"+u_adress+"',c_num='0' where u_id='"+u_id+"' and c_num='-1'";
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
