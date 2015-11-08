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

import com.gaorui.dao.productdetailscl;
import com.gaorui.dao.showcommoditycl;
import com.gaorui.model.CommodityBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class messageshowServlet
 */
public class getcommodityServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		response.setHeader("Access-Control-Allow-Origin","*");
	
		this.doPost(request, response);
		}
		



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		/**
		 * 实现发送用户放入购物车的商品数据的servlet,属于controller层，发送了json数据给前台，ajax数据交互
		 */
		
		response.setHeader("Access-Control-Allow-Origin","*");//设置响应头，确保手机端能访问到servlet
		response.setContentType("text/html;charset=utf-8");//转换，解决乱码。
		PrintWriter pw=response.getWriter();
		JSONObject ResultJson=new JSONObject();
		JSONObject jsonObject1=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		String id = request.getParameter("id");
	System.out.println("测试id"+id);
		if(isNum(id)){
			
		//	String id11 = request.getParameter("id");
			System.out.println("ajax2次调用："+id);
			int status=200;
    		//String info="success";
    		ResultJson.put("status",status);
    	
			showcommoditycl cmc = new showcommoditycl();
			ArrayList<CommodityBean> al=cmc.getCommodity1(id);
			String link="#";
			for(int i=0;i<al.size();i++)
			{
				
				CommodityBean cb1 =al.get(i);
				ResultJson.put("c_id",cb1.getC_id());
				ResultJson.put("title",cb1.getTitle());
				ResultJson.put("link",link+cb1.getC_id());
				ResultJson.put("desc",cb1.getDesc());
				ResultJson.put("img",cb1.getImg());
				
				System.out.println(cb1.getC_id()+"xxxx");
			}
			jsonObject1.put("dataa",ResultJson);
				pw.println(jsonObject1);
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
	/*	showcommoditycl cmc = new showcommoditycl();
		ArrayList<CommodityBean> al=cmc.getCommodity1(id11);
		String link="#";
		for(int i=0;i<al.size();i++)
		{
			
			CommodityBean cb1 =al.get(i);
			jsonObject1.put("c_id",cb1.getC_id());
			jsonObject1.put("title",cb1.getTitle());
			jsonObject1.put("link",link+cb1.getC_id());
			jsonObject1.put("desc",cb1.getDesc());
			jsonObject1.put("img",cb1.getImg());
			jsonArray.add(jsonObject1);
			System.out.println(cb1.getC_id()+"xxxx");
		}
			ResultJson.put("students",jsonArray);
			pw.println(ResultJson);
			pw.flush();
			pw.close(); */
	/*	}*/
		}
	
	
	public static boolean isNum(String str){
		boolean a= false;
		if(str!=null){
		 a = str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
		}
		return a;
	}
}


