package cn.wagentim.homeapps.discount.webs;

import java.net.URI;

import cn.wagentim.homeapps.discount.handlers.ISiteHandler;
import cn.wagentim.homeapps.discount.handlers.SparHandyHandler;
import cn.wagentim.homeapps.utils.StringConstants;

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
        return StringConstants.EMPTY_STRING;
    }

    @Override
    public String getUserName()
    {
        return StringConstants.EMPTY_STRING;
    }

    @Override
    public String getPassword()
    {
        return StringConstants.EMPTY_STRING;
    }

    @Override
    public String getData()
    {
        return null;
    }

	@Override
	public ISiteHandler getHandler()
	{
		return new SparHandyHandler();
	}

	@Override
	public String getUserNameDefinition()
	{
		return null;
	}

	@Override
	public String getPasswordDefinition()
	{
		return null;
	}

	@Override
	public URI getLoginURI()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
