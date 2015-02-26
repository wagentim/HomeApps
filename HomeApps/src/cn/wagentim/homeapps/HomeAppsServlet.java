package cn.wagentim.homeapps;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import cn.wagentim.homeapps.auth.Auth;

public class HomeAppsServlet extends HttpServlet
{
	private static final long serialVersionUID = -7209820805065847987L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{
		if( Auth.isSessionAvailable(request) )
		{
			try
			{
				request.getRequestDispatcher("/pages/buymanager/usereditor.jsp").forward(request, response);
			} catch (ServletException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			try
			{
				request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
			} catch (ServletException e)
			{
				e.printStackTrace();
			}
		}
	}
}
