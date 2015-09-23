package cn.wagentim.homeapps.servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wagentim.homeapps.discount.GrabberListener;
import cn.wagentim.homeapps.discount.GrabberManager;
import cn.wagentim.homeapps.discount.IGrabberListener;
import cn.wagentim.homeapps.utils.AppUtils;
import cn.wagentim.homeapps.utils.Constants;
import cn.wagentim.homeapps.utils.RequestHelper;

public class GrabbedProductServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2900900137333082962L;
	private Logger logger = Logger.getLogger(GrabbedProductServlet.class.getSimpleName());

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		int opt = RequestHelper.getOperation(request);
		logger.log(Level.INFO, "GrabbedProductServlet#doPost do the operation" + AppUtils.getOperationName(opt));
		GrabberManager gm = GrabberManager.INSTANCE();

		switch(opt)
		{
			case Constants.OPT_START_GRABBING:
				
				IGrabberListener listener;
				
				if( isNew() )
				{
					listener = new GrabberListener(response);
				}
				else
				{
					listener = getGrabberListener();
				}
				
				gm.addGrabberListener(listener);
				gm.start();
				break;
				
			case Constants.OPT_STOP_GRABBING:
				gm.stop();
		}
	}

	private IGrabberListener getGrabberListener()
	{
		// TODO Auto-generated method stub
		return null;
	}

	private boolean isNew()
	{
		// TODO Auto-generated method stub
		return false;
	}
}
