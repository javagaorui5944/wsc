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

import com.gaorui.dao.shopcarcl;
import com.gaorui.dao.showordercommoditycl;
import com.gaorui.dao.showshopcarcl;
import com.gaorui.model.CommodityBean;

/**
 * Servlet implementation class showordercommodityServlet
 */
public class showordercommodityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	/**
	 * 实现发送用户已付款的商品的数据给后台管理界面的servlet,属于controller层，发送了json数据给前台，ajax数据交互
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");//转换，解决乱码。
		response.setHeader("Access-Control-Allow-Origin","*");//设置响应头，确保手机端能访问到servlet
		PrintWriter pw=response.getWriter();
		JSONObject ResultJson=new JSONObject();
		JSONObject jsonObject1=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		
		showordercommoditycl scdc = new showordercommoditycl();
		ArrayList<CommodityBean> ar1 = scdc.getc_idfromw_cu();
		int num=0;
		for(int j=0;j<ar1.size();j++){
			CommodityBean cb1 = ar1.get(j);
		
			
			showshopcarcl sscl = new showshopcarcl();
			 num =sscl.getshopcount1(cb1.getC_id(),cb1.getU_id());
		
			String s = cb1.getC_num();
			if(s!=null){
			System.out.println(s.equals("0")+"蛋疼测试");
		//	String u_adress1 = null;
			if(s.equals("0")){
				if(num<1){
					num=1;
				}
			//}
		//		u_adress1=new String(cb1.getU_adress().getBytes("UTF-8"),"GBK");
				//num =sscl.getshopcount(cb1.getC_id(),cb1.getU_id());
				jsonObject1.put("c_id",cb1.getC_id());
				jsonObject1.put("u_id",cb1.getU_id());
				jsonObject1.put("title",cb1.getTitle());
				jsonObject1.put("num",num);	
				jsonObject1.put("c_num",cb1.getC_num());
				jsonObject1.put("u_adress",cb1.getU_adress());
				jsonArray.add(jsonObject1);
			
			}
			}
		}
		pw.println(jsonArray);
		pw.flush();
		pw.close(); 	
	}
	

}
