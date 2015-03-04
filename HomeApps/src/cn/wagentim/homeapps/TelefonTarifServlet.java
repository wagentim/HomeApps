package cn.wagentim.homeapps;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wagentim.homeapps.discount.webs.IWebsite;
import cn.wagentim.homeapps.discount.webs.Webs;

public class TelefonTarifServlet extends HttpServlet
{
	private static final long serialVersionUID = 3148873698404143611L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		// TODO currently do not use session for auth
		
		PrintWriter out = response.getWriter();
		out.print("现在开始抓取最新信息。。。");
		
		for(Class instance : Webs.WEB_TEL)
		{
			try
			{
				IWebsite site = (IWebsite) instance.newInstance();
				out.print("Executing " + site.getName() + ", please wait...");
				out.flush();
				String result = site.getHandler().exec();
				
			} catch (InstantiationException | IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}
}
