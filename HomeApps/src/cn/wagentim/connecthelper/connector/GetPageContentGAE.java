
package cn.wagentim.connecthelper.connector;

import java.io.IOException;
import java.net.URL;

import cn.wagentim.homeapps.utils.StringConstants;

import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

public class GetPageContentGAE extends AbstractConnector
{
    private final String url;
    private String pageContent = StringConstants.EMPTY_STRING;
    private URLFetchService service;

    public GetPageContentGAE(String url)
    {
        this.url = url;
        service = URLFetchServiceFactory.getURLFetchService();
    }

    public void run()
    {
    	try 
    	{
    		HTTPResponse response = service.fetch(new URL(url));
    		if( response.getResponseCode() < 300 )
    		{
    			byte[] content = response.getContent();
    			
    			pageContent = new String(content, "utf-8");
    		}
    		

    	} catch (IOException e) 
    	{
    		e.printStackTrace();
    	}
    	
    }

    public String getPageContent()
    {
        return pageContent;
    }

}
