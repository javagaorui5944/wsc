package com.gaorui.web;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import javax.servlet.http.HttpSession;

import com.gaorui.dao.productdetailscl;
import com.gaorui.dao.registeredCl;
import com.gaorui.dao.shopcarcl;
import com.gaorui.dao.showcommoditycl;
import com.gaorui.model.CommodityBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class messageshowServlet
 */
public class shopcarclServlet extends HttpServlet {



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 this.doPost(request, response);
	}

	/**
	 * 实现用户将商品放入购物车的servlet,属于controller层，发送了json数据给前台，ajax数据交互
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");//转换，解决乱码。
		response.setHeader("Access-Control-Allow-Origin","*");//设置响应头，确保手机端能访问到servlet
		PrintWriter pw=response.getWriter();	
        JSONObject ResultJson=new JSONObject();
        int status=0;
        String info=null;
		String c_id =(String)request.getParameter("id");

		System.out.println("idididididid"+c_id);
		/*HttpSession session = request.getSession();
		String Username=(String) session.getAttribute("Username");*/
		String Username = (String)request.getParameter("Username");  
		System.out.println("购物车购买2321测试：Username"+Username);
		shopcarcl scl = new shopcarcl();
		String title = scl.get_titleByc_id(c_id);
		String u_id = scl.getu_idByu_name(Username); 
		boolean b = scl.insertshopCommodity(u_id, c_id,title);
		if(b){
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
        	status=100;
    		info="failed";
    		ResultJson.put("status",status);
    		ResultJson.put("info",info);
        	pw.println(ResultJson);
    		pw.flush();
    		pw.close();
        	}	
		//request.getRequestDispatcher("/shop/xx.jsp").forward(request, response);
		}
		/*productdetailscl pcl = new productdetailscl();
		ArrayList<CommodityBean> al = pcl.getCommodity(id);
	
		for(int i=0;i<al.size();i++)
		{
			CommodityBean cb1 =al.get(i);
			request.setAttribute("title",cb1.getTitle());
			request.setAttribute("desc", cb1.getDesc());
			request.setAttribute("img", cb1.getImg());
			request.setAttribute("c_id", cb1.getC_id());
			System.out.println(cb1.getC_id()+"xxxx");
	
			
		}*/
	

	}

