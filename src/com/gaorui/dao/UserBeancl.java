package com.gaorui.dao;




import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.gaorui.model.CommodityBean;
import com.gaorui109.util.Dbcp;
public class UserBeancl {
	PreparedStatement pstm=null;
	ResultSet rs =null;
	Connection conn=null;
	/*
	 * 在数据库里w_user查找数据判断用户名和密码*/
	public boolean CheckUser(String Username,String Passwd)
		{
			boolean b=false;
			Dbcp cd=new Dbcp();
			try {
				conn=cd.getConn();
				String sql="select * from w_user where u_name='"+Username+"' and u_passwd='"+Passwd+"'";
				pstm=conn.prepareStatement(sql);
				rs=pstm.executeQuery();
				while(rs.next())
					{
					   b=true;
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				cd.close(conn,pstm,rs);	
			}
			return b;
		}
}
