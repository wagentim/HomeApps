package cn.wagentim.homeapps;

import java.io.IOException;

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
			response.getWriter().print("ok");
		}
		else
		{
			response.sendRedirect("/pages/login.jsp");
		}
	}
}
