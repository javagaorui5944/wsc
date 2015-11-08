package com.gaorui.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.gaorui.dao.Managercl;
import com.gaorui.dao.showcommoditycl;
import com.gaorui.model.CommodityBean;

/**
 * Servlet implementation class insertcommodityServlet
 */
public class insertcommodityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 this.doPost(request, response);
		}

	/**
	 * 实现后台管理系统增加商品的servlet,属于controller层，发送了json数据给前台，ajax数据交互
	 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html;charset=utf-8");//转换，解决乱码。
			String title = request.getParameter("title");
			String desc  = request.getParameter("desc");
			String img   = request.getParameter("img");
			String price = request.getParameter("price");
			//String desc1=new String(desc.getBytes("ISO-8859-1"),"UTF-8");
		//	System.out.println("增加商品后台测试："+title+desc+img+price);
			PrintWriter pw=response.getWriter();
			//JSONObject ResultJson=new JSONObject();
			JSONObject jsonObject1=new JSONObject();
			JSONArray jsonArray=new JSONArray();
			boolean a = false ;
			Managercl mcl = new Managercl();
			a = mcl.insertCommodity(img, desc, title, price);
			if(a){
				
			
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
			//System.out.println(desc3+"描述");
				
			//	System.out.println(cb1.getC_id()+"xxxx");
			}
			
			pw.println(jsonArray);
			pw.flush();
			pw.close(); 
		}
			else{
				int status=0;
        	String	info="failed";
        	jsonObject1.put("status",status);
        	jsonObject1.put("info",info);
            	pw.println(jsonObject1);
        		pw.flush();
        		pw.close();
			}
	}
}

