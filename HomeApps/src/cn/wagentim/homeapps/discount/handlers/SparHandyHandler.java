package cn.wagentim.homeapps.discount.handlers;

import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.utils.URIBuilder;

import cn.wagentim.connecthelper.connector.GetPageContent;
import cn.wagentim.homeapps.discount.webs.IWebsite;
import cn.wagentim.homeapps.entities.StringEntity;
import cn.wagentim.homeapps.entities.managers.DataManager;
import cn.wagentim.homeapps.utils.StringConstants;
import cn.wagentim.homeapps.utils.Validator;

public class SparHandyHandler extends AbstractSiteHandler
{
    private Long id = 0L;

	@Override
	public void exec()
	{
		grabDiscountPicOnMainPage();
	}

	public void grabDiscountPicOnMainPage()
	{
	    URIBuilder builder = new URIBuilder();
	    builder.setScheme("http");
	    builder.setHost(site.getDomain());

	    try
        {
            GetPageContent getPage = new GetPageContent(builder.build());
            getPage.run();
            String content = getPage.getPageContent();

            if( !Validator.isNullOrEmpty(content) )
            {
                StringEntity cont = new StringEntity();
                cont.setValue(content);
                DataManager.INSTANE.DB_DATA().addOrModifyData(cont, 0L);
            }
        }
        catch ( URISyntaxException e )
        {
            e.printStackTrace();
        }
	}

	public void parserDiscountPicOnMainPage()
    {
	    String page = StringConstants.EMPTY_STRING;

        List<StringEntity> contents = (List<StringEntity>)DataManager.INSTANE.DB_DATA().getAllEntity(StringEntity.class);
        if( null != contents && contents.size() > 0 )
        {
            for(StringEntity se : contents)
            {
                page = se.getValue();
            }

            if( !Validator.isNullOrEmpty(page) )
            {
                System.out.println(page);
            }
        }
    }

	public static void main(String[] args)
	{
	    SparHandyHandler handler = new SparHandyHandler();

	    handler.grabDiscountPicOnMainPage();
	    handler.parserDiscountPicOnMainPage();
	}

    private String formatResult()
	{
		return null;
	}

	@Override
	public void setSite(IWebsite site) 
	{
		this.site = site;
	}

}
