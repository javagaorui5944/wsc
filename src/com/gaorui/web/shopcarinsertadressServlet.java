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

import com.gaorui.dao.Managercl;
import com.gaorui.dao.shopcarcl;
import com.gaorui.dao.shopcarinsertadresscl;
import com.gaorui.dao.showcommoditycl;
import com.gaorui.model.CommodityBean;

/**
 * Servlet implementation class shopcarinsertadressServlet
 */
public class shopcarinsertadressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 this.doPost(request, response);
		}

	/**
	 * 实现根据用户id增加商品地址的servlet,属于controller层，发送了json数据给前台，ajax数据交互
	 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setHeader("Access-Control-Allow-Origin","*");//设置响应头，确保手机端能访问到servlet
			response.setContentType("text/html;charset=utf-8");//转换，解决乱码。
			PrintWriter pw=response.getWriter();
			JSONObject jsonObject1=new JSONObject();
			JSONArray jsonArray=new JSONArray();
			String u_adress = (String)request.getParameter("u_adress");
			String Username = (String)request.getParameter("Username");
			shopcarcl scl = new shopcarcl();
			String u_id = scl.getu_idByu_name(Username); 
			System.out.println("增加商品地址后台测试："+u_adress+Username);
			shopcarinsertadresscl sddl = new shopcarinsertadresscl(); 
			boolean a = false ;
			
			a=sddl.insertshopcaradress(u_adress, u_id);
			int status = 0;
			if(a){
				
					status=200; 
		       		jsonObject1.put("status",status);
		           	pw.println(jsonObject1);
		       		pw.flush();
		       		pw.close();
		}
			else{
				
				 	status=100;
		       		String	info="failed";
		       		jsonObject1.put("status",status);
		           	pw.println(jsonObject1);
		       		pw.flush();
		       		pw.close();
		}
			
	}

}
