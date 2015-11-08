package com.gaorui.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gaorui.dao.showcommoditycl;
import com.gaorui.model.CommodityBean;

/**
 * Servlet implementation class getcommodityServlet1
 */
public class getcommodityServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin","*");
		this.doPost(request, response);
	}

	/**
	 * 实现发送数据库中所有商品数据的servlet,属于controller层，发送了json数据给前台，ajax数据交互
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");//转换，解决乱码。
		response.setHeader("Access-Control-Allow-Origin","*");//设置响应头，确保手机端能访问到servlet
		PrintWriter pw=response.getWriter();
		JSONObject ResultJson=new JSONObject();
		JSONObject jsonObject1=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		String id = request.getParameter("id");
		String name= request.getParameter("name");
		System.out.println("2336测试id:"+name);
		if(id==null){
		showcommoditycl cmc = new showcommoditycl();
		ArrayList<CommodityBean> al=cmc.getCommodity();
		String link="./shop.html?id=";
		for(int i=0;i<al.size();i++)
		{
			CommodityBean cb1 =al.get(i);
			jsonObject1.put("c_id",cb1.getC_id());
			jsonObject1.put("title",cb1.getTitle());
			jsonObject1.put("link",link+cb1.getC_id()+"&="+name+"");
			jsonObject1.put("desc",cb1.getDesc());
			jsonObject1.put("img",cb1.getImg());
			jsonArray.add(jsonObject1);
			System.out.println(cb1.getC_id()+"xxxx");
		}
			ResultJson.put("students",jsonArray);
			//ResultJson.put("name", name);
			pw.println(ResultJson);
			pw.flush();
			pw.close(); 
			
		}
			
			
			
		//	String id = request.getParameter("id");
			if(id!=null){
			System.out.println("判断字符串是否有整数"+isNum(id));
			if(isNum(id)){
				
				String id11 = request.getParameter("id");
				System.out.println("ajax2次调用："+id);
				HttpSession session = request.getSession();
		
				session.setAttribute("id11",id11);
				System.out.println("session第一存id"+id11);
				int status=200;
	    		String info="success";
	    		ResultJson.put("status",status);
	    		ResultJson.put("info",info);
	    	
	    		pw.println(ResultJson);
	    		
	    		pw.flush();
	    		pw.close();
			}
			else{
				int status=100;
	    		String info="success";
	    		ResultJson.put("status",status);
	    		ResultJson.put("info",info);
	    	
	    		pw.println(ResultJson);
	    		
	    		pw.flush();
	    		pw.close();
			}
			
		}
	
	
	
	
			
		
	
}
	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");}
	}
