package cn.wagentim.homeapps.discount;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import cn.wagentim.homeapps.discount.handlers.BabyMarketHandler;
import cn.wagentim.homeapps.discount.handlers.ISiteHandler;

/**
 * The Grabber Manager controller the whole grabbing life cycle
 * @author wagentim
 *
 */
public final class GrabberManager
{
	private static final Class[] handlers = 
	{
		BabyMarketHandler.class
	};
	
	private static final ConcurrentLinkedQueue<IGrabberListener> listeners = new ConcurrentLinkedQueue<IGrabberListener>();
	private static final GrabberManager manager = new GrabberManager();
	private static final int STATUS_IDLE = 0x00;
	private static final int STATUS_PROCESSING = 0x01;
	private int status = STATUS_IDLE;
	private ISiteHandler currHandler = null;
	private GrabberManager(){}
	private volatile boolean shouldStop = false;
	
	public static GrabberManager INSTANCE()
	{
		return manager;
	}
	
	public synchronized int start()
	{
		if(STATUS_PROCESSING == status)
		{
			sendMessage("Grabber is already started. You can only stop or check its logging messages");
			return -1;
		}
		
		status = STATUS_PROCESSING;

		try
		{
			for (int i = 0; i < handlers.length; i++)
			{
				if( !shouldStop )
				{
					ISiteHandler handler = (ISiteHandler) handlers[i].newInstance();
					handler.setListeners(listeners);
					currHandler = handler;
					handler.exec();
				}
			}
		} 
		catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		
		return 0;
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
	
	public synchronized void addGrabberListener(IGrabberListener listener)
	{
		if( null != listener )
		{
			if( !findListener(listener) )
			{
				listeners.add(listener);
				if( null != currHandler )
				{
					currHandler.addGrabberListener(listener);
				}
			}
		}
	}
	
	public synchronized void removeGrabberListener(IGrabberListener listener)
	{
		if( null != listener )
		{
			if( findListener(listener) )
			{
				listeners.remove(listeners);
				if( null != currHandler )
				{
					currHandler.removeGrabberListener(listener);
				}
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

	public synchronized void stop()
	{
		shouldStop = true;
		status = STATUS_IDLE;
	}
}
