package cn.wagentim.homeapps.eventbus;

import java.lang.reflect.Method;

import de.esolutions.fw.util.commons.job.DispatcherBase;

/**
 * Wraps a single-argument subscriber method on a specific object and an
 * exception handler. In instance will be created for each applicable method in
 * the registered listener. {@link #handleEvent(Object)} invokes the method
 * asynchronously. For synchronous invocation see {@link AsyncEventSubscriber}.
 * 
 * @author yuku8413
 */
class AsyncEventSubscriber extends EventSubscriber
{
    private final DispatcherBase dispatcher;

    AsyncEventSubscriber(Object target, Method method, DispatcherBase dispatcher, SubscriberExceptionHandler subscriberExceptionHandler)
    {
        super(target, method, subscriberExceptionHandler);
        this.dispatcher = dispatcher;
    }

    public void handleEvent(final Object event)
    {
        dispatcher.execute(new Runnable()
        {
            public void run()
            {
                AsyncEventSubscriber.super.handleEvent(event);
            }
        });
    }
}
