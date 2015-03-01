package cn.wagentim.homeapps.entities.managers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class CachedDataManager
{
    private List<String> currentAuths = new CopyOnWriteArrayList<String>();

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
		System.out.println("auth data is cached: " + isContained);
		return isContained;
	}
}
