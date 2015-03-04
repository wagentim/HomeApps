package cn.wagentim.homeapps.discount.handlers;

import cn.wagentim.homeapps.discount.webs.IWebsite;

public abstract class AbstractSiteHandler implements ISiteHandler
{
	protected final IWebsite site;
	
	protected AbstractSiteHandler(final IWebsite site)
	{
		this.site = site;
	}
}
