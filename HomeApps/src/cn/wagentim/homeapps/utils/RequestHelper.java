package cn.wagentim.homeapps.utils;

import javax.servlet.http.HttpServletRequest;

public final class RequestHelper
{
	public static final int getEntityType(final HttpServletRequest request)
	{
		String entityType = request.getParameter(Constants.ENTITY);
		
		return Integer.valueOf(entityType).intValue();
	}
	
	public static final int getOperation(final HttpServletRequest request)
	{
		String opt = request.getParameter(Constants.OPERATION);
		
		return Integer.valueOf(opt).intValue();
	}
	
	public static final Long getID(final HttpServletRequest request)
	{
		String opt = request.getParameter(Constants.ID);
		
		return Long.valueOf(opt);
	}
}
