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

import net.sf.json.JSONObject;

import com.gaorui.dao.UserBeancl;
import com.gaorui.dao.registeredCl;


public class registeredServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	PreparedStatement ps=null;
	ResultSet rs =null;
	Connection conn=null;
	String Username;
	String Userpasswd;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * 实现用户注册的servlet,属于controller层，发送了json数据给前台，ajax数据交互
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html;charset=utf-8");//转换，解决乱码。
		response.setHeader("Access-Control-Allow-Origin","*");//设置响应头，确保手机端能访问到servlet
		PrintWriter pw=response.getWriter();	
        JSONObject ResultJson=new JSONObject();
        registeredCl rcl = new registeredCl();
        int status=0;
        String info=null;
        Username=request.getParameter("email");
		Userpasswd=request.getParameter("password");
		System.out.println("测试111"+Username+Userpasswd);
		
		if(rcl.sameUsernamecl(Username)){
			status=50;
    		info="Username已经存在";
    		ResultJson.put("status",status);
    		ResultJson.put("info",info);
    		pw.println(ResultJson);
    		
    		pw.flush();
    		pw.close();

		}
	
		else   if(rcl.registeredcl(Username,Userpasswd))
        	{
        	
        	HttpSession session = request.getSession(true);
	    	session.removeAttribute("Username");  
	        	session.setAttribute("Username", Username);
        		status=200;
        		info="success";
        		ResultJson.put("status",status);
        		ResultJson.put("info",info);
        		pw.println(ResultJson);
        		
        		pw.flush();
        		pw.close();
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


