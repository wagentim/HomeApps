package cn.wagentim.homeapps.discount;

import javax.servlet.http.HttpServletResponse;

public class GrabberListener implements IGrabberListener
{
	private final HttpServletResponse response;
	
	public GrabberListener(HttpServletResponse response)
	{
		this.response = response;
	}
	
	@Override
	public void currentMessage(String message)
	{
		// do send block message
	}

}
