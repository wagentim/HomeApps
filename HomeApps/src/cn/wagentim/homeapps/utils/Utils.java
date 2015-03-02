package cn.wagentim.homeapps.utils;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.gson.Gson;

public final class Utils
{
	private static final Gson json = new Gson();

	public static final String toJson(final Object object)
	{
		if( null == object )
		{
			return StringConstants.EMPTY_STRING;
		}

		return json.toJson(object);
	}

	public static final JSONArray fromJson(final String json)
	{
	    if( Validator.isNullOrEmpty(json) )
	    {
	        return null;
	    }

	    try
        {
            JSONArray array = new JSONArray(json);
            return array;

        }
        catch ( JSONException e )
        {
            e.printStackTrace();
        }

	    return null;
	}
}
