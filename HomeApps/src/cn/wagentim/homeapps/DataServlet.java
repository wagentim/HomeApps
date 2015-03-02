package cn.wagentim.homeapps;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wagentim.homeapps.entities.managers.DataManager;
import cn.wagentim.homeapps.entities.managers.EntityFactory;
import cn.wagentim.homeapps.utils.Constants;
import cn.wagentim.homeapps.utils.RequestHelper;

public class DataServlet extends HttpServlet
{

	private static final long serialVersionUID = 688809014531432187L;
	private Logger logger = Logger.getLogger(DataServlet.class.getSimpleName());

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		int entityType = RequestHelper.getEntityType(request);
		int opt = RequestHelper.getOperation(request);
		Long id = RequestHelper.getID(request);
		logger.log(Level.INFO, "DataServlet#doPost Entity Type: " + entityType + "; operation: " + opt + "; Entity ID: " + id);

		switch(opt)
		{
			case Constants.OPT_ENTITY_SAVE_OR_UPDATE:
				Object entity = EntityFactory.createEntity(entityType, request);
				DataManager.INSTANE.DB_DATA().addOrModifyData(entity, id, entityType);
				request.getRequestDispatcher(Constants.PAGE_EDIT_USER).forward(request, response);
				break;

		}
	}
}
