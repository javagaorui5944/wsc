package com.gaorui.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gaorui.dao.showcommoditycl;
import com.gaorui.model.CommodityBean;

/**
 * Servlet implementation class showcommodityServlet
 */
public class showcommodityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 this.doPost(request, response);
	}

	/**
	 * 实现发送给前台所有商品数据的servlet,属于controller层，发送了json数据给前台，ajax数据交互
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin","*");//设置响应头，确保手机端能访问到servlet
		response.setContentType("text/html;charset=utf-8");//转换，解决乱码。
		HttpSession session = request.getSession(true);
		//String a = (String)session.getAttribute("Username");
		//System.out.println(a+"Session测试");
		PrintWriter pw=response.getWriter();
		JSONObject ResultJson=new JSONObject();
		JSONObject jsonObject1=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		//CommodityBean cb=new CommodityBean();
		showcommoditycl cmc = new showcommoditycl();
		ArrayList<CommodityBean> al=cmc.getCommodity();
		//String link="../productdetailsServlet?id=";
		for(int i=0;i<al.size();i++)
		{
			CommodityBean cb1 =al.get(i);
			jsonObject1.put("c_id",cb1.getC_id());
			jsonObject1.put("title",cb1.getTitle());
			//jsonObject1.put("link",link+cb1.getC_id());
			jsonObject1.put("desc",cb1.getDesc());
			jsonObject1.put("img",cb1.getImg());
			jsonObject1.put("price",cb1.getPrice());
			
			jsonArray.add(jsonObject1);
		//	System.out.println(cb1.getC_id()+"xxxx");
		}
		
		pw.println(jsonArray);
		pw.flush();
		pw.close(); 
	}


}
