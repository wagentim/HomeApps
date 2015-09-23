package cn.wagentim.homeapps.eventbus;

import de.esolutions.fw.util.commons.job.DispatcherBase;

/**
 * Interface which is implemented by the {@link EventBus}. Interface is
 * extracted to allow creation of a logging proxy or whatever else.
 * Documentation is contained in {@link EventBus}.
 * 
 * @author yuku8413
 */
public interface IEventBus
{

    /**
     * Registers all subscriber methods on {@code object} to receive events.
     * Subscriber methods are selected and classified using this EventBus's {@link SubscriberFindingStrategy}; the default strategy is the {@link AnnotatedSubscriberFinder}.
     * 
     * @param object
     *        object whose subscriber methods should be registered.
     */
    public void register(Object object);

    /**
     * Registers all subscriber methods on {@code object} to receive events.
     * Subscriber methods are selected and classified using this EventBus's {@link SubscriberFindingStrategy}; the default strategy is the {@link AnnotatedSubscriberFinder}.
     * 
     * @param object
     *        object whose subscriber methods should be registered.
     */
    public void register(Object object, final DispatcherBase dispatcher);

    /**
     * Unregisters all subscriber methods on a registered {@code object}.
     * 
     * @param object
     *        object whose subscriber methods should be unregistered.
     * @throws IllegalArgumentException
     *         if the object was not previously registered.
     */
    public void unregister(Object object);

    /**
     * Posts an event to all registered subscribers. This method will return
     * successfully after the event has been posted to all subscribers, and
     * regardless of any exceptions thrown by subscribers.
     * 
     * <p>
     * If no subscribers have been subscribed for {@code event}'s class, and {@code event} is not already a {@link DeadEvent}, it will be wrapped in a DeadEvent and reposted.
     * 
     * @param event
     *        event to post.
     */
    public void post(Object event);

}