
package cn.wagentim.connecthelper.connector;

import java.io.IOException;
import java.net.URI;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import cn.wagentim.homeapps.utils.ConnectorHelper;
import cn.wagentim.homeapps.utils.StringConstants;
import cn.wagentim.homeapps.utils.Validator;

public class GetPageContent extends AbstractConnector
{
    private final URI uri;
    private String pageContent = StringConstants.EMPTY_STRING;

    public GetPageContent(URI uri)
    {
        this.uri = uri;
    }

    public void run()
    {
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(getCookieStore());
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try
        {
            HttpGet httpget = new HttpGet(uri);
            CloseableHttpResponse response = null;
            try
            {
                response = httpclient.execute(httpget, context);
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
