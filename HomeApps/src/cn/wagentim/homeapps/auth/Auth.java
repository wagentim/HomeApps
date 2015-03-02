package cn.wagentim.homeapps.auth;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.wagentim.homeapps.entities.managers.DataManager;
import cn.wagentim.homeapps.utils.Constants;
import cn.wagentim.homeapps.utils.StringConstants;
import cn.wagentim.homeapps.utils.Validator;

public final class Auth
{
    public static final int MAX_SESSION_TIME_OUT = 60 * 60 * 24;
    private static Logger logger = Logger.getLogger(Auth.class.getSimpleName());

    public static final boolean isSessionAvailable(final HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);

        if( null == session )
        {
        	logger.log(Level.INFO, "Session is null");
            return false;
        }

        if( isTimeOut(session.getCreationTime()) || !isAuthAvailable(session) )
        {
            session.invalidate();
            return false;
        }

        return true;
    }

    private static boolean isAuthAvailable(HttpSession session)
	{
    	String md5 = (String) session.getAttribute(Constants.AUTH);

    	if(Validator.isNullOrEmpty(md5))
    	{
    		logger.log(Level.INFO, "Get invailable auth data");
    		return false;
    	}

    	return DataManager.INSTANE.CACHE_DATA().isAuthAvailable(md5);
	}

	private static boolean isTimeOut(long creationTime)
    {
		long endTime = 0;
        boolean isTimeout = ( ( (endTime = System.currentTimeMillis()) - creationTime) > (MAX_SESSION_TIME_OUT * 1000));
        logger.log(Level.INFO, "Time out: " + isTimeout + " -> " + endTime + " : " + creationTime + " : " + MAX_SESSION_TIME_OUT * 1000);
        return isTimeout;
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
