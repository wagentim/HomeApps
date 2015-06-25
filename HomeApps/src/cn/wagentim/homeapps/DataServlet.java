package cn.wagentim.homeapps;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.wagentim.homeapps.entities.IEntity;
import cn.wagentim.homeapps.entities.OrderEntity;
import cn.wagentim.homeapps.entities.OrderItemEntity;
import cn.wagentim.homeapps.entities.managers.DataManager;
import cn.wagentim.homeapps.entities.managers.EntityFactory;
import cn.wagentim.homeapps.entities.managers.EntityHelper;
import cn.wagentim.homeapps.utils.Constants;
import cn.wagentim.homeapps.utils.JSONUtils;
import cn.wagentim.homeapps.utils.RequestHelper;

public class DataServlet extends HttpServlet
{

	private static final long serialVersionUID = 688809014531432187L;
	private Logger logger = Logger.getLogger(DataServlet.class.getSimpleName());

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		int entityType = RequestHelper.getEntityType(request);
		int opt = RequestHelper.getOperation(request);
		Long id = RequestHelper.getID(request);
		PrintWriter out = response.getWriter();
		logger.log(Level.INFO, "DataServlet#doPost Entity Type: " + entityType + "; operation: " + opt + "; ID: " + id);

		switch(opt)
		{
			case Constants.OPT_ENTITY_SAVE_OR_UPDATE:
				IEntity entity = EntityFactory.createEntity(entityType, request);
				DataManager.INSTANE.DB_DATA().addOrModifyData(entity, id);
				switch(entityType)
				{
				    case Constants.ENTITY_CUSTOMER:
				        response.sendRedirect(Constants.PAGE_EDIT_USER);
				        break;
				    case Constants.ENTITY_PRODUCT:
				        response.sendRedirect(Constants.PAGE_EDIT_PRODUCT);
				        break;
				    case Constants.ENTITY_ORDER:
				        response.sendRedirect(Constants.PAGE_ORDER);
				        break;
				}
				break;

			case Constants.OPT_DELETE:
				
				if( 0 != id )
				{
					DataManager.INSTANE.DB_DATA().deleteEntity(EntityHelper.getEntityClazz(entityType), id);
					out.print("true");
					out.flush();
				}
				else
				{
					logger.log(Level.INFO, "DataServlet#doPost the id is null");
				}
				break;
				
			case Constants.OPT_ENTITY_DELETE_ALL:
				boolean success = DataManager.INSTANE.DB_DATA().deleteAllOrders(id);
				out.print(success);
				out.flush();
				break;
				
			case Constants.OPT_ENTITY_GET_ALL:
				
				List<OrderEntity> orders = DataManager.INSTANE.DB_DATA().getAllOrders(id);
				
				for(int i = 0; i < orders.size(); i++)
				{
					OrderEntity order = orders.get(i);
					List<Long> items = order.getItems();
					
					if( null != items && !items.isEmpty() )
					{
						for(int j=0; j < items.size(); j++)
						{
							OrderItemEntity item = DataManager.INSTANE.DB_DATA().getOrderItem(items.get(j)); 
							
							order.addOrder(item);
						}
					}
				}
				
				String content = JSONUtils.toJsonString(orders);
				response.setContentType("application/json");
				System.out.println(content);
				out.print(content);
				out.flush();
				break;
		}
	}
}
