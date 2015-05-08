package cn.wagentim.homeapps.discount.handlers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import cn.wagentim.connecthelper.connector.GetPageContent;


public class BabyMarketHandler extends AbstractSiteHandler
{

	@Override
	public void exec()
	{
		if( ISiteHandler.GRAB_DISCOUNT == grabType )
		{
			doGrabDiscount();
		}
		else if( ISiteHandler.GRAB_ALL == grabType )
		{
			doGrabAll();
		}
	}
	
	private List<String> getLinks()
	{
		List<String> result = new ArrayList();
		try
		{
			GetPageContent gpc = new GetPageContent(new URI(site.getDomain()));
			gpc.run();
			
		} 
		catch (URISyntaxException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	private void doGrabDiscount()
	{
		
		
	}

	private void doGrabAll()
	{
		// TODO Auto-generated method stub
		
	}

}
