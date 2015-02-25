package cn.wagentim.homeapps.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class Auth
{
	public static final int MAX_SESSION_TIME_OUT = 60 * 60 * 24;
	
	public static final boolean hasValidSession(final HttpServletRequest request)
	{
		HttpSession session = request.getSession(false);
		
		if( null == session)
		{
			return false;
		}
		else if(  isTimeout(session.getCreationTime()) )
		{
			session.invalidate();
			
			return false;
		}
		
		return true;
	}

	private static boolean isTimeout(long creationTime)
	{
		return (System.currentTimeMillis() - creationTime) > MAX_SESSION_TIME_OUT;
	}
	
	public static String getMD5Encode(String userName, String password)
    {
	    StringBuffer sb = new StringBuffer();
	    sb.append(userName).append(";").append(password).append(";").append(System.currentTimeMillis());
	    return getMD5Encode(sb.toString());
    }

	public static String getMD5Encode(String input)
	{
	    try
        {
            byte[] bytesContent = input.getBytes("utf-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] theDigest = md.digest(bytesContent);

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < theDigest.length; i++)
            {
             sb.append(Integer.toString((theDigest[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();

        }
        catch ( UnsupportedEncodingException e )
        {
            e.printStackTrace();
        }
        catch ( NoSuchAlgorithmException e )
        {
            e.printStackTrace();
        }

	    return StringConstants.EMPTY_STRING;
	}
}
