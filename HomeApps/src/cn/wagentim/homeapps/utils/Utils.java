package cn.wagentim.homeapps.utils;

import com.google.gson.Gson;

public final class Utils
{
	private static final Gson json = new Gson();

	public static final String toJson(Object object)
	{
		if( null == object )
		{
			return StringConstants.EMPTY_STRING;
		}

		return json.toJson(object);
	}
}
