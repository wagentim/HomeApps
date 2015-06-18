package cn.wagentim.homeapps.utils;

import cn.wagentim.homeapps.entities.IDEntity;

public final class IDGenerator
{
	public static final String getNextID(IDEntity entity)
	{
		if( null == entity )
		{
			return StringConstants.EMPTY_STRING;
		}
		
		long nextID;
		long lastNumber = Long.parseLong(entity.getLastNumber());
		
		if( -1 == lastNumber )
		{
			nextID = entity.getMode();
		}
		else
		{
			nextID = lastNumber + 1;
		}
		
		long max = getMaximumIDNumber(entity);
		
		if( nextID > max )
		{
			return StringConstants.EMPTY_STRING;
		}
		
		String currValue = String.valueOf(nextID);
		
		entity.setLastNumber(currValue);
		return currValue;
	}
	
	private static final long getMaximumIDNumber(IDEntity entity)
	{
		String mode = String.valueOf(entity.getMode());
		mode.replaceAll("0", "9");
		return Long.valueOf(mode);
	}
}
