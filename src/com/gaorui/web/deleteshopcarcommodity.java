package com.gaorui.web;

import java.awt.image.RescaleOp;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gaorui.dao.Managercl;
import com.gaorui.dao.deleteshopcarcommoditycl;
import com.gaorui.dao.shopcarcl;

/**
 * Servlet implementation class deleteshopcarcommodity
 */
public class deleteshopcarcommodity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		this.doPost(req, res);
	}


protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
	/**
	 * 实现删除放入购物车商品功能的servlet,属于controller层，发送了json数据给前台，ajax数据交互
	 */
	res.setHeader("Access-Control-Allow-Origin","*");//设置响应头，确保手机端能访问到servlet
	res.setContentType("text/html;charset=utf-8");//转换，解决乱码。
		String c_id=(String)req.getParameter("c_id");
		String Username = (String)req.getParameter("Username");
		PrintWriter pw=res.getWriter();
		JSONObject ResultJson=new JSONObject();
		JSONObject jsonObject1=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		shopcarcl scl = new shopcarcl();
		String u_id = scl.getu_idByu_name(Username); 
		System.out.println("删除购物车的物品057测试:"+u_id+"c_id"+c_id);
		deleteshopcarcommoditycl dcdcl = new deleteshopcarcommoditycl();
		boolean a = dcdcl.deleteshopcarCommodity(c_id, u_id);
		int status=0;	
        String info=null;
			
			if(a){
        		info="success";	
        		status=200;
        		ResultJson.put("status",status);
        		ResultJson.put("result",info);
        	
			}
			pw.println(ResultJson);     		
    		pw.flush();
    		pw.close();
	}

}
