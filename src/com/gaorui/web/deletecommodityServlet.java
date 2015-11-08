package com.gaorui.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gaorui.dao.Managercl;
import com.gaorui.dao.UserBeancl;

/**
 * Servlet implementation class deletecommodityServlet
 */
public class deletecommodityServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
		{
			this.doPost(req, res);
		}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
		{
		/**
		 * 实现删除商品功能的Servlet,属于controller层，发送了json数据给前台，ajax数据交互
		 */
		res.setContentType("text/html;charset=utf-8");//转换，解决乱码。
			String id=req.getParameter("c_id");
			int id1 = Integer.parseInt(id);
			
			PrintWriter pw=res.getWriter();
			JSONObject ResultJson=new JSONObject();
			JSONObject jsonObject1=new JSONObject();
			JSONArray jsonArray=new JSONArray();
			Managercl mcl = new Managercl();
			int status=0;
	        String info=null;
				boolean a = false;
				a = mcl.deleteCommodity(id1);
				if(a){
	        		info="success";		
	        		ResultJson.put("result",info);
	        	
				}
				pw.println(ResultJson);     		
        		pw.flush();
        		pw.close();
	        /*if(ucl.CheckUser(Username,Userpasswd))
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
        	
	        	
	        }		*/
		}

}
