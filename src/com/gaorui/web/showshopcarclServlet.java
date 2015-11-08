package com.gaorui.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gaorui.dao.shopcarcl;
import com.gaorui.dao.showshopcarcl;
import com.gaorui.model.CommodityBean;

/**
 * Servlet implementation class showshopcarclServlet
 */
public class showshopcarclServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin","*");
		this.doPost(request, response);
		
	}

	/**
	 * 实现发送对应用户放入购物车的商品数据到用户前台购物车的servlet,属于controller层，发送了json数据给前台，ajax数据交互
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");//转换，解决乱码。
		response.setHeader("Access-Control-Allow-Origin","*");//设置响应头，确保手机端能访问到servlet
	/*	HttpSession session = request.getSession(true);*/
		PrintWriter pw=response.getWriter();
		JSONObject ResultJson=new JSONObject();
		JSONObject jsonObject1=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		/*String Username=(String) session.getAttribute("Username");*/
		String Username = (String)request.getParameter("Username");  
		System.out.println("3:19测试，加油："+Username);
		shopcarcl scl = new shopcarcl();
		String u_id = scl.getu_idByu_name(Username); 
		showshopcarcl sscl = new showshopcarcl();
		int num = 0;
		ArrayList<CommodityBean> al = sscl.getshopcarCommodityids(u_id);
		ArrayList<CommodityBean> al1 = null;
		for(int i = 0 ; i<al.size(); i++){
			CommodityBean cb =al.get(i);
			
			String s = cb.getC_num();
			/*if(s!=null){*/
		//	System.out.println(s.equals("0")+"蛋疼测试");
			if(s!=null){
			if(s.equals("-1")){
			num =	sscl.getshopcount(cb.getC_id(),cb.getU_id());
			if(num<1){
					num=1;
			}
		
					String c_id=	cb.getC_id();
					if(isNum(c_id)){
					al1 = sscl.getshopcarCommodity(c_id);	
					for(int j=0;j<al1.size();j++){
						CommodityBean cb1 = al1.get(j);
						jsonObject1.put("c_id",cb1.getC_id());
						jsonObject1.put("title",cb1.getTitle());
						//jsonObject1.put("link",cb1.getC_id());
					//	jsonObject1.put("desc",cb1.getDesc());
						jsonObject1.put("img",cb1.getImg());
						jsonObject1.put("num",num);
						jsonObject1.put("price", cb1.getPrice());
						System.out.println(cb1.getC_id()+"xxxx");	
						jsonArray.add(jsonObject1);
					}
					}
			}
			}
		}
		
	
		ResultJson.put("students",jsonArray);
		pw.println(ResultJson);
		pw.flush();
		pw.close(); 
		/*request.getRequestDispatcher("/shop/shopcar1.jsp").forward(request, response);*/
	}
	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");}

}

