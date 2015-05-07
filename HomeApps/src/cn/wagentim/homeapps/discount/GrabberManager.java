package cn.wagentim.homeapps.discount;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import cn.wagentim.homeapps.discount.handlers.BabyMarketHandler;
import cn.wagentim.homeapps.entities.managers.CachedDataManager;

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
	private Logger logger = Logger.getLogger(GrabberManager.class.getSimpleName());
	private static final int STATUS_IDLE = 0x00;
	private static final int STATUS_PROCESSING = 0x01;
	private int status = STATUS_IDLE;
	
	private GrabberManager(){}
	
	public static GrabberManager INSTANCE()
	{
		return manager;
	}
	
	public synchronized int start()
	{
		if(STATUS_PROCESSING == status)
		{
			logger.info("Grabber is already started. You can only stop or check its logging messages");
			return -1;
		}
		
		status = STATUS_PROCESSING;
		logger.info("Start Grabber...");
		
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
	
	public void addGrabberListener(IGrabberListener listener)
	{
		if( null != listener )
		{
			if( !findListener(listener) )
			{
				listeners.add(listener);
				updateListener();
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
				updateListener();
			}
		}
	}
}
