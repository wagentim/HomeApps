
package cn.wagentim.connecthelper.connector;

import java.io.IOException;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import cn.wagentim.homeapps.discount.webs.IWebsite;
import cn.wagentim.homeapps.utils.ConnectorHelper;
import cn.wagentim.homeapps.utils.StringConstants;
import cn.wagentim.homeapps.utils.Validator;


/**
 * Post User Name and Password to the web site for login
 *
 * @author bihu8398
 *
 */
public class PostAuthentication extends AbstractConnector
{
    private final IWebsite site;
    private String pageContent = StringConstants.EMPTY_STRING;

    public PostAuthentication(final IWebsite site)
    {
        this.site = site;
    }

	@Override
	public void run()
	{
        CloseableHttpClient httpclient = ConnectorHelper.getCloseableHttpClientWithCredentialsProvider(site);
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(getCookieStore());
        try
        {
            CloseableHttpResponse response = null;
            try
            {
                HttpPost post = new HttpPost(site.getLoginURI());
                UrlEncodedFormEntity entity = ConnectorHelper.getLoginPostEntity(site);
                entity.setContentType("application/x-www-form-urlencoded");
                post.setEntity(entity);
                response = httpclient.execute(post, context);
                
                if( !Validator.isHttpResponseOk(response) )
                {
                	System.out.println(response.getStatusLine().getReasonPhrase());
                	pageContent = StringConstants.EMPTY_STRING;
                	return;
                }
                
                pageContent = ConnectorHelper.getPageContent(response);
                EntityUtils.consume(response.getEntity());
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    response.close();
                }
                catch ( IOException e )
                {
                    e.printStackTrace();
                }
            }
        }
        finally
        {
            try
            {
                httpclient.close();
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
	}
	
	public String getPageContent()
	{
		return pageContent;
	}
}