package cn.wagentim.homeapps.utils;

import java.util.List;

import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;

import cn.wagentim.homeapps.entities.CustomerEntity;
import cn.wagentim.homeapps.entities.managers.DataManager;



public final class Validator
{
	public static boolean isNullOrEmpty(final String s)
    {
        return s == null || s.length() == 0;
    }

	public static boolean isNullOrEmpty(final String[] s)
    {
	    boolean result = false;
	    for(int i = 0; i < s.length; i++)
	    {
	        result &= isNullOrEmpty(s[i]);
	    }
        return result;
    }

	public static boolean isNullOrEmpty(final Object[] array)
	{
	    return (null == array) || (array.length <= 0);
	}

	public static boolean isNull(final Object object)
	{
		return null == object;
	}

	public static final boolean isUserLoginDataOk(final String[] loginInfo)
	{
		if( null == loginInfo || loginInfo.length != 2 )
		{
			return false;
		}

		String usr = loginInfo[0];
		String pwd = loginInfo[1];

		if( null == usr || null == pwd || usr.isEmpty() || pwd.isEmpty() )
		{
			return false;
		}

		return true;
	}
	
	public static boolean isHttpResponseOk(CloseableHttpResponse response)
	{
		StatusLine statusLine = response.getStatusLine();

		if (statusLine.getStatusCode() >= 300)
		{
			return false;
		}

		return true;
	}
	
	public static List<CustomerEntity> checkUser(final String username, final String password)
	{
		return DataManager.INSTANE.DB_DATA().checkUser(username, password);
	}
	
}
