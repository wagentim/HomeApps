package cn.wagentim.homeapps.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import cn.wagentim.homeapps.discount.webs.IWebsite;

public final class ConnectorHelper
{
	public static final CredentialsProvider getCredentialsProvider(final IWebsite site)
	{
		CredentialsProvider provider = new BasicCredentialsProvider();
		provider.setCredentials(
                new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
                new UsernamePasswordCredentials(site.getUserName(), site.getPassword()));
		
		return provider;
	}
	
	public static final CloseableHttpClient getCloseableHttpClientWithCredentialsProvider(final IWebsite site)
	{
		return HttpClients.custom().setDefaultCredentialsProvider(getCredentialsProvider(site)).setRedirectStrategy(new LaxRedirectStrategy()).build();
	}

	public static String getPageContent(final CloseableHttpResponse response) throws IllegalStateException, IOException
	{
		HttpEntity entity = response.getEntity();
		
		return EntityUtils.toString(entity);
	}
	
	public static final UrlEncodedFormEntity getLoginPostEntity(final IWebsite site)
	{
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        nameValuePairs.add(new BasicNameValuePair(site.getUserNameDefinition(), site.getUserName()));
        nameValuePairs.add(new BasicNameValuePair(site.getPasswordDefinition(), site.getPassword()));
        return new UrlEncodedFormEntity(nameValuePairs, Charset.forName("utf-8"));
	}
}
