package cn.wagentim.homeapps.discount.webs;

import cn.wagentim.homeapps.discount.handlers.ISiteHandler;
import cn.wagentim.homeapps.discount.handlers.SparHandyHandler;

public class SparHandy implements IWebsite
{
    @Override
    public String getName()
    {
        return "SparHandy";
    }

    @Override
    public String getDomain()
    {
        // TODO Auto-generated method stub
        return "www.sparhandy.de";
    }

    @Override
    public String getEntryPoint()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getAuth()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUserName()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getPassword()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getData()
    {
        return null;
    }

	@Override
	public ISiteHandler getHandler()
	{
		return new SparHandyHandler(this);
	}

}
