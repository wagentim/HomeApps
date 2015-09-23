package cn.wagentim.homeapps.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import cn.wagentim.homeapps.auth.Auth;
import cn.wagentim.homeapps.utils.Constants;

public class HomeAppsServlet extends HttpServlet
{
	private static final long serialVersionUID = -7209820805065847987L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		doLoginCheck(request, response);
	}
	
	private void doLoginCheck(HttpServletRequest request, HttpServletResponse response)
	{
		if( Auth.isSessionAvailable(request) )
		{
				try
				{
					response.sendRedirect(Constants.PAGE_HOME);
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
		}
		else
		{
			try
			{
				response.sendRedirect(Constants.PAGE_LOGIN);
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doLoginCheck(request, response);
	}
}
