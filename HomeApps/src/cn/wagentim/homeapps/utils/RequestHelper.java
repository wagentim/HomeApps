package cn.wagentim.homeapps.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

public final class RequestHelper
{
    private static Logger logger = Logger.getLogger(RequestHelper.class.getSimpleName());

	public static final int getEntityType(final HttpServletRequest request)
	{
		String entityType = request.getParameter(Constants.ENTITY);
		logger.log(Level.INFO, "RequestHelper#getEntityType: entity type=" + entityType);

		if( Validator.isNullOrEmpty(entityType) )
		{
		    return Constants.ERROR_INT;
		}

		return Integer.valueOf(entityType).intValue();
	}

	public static final int getOperation(final HttpServletRequest request)
	{
		String opt = request.getParameter(Constants.OPERATION);
		logger.log(Level.INFO, "RequestHelper#getOperation: operation=" + opt);
		if( Validator.isNullOrEmpty(opt) )
        {
            return Constants.ERROR_INT;
        }
		return Integer.valueOf(opt).intValue();
	}

	public static final Long getID(final HttpServletRequest request)
	{
		String userID = request.getParameter(Constants.ID);
		if(Validator.isNullOrEmpty(userID))
		{
			return null;
		}
		return Long.valueOf(userID);
	}
}
