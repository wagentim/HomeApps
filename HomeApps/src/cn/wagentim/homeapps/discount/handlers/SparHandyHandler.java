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
		grabDiscountPicOnMainPage();
		return formatResult();
	}

	private void grabDiscountPicOnMainPage()
	{
		// TODO Auto-generated method stub
		
	}

	private String formatResult()
	{
		return null;
	}

}
