package cn.wagentim.homeapps.utils;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;

public final class JSONUtils
{
	private static final Gson json = new Gson();

	public static final String toJsonString(final Object object)
	{
		if( null == object )
		{
			return StringConstants.EMPTY_STRING;
		}

		return json.toJson(object);
	}

	public static final JSONArray getJsonArray(final String json)
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

	public static final JSONObject getJsonObject(final String json)
    {
        if( Validator.isNullOrEmpty(json) )
        {
            return null;
        }

        try
        {
            JSONObject object = new JSONObject(json);
            return object;

        }
        catch ( JSONException e )
        {
            e.printStackTrace();
        }

        return null;
    }
}
