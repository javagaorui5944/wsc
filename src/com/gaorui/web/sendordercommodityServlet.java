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

import com.gaorui.dao.sendordercommoditycl;
import com.gaorui.dao.showordercommoditycl;
import com.gaorui.dao.showshopcarcl;
import com.gaorui.model.CommodityBean;

/**
 * Servlet implementation class sendordercommodityServlet
 */
public class sendordercommodityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * 实现后台管理系统发货的servlet,属于controller层，发送了json数据给前台，ajax数据交互
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
		String c_num = (String)request.getParameter("c_num");
		String c_id  = request.getParameter("c_id");
		int c_num1 = 0;
		if(c_num!=null){
	 	c_num1 = Integer.parseInt(c_num);}
    /*	HttpSession session = request.getSession(true);
    	session.removeAttribute("Username");  
        	session.setAttribute("Username", Username);*/
		String u_id  = request.getParameter("u_id");
		sendordercommoditycl sdcl = new sendordercommoditycl();
		boolean b = false;
		b = sdcl.updatec_num(c_num1, c_id, u_id);
		b=true;
		if(b){
			
			showordercommoditycl scdc = new showordercommoditycl();
			ArrayList<CommodityBean> ar1 = scdc.getc_idfromw_cu();
			for(int j=0;j<ar1.size();j++){
				CommodityBean cb1 = ar1.get(j);
				int num=0;
				showshopcarcl sscl = new showshopcarcl();
				num =sscl.getshopcount2(cb1.getC_id(),cb1.getU_id());
				if(num<1){
					num=1;
				}	
				String s = cb1.getC_num();
				if(s!=null){
		if(!s.equals("0")&&!s.equals("-1")){
				
				//jsonObject1.put("c_id",cb1.getC_id());
				jsonObject1.put("u_id",cb1.getU_id());
				jsonObject1.put("title",cb1.getTitle());
				jsonObject1.put("num",num);
				jsonObject1.put("u_adress",cb1.getU_adress());
				/*if(!cb1.getC_num().equals("0"))*/
				jsonObject1.put("c_num",cb1.getC_num());
				System.out.println("物流测试："+cb1.getU_id());
		/*		}*/
				jsonArray.add(jsonObject1);
			}
			
				}
		}
			pw.println(jsonArray);
			pw.flush();
			pw.close(); 
			}
		}

}
