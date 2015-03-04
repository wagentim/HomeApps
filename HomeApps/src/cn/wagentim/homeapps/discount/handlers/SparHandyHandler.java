package cn.wagentim.homeapps.discount.handlers;

import cn.wagentim.homeapps.discount.webs.IWebsite;

public class SparHandyHandler extends AbstractSiteHandler
{

	public SparHandyHandler(final IWebsite site)
	{
		super(site);
	}

	@Override
	public String exec()
	{
		
		return formatResult();
	}

	private String formatResult()
	{
		return null;
	}

}
