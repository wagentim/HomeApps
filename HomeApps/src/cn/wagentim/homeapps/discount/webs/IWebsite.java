package cn.wagentim.homeapps.discount.webs;

import java.net.URI;

import cn.wagentim.homeapps.discount.handlers.ISiteHandler;

public interface IWebsite
{
    String getName();
    String getDomain();
    String getEntryPoint();
    String getAuth();
    String getUserName();
    String getPassword();
    String getData();
    ISiteHandler getHandler();
	String getUserNameDefinition();
	String getPasswordDefinition();
	URI getLoginURI();
}
