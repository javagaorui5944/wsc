package com.gaorui.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gaorui.dao.shopcarcl;
import com.gaorui.dao.showordercommoditycl;
import com.gaorui.dao.showshopcarcl;
import com.gaorui.model.CommodityBean;

/**
 * Servlet implementation class checklogisticsServlet
 */
public class checklogisticsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * 实现查看货流的servlet,属于controller层，发送了json数据给前台，ajax数据交互
		 */
		response.setHeader("Access-Control-Allow-Origin","*");//设置响应头，确保手机端能访问到servlet
		response.setContentType("text/html;charset=utf-8");//转换，解决乱码。
		PrintWriter pw=response.getWriter();
		JSONObject ResultJson=new JSONObject();
		JSONObject jsonObject1=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		
		String Username = (String)request.getParameter("Username");  
		System.out.println("订单处理：Username"+Username);
		shopcarcl scl = new shopcarcl();
		String u_id = scl.getu_idByu_name(Username);
		showordercommoditycl scdc = new showordercommoditycl();
		ArrayList<CommodityBean> ar1 = scdc.getlogistics(u_id);
		for(int j=0;j<ar1.size();j++){
			CommodityBean cb1 = ar1.get(j);
			int num=0;
			showshopcarcl sscl = new showshopcarcl();
			if(num<1){
				num=1;
			}
			ArrayList<CommodityBean>  al1 = scdc.getlogisticsimg(cb1.getC_id());
			for(int j1 = 0 ; j1<al1.size() ;j1++){
				jsonObject1.put("img", al1.get(j1).getImg());
				jsonArray.add(jsonObject1);
		
		
	
			
			System.out.println("c_id"+cb1.getC_id()+"u_id"+cb1.getU_id());
		/*	}*/
			}
		}
		ResultJson.put("students",jsonArray);
		pw.println(ResultJson);
		pw.flush();
		pw.close(); 
	}

}
