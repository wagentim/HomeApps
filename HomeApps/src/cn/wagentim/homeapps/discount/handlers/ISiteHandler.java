package cn.wagentim.homeapps.discount.handlers;

import java.util.concurrent.ConcurrentLinkedQueue;

import cn.wagentim.homeapps.discount.IGrabberListener;
import cn.wagentim.homeapps.discount.webs.IWebsite;

public interface ISiteHandler
{
	public static final int GRAB_ALL = 0x00;
	public static final int GRAB_DISCOUNT = 0x01;
	
	void exec();
	void setSite(IWebsite site);
	void setListeners(ConcurrentLinkedQueue<IGrabberListener> listeners);
	void addGrabberListener(IGrabberListener listener);
	void removeGrabberListener(IGrabberListener listener);
	void setGrabType(int type);
}
