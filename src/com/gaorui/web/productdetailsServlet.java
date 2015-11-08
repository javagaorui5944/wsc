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
import com.gaorui.dao.showcommoditycl;
import com.gaorui.model.CommodityBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class messageshowServlet
 */
public class productdetailsServlet extends HttpServlet {



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 this.doPost(request, response);
	}
	/**
	 * 实现发送给前台商品id的对应的所有商品数据的servlet,属于controller层，发送了json数据给前台，ajax数据交互
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin","*");//设置响应头，确保手机端能访问到servlet
		response.setContentType("text/html;charset=utf-8");//转换，解决乱码。
		PrintWriter pw=response.getWriter();
		JSONObject ResultJson=new JSONObject();
		/*JSONObject jsonObject1=new JSONObject();
		JSONArray jsonArray=new JSONArray();*/
		String id =(String)request.getParameter("id");
		CommodityBean cb=new CommodityBean();
		productdetailscl pcl = new productdetailscl();
		ArrayList<CommodityBean> al = pcl.getCommodity(id);
	
		for(int i=0;i<al.size();i++)
		{
			CommodityBean cb1 =al.get(i);
			/*request.setAttribute("title",cb1.getTitle());
			request.setAttribute("desc", cb1.getDesc());
			request.setAttribute("img", cb1.getImg());
			request.setAttribute("c_id", cb1.getC_id());
			System.out.println(cb1.getC_id()+"xxxx");*/
			ResultJson.put("title",cb1.getTitle());
    		ResultJson.put("desc",cb1.getDesc());
    		ResultJson.put("img",cb1.getImg());
    		ResultJson.put("c_id",cb1.getC_id());	
		}
	pw.println(ResultJson);   		
		pw.flush();
		pw.close();
	/*request.getRequestDispatcher("/shop/xx.jsp").forward(request, response);*/

	}

}
