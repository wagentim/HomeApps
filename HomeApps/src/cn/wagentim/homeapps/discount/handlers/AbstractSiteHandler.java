package cn.wagentim.homeapps.discount.handlers;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import cn.wagentim.homeapps.discount.IGrabberListener;
import cn.wagentim.homeapps.discount.webs.IWebsite;

public abstract class AbstractSiteHandler implements ISiteHandler
{
	protected IWebsite site = null;
	protected ConcurrentLinkedQueue<IGrabberListener> listeners = null;
	protected int grabType = ISiteHandler.GRAB_DISCOUNT;
	
	public void setSite(IWebsite site)
	{
		this.site = site;
	}
	
	public void setListeners(ConcurrentLinkedQueue<IGrabberListener> listeners)
	{
		this.listeners = listeners;
	}
	
	private boolean findListener(IGrabberListener listener)
	{
		Iterator<IGrabberListener> it = listeners.iterator();
		
		while(it.hasNext())
		{
			if( listener == it.next() )
			{
				return true; 
			}
		}
		
		return false;
	}
	
	public void addGrabberListener(IGrabberListener listener)
	{
		if( null != listener )
		{
			if( !findListener(listener) )
			{
				listeners.add(listener);
			}
		}
	}
	
	public void removeGrabberListener(IGrabberListener listener)
	{
		if( null != listener )
		{
			if( findListener(listener) )
			{
				listeners.remove(listeners);
			}
		}
	}
	
	protected void sendMessage(String message)
	{
		Iterator<IGrabberListener> it = listeners.iterator();
		
		while(it.hasNext())
		{
			((IGrabberListener)it.next()).currentMessage(message);
		}
	}
	
	public void setGrabType(int type)
	{
		this.grabType = type;
	}
	
}
