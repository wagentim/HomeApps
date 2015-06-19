package cn.wagentim.homeapps.entities.managers;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class CachedDataManager
{
    private Map<String, Long> currentUsers = new ConcurrentHashMap<String, Long>(20);
    private Logger logger = Logger.getLogger(CachedDataManager.class.getSimpleName());

    public void addNewAuth(final Long userID, final String auth)
    {
        if( !currentUsers.keySet().contains(auth) )
        {
        	currentUsers.put(auth, userID);
        }
    }
    
    public Long getUserID(final String md5)
    {
    	if( null != md5 && md5.length() > 0 )
    	{
    		return currentUsers.get(md5);
    	}
    	
    	return -1L;
    }

	public boolean isAuthAvailable(String md5)
	{
		boolean isContained = currentUsers.keySet().contains(md5);
		logger.log(Level.INFO, "auth data is cached: " + isContained);
		return isContained;
	}
}
