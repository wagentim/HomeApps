package cn.wagentim.homeapps.entities.managers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class CachedDataManager
{
    private List<String> currentAuths = new CopyOnWriteArrayList<String>();
    private Logger logger = Logger.getLogger(CachedDataManager.class.getSimpleName());

    public void addNewAuth(final String auth)
    {
        if( !currentAuths.contains(auth) )
        {
            currentAuths.add(auth);
        }
    }

	public boolean isAuthAvailable(String md5)
	{
		boolean isContained = currentAuths.contains(md5);
		logger.log(Level.INFO, "auth data is cached: " + isContained);
		return isContained;
	}
}
