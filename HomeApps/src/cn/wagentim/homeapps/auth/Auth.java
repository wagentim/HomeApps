package cn.wagentim.homeapps.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class Auth
{
    private static final long MAX_SESSION_AVAILABLE_TIME = 1000 * 60 * 60 * 24;

    public static final boolean isSessionAvailable(final HttpServletRequest request)
    {
        HttpSession session = request.getSession(false);

        if( null == session )
        {
            return false;
        }

        if( isTimeOut(session.getCreationTime()) )
        {
            session.invalidate();
            return false;
        }

        return true;
    }

    private static boolean isTimeOut(long creationTime)
    {
        return ( (System.currentTimeMillis() - creationTime) > MAX_SESSION_AVAILABLE_TIME);
    }
}
