package com.gaorui.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gaorui.dao.UserBeancl;

/**
 * Servlet implementation class LoginsameclServlet
 */
public class LoginsameclServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PreparedStatement ps=null;
	ResultSet rs =null;
	Connection conn=null;
	String Username;
	String Userpasswd;
	
	
   
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
		{
			this.doPost(req, res);
		}

	/**
	 * 实现判断前台用户注册界面输入Username是否已经存在的servlet，,属于controller层，发送了json数据给前台，ajax数据交互
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
		{
		res.setHeader("Access-Control-Allow-Origin","*");//设置响应头，确保手机端能访问到servlet
			String action=req.getParameter("action");
			Username=req.getParameter("email");
			Userpasswd=req.getParameter("password");
			System.out.println("测试111"+Username+Userpasswd);
			
			PrintWriter pw=res.getWriter();
			JSONObject ResultJson=new JSONObject();
			JSONObject jsonObject1=new JSONObject();
			JSONArray jsonArray=new JSONArray();
			UserBeancl ucl = new UserBeancl();
	        int status=0;
	        String info=null;
	        if(ucl.CheckUser(Username,Userpasswd))
	        	{
	        	
	    	HttpSession session = req.getSession(true);
	    	session.removeAttribute("Username");  
	        	session.setAttribute("Username", Username);
	        	status=200;
        		info="success";
        		ResultJson.put("status",status);
        		ResultJson.put("info",info);
        	
        		pw.println(ResultJson);
        		
        		pw.flush();
        		pw.close();
	/*        	
	       String result = "\"data\":\"{\"status\":\""+ 200 +"\",\"Userage\":\" "+ 200+" \"}";  
	    	pw.println(result);
			pw.flush();
			pw.close();*/
	        	
	 	      
	    /*	
	      	jsonObject1.put("status",status);
				jsonArray.add(jsonObject1);
			
			ResultJson.put("data",jsonArray);
			pw.println(ResultJson);
			pw.flush();
			pw.close(); */
	        	}
	        else
	        {
	        	status=0;
        		info="failed";
        		ResultJson.put("status",status);
        		ResultJson.put("info",info);
            	pw.println(ResultJson);
        		pw.flush();
        		pw.close();
        	
	        	
	        }		
		}
}
