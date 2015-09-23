/*
 * Copyright (C) 2007 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.wagentim.homeapps.eventbus;

import java.lang.reflect.Method;

import cn.wagentim.homeapps.generics.GIterator;
import cn.wagentim.homeapps.generics.GList;
import cn.wagentim.homeapps.generics.GMap;
import cn.wagentim.homeapps.generics.GPair;
import cn.wagentim.homeapps.generics.Generics;
import cn.wagentim.homeapps.utils.Preconditions;
import cn.wagentim.homeapps.utils.Predicate;
import cn.wagentim.homeapps.utils.Predicates;
import de.esolutions.fw.util.commons.job.DispatcherBase;

/**
 * Dispatches events to listeners, and provides ways for listeners to register
 * themselves.
 *
 * <p>
 * The EventBus allows publish-subscribe-style communication between components without requiring the components to explicitly register with one another (and thus be aware of each other). It is designed exclusively to replace traditional Java in-process event distribution using explicit registration. It is <em>not</em> a general-purpose publish-subscribe system, nor is it intended for interprocess communication.
 *
 * <h2>Receiving Events</h2>
 * <p>
 * To receive events, an object should:
 * <ol>
 * <li>Expose a public method, known as the <i>event subscriber</i>, which accepts a single argument of the type of event desired;</li>
 * <li>At the moment it is not possible to use annotations, therefore all event classes must implement {@link EventMarker};</li>
 * <li>Pass itself to an EventBus instance's {@link #register(Object)} method.</li>
 * </ol>
 *
 * <h2>Posting Events</h2>
 * <p>
 * To post an event, simply provide the event object to the {@link #post(Object)} method. The EventBus instance will determine the type of event and route it to all registered listeners. At the moment it is not possible to use annotations, therefore all event classes must implement {@link EventMarker};
 *
 * <p>
 * Events are routed based on their type &mdash; an event will be delivered to any subscriber for any type to which the event is <em>assignable.</em> At the moment, it includes only the last class in the class hierarchy and does not apply to base classes or interfaces. Do not extend the event classes.
 *
 * <p>
 * When {@code post} is called, all registered subscribers for an event are run in sequence, so subscribers should be reasonably quick. If an event may trigger an extended process (such as a database load), spawn a thread or queue it for later. (For a convenient way to do this, use an {@link AsyncEventBus}.)
 *
 * <h2>Subscriber Methods</h2>
 * <p>
 * Event subscriber methods must accept only one argument: the event.
 *
 * <p>
 * Subscribers should not, in general, throw. If they do, the EventBus will catch and log the exception. This is rarely the right solution for error handling and should not be relied upon; it is intended solely to help find problems during development.
 *
 * <h2>Dead Events</h2>
 * <p>
 * If an event is posted, but no registered subscribers can accept it, it is considered "dead." To give the system a second chance to handle dead events, they are wrapped in an instance of {@link DeadEvent} and reposted.
 *
 * <p>
 * If a subscriber for a supertype of all events (such as Object) is registered, no event will ever be considered dead, and no DeadEvents will be generated. Accordingly, while DeadEvent extends {@link Object}, a subscriber registered to receive any Object will never receive a DeadEvent.
 *
 * <p>
 * This class is safe for concurrent use.
 *
 * <p>
 * See the Guava User Guide article on <a href= "http://code.google.com/p/guava-libraries/wiki/EventBusExplained"> {@code EventBus}</a>.
 *
 * @author yuku8413
 */
public class EventBus implements IEventBus
{
    public static final boolean RETHROW_EXCEPTIONS = true;
    public static final boolean ONLY_LOG_EXCEPTIONS = false;

    /**
     * All registered event subscribers, indexed by event type.
     *
     * <p>
     * This SetMultimap is NOT safe for concurrent use; all access should be made after acquiring a read or write lock via {@link #subscribersByTypeLock}.
     */
    private final GMap<Class, GList<EventSubscriber>> subscribersByType = Generics.newHashMap();

    /** Stores all the last event of a kind. If a listener is registered, the last event will be fired on this. */
//    private final GMap<Class, Object> eventsByType = Generics.newHashMap();

    /**
     * Strategy for finding subscriber methods in registered objects. Currently,
     * only the {@link AnnotatedSubscriberFinder} is supported, but this is
     * encapsulated for future expansion.
     */
    private final SubscriberFindingStrategy finder = new MarkerInterfaceSubscriberFindingStrategy();

    private SubscriberExceptionHandler subscriberExceptionHandler;

    private final Object subscribersByTypeLock = new Object();

    private final SubscriberFactory syncFactory = new SubscriberFactory()
    {
        public EventSubscriber create(Object object, Method method)
        {
            return new EventSubscriber(object, method, subscriberExceptionHandler);
        }
    };

    /**
     * Creates a new EventBus with the given {@code identifier}.
     *
     * @param identifier
     *        a brief name for this bus, for logging purposes. Should be a
     *        valid Java identifier.
     * @param failFast
     *        <code>true</code> will throw all exceptions, <code>false</code> will only log exceptions.
     */
    public EventBus(String identifier, boolean failFast)
    {
        this(new ThrowingSubscriberExceptionHandler(identifier));
    }

    /**
     * Creates a new EventBus with the given {@link SubscriberExceptionHandler}.
     *
     * @param subscriberExceptionHandler
     *        Handler for subscriber exceptions.
     */
    public EventBus(SubscriberExceptionHandler subscriberExceptionHandler)
    {
        Preconditions.checkNotNull(subscriberExceptionHandler);
        this.subscriberExceptionHandler = subscriberExceptionHandler;
    }

    /**
     * Registers all subscriber methods on {@code object} to receive events.
     * Subscriber methods are selected and classified using this EventBus's {@link SubscriberFindingStrategy}; Since at the moment it is not possible
     * to use annotations the default strategy is the {@link MarkerInterfaceSubscriberFindingStrategy}. This means that all
     * event classes must implement {@link EventMarker}.
     *
     * @param object
     *        object whose subscriber methods should be registered.
     */
    public void register(Object object)
    {
        register(object, syncFactory);
    }

    /**
     * Registers all subscriber methods on {@code object} to receive events.
     * <p>
     * Subscriber will be notified on using the given {@link IDispatcher}.
     * <p>
     * Subscriber methods are selected and classified using this EventBus's {@link SubscriberFindingStrategy}; the default strategy is the {@link AnnotatedSubscriberFinder}.
     *
     * @param object
     *        object whose subscriber methods should be registered.
     */
    public void register(Object object, final DispatcherBase dispatcher)
    {
        register(object, new SubscriberFactory()
        {
            public EventSubscriber create(Object object, Method method)
            {
                return new AsyncEventSubscriber(object, method, dispatcher, subscriberExceptionHandler);
            }
        });
    }

    /**
     * @param object
     */
    private void register(Object object, SubscriberFactory factory)
    {
        //the list of events that might be retriggered on the new listener
//        GList<EventWithSubscriber> events = Generics.newArrayList();

        GList<GPair<Class, Method>> methodsInListener = finder.findAllSubscribers(object);

        synchronized (subscribersByTypeLock)
        {
            for (GIterator<GPair<Class, Method>> iterator = methodsInListener.iterator(); iterator.hasNext();)
            {
                GPair<Class, Method> entry = iterator.next();

                GList<EventSubscriber> subscribers = subscribersByType.get(entry.first);
                if (subscribers == null)
                {
                    subscribers = Generics.newArrayList();
                    subscribersByType.put(entry.first, subscribers);
                }
                EventSubscriber subscriber = factory.create(object, entry.second);
                subscribers.add(subscriber);

                //do we have an event for this?
//                Object event = eventsByType.get(entry.first);
//                if (event != null)
//                {
//                    //add this to the dispatcher list
//                    events.add(new EventWithSubscriber(event, subscriber));
//                }
            }

//            if (!events.isEmpty())
//            {
//                dispatchQueuedEvents(events);
//            }
        }
    }

    /**
     * Unregisters all subscriber methods on a registered {@code object}.
     *
     * @param object
     *        object whose subscriber methods should be unregistered.
     * @throws IllegalArgumentException
     *         if the object was not previously registered.
     */
    public void unregister(final Object object)
    {
        GList<GPair<Class, Method>> methodsInListener = finder.findAllSubscribers(object);

        for (GIterator<GPair<Class, Method>> iterator = methodsInListener.iterator(); iterator.hasNext();)
        {
            GPair<Class, Method> entry = iterator.next();

            synchronized (subscribersByTypeLock)
            {
                GList<EventSubscriber> currentSubscribers = subscribersByType.get(entry.first);

                boolean removed = currentSubscribers.removeAll(Predicates.filter(currentSubscribers, new Predicate<EventSubscriber>()
                {
                    public boolean apply(EventSubscriber subscriber)
                    {
                        return subscriber.getSubscriber() == object;
                    }
                }));

                if (!removed)
                {
                    throw new IllegalArgumentException("missing event subscriber for a method. Is " + object + " registered?");
                }
            }
        }
    }

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
    public void post(Object event)
    {
        //TODO flatten hierarchy and do dispatchTypes.iterator();
        GList<EventWithSubscriber> events = Generics.newArrayList();

        boolean dispatched = false;
        Class eventType = event.getClass();
        synchronized (subscribersByTypeLock)
        {
            //keep last event
//            eventsByType.put(event.getClass(), event);

            GList<EventSubscriber> wrappers = subscribersByType.get(eventType);

            if ( (wrappers != null) && !wrappers.isEmpty())
            {
                dispatched = true;
                for (GIterator<EventSubscriber> wrappersIterator = wrappers.iterator(); wrappersIterator.hasNext();)
                {
                    enqueueEvent(events, event, wrappersIterator.next());
                }
            }
        }

        if (!dispatched && ! (event instanceof DeadEvent))
        {
            post(new DeadEvent(this, event));
        }
        dispatchQueuedEvents(events);
    }

    /**
     * Queue the {@code event} for dispatch during {@link #dispatchQueuedEvents()}. Events are queued in-order of occurrence
     * so they can be dispatched in the same order.
     */
    void enqueueEvent(GList<EventWithSubscriber> events, Object event, EventSubscriber subscriber)
    {
        events.add(new EventWithSubscriber(event, subscriber));
    }

    /**
     * Drain the queue of events to be dispatched. As the queue is being
     * drained, new events may be posted to the end of the queue.
     */
    void dispatchQueuedEvents(GList<EventWithSubscriber> events)
    {
        for (GIterator<EventWithSubscriber> iterator = events.iterator(); iterator.hasNext();)
        {
            EventWithSubscriber eventWithSubscriber = iterator.next();
            eventWithSubscriber.subscriber.handleEvent(eventWithSubscriber.event);
        }
    }

    /**
     * Simple logging handler for subscriber exceptions.
     */
    private static final class ThrowingSubscriberExceptionHandler implements SubscriberExceptionHandler
    {
        /**
         * @param identifier
         *        a brief name for this bus, for logging purposes. Should be
         *        a valid Java identifier.
         */
        public ThrowingSubscriberExceptionHandler(String identifier)
        {
        }

        public void handleException(Throwable exception, EventSubscriber eventSubscriber)
        {
            throw new RuntimeException("Could not dispatch event to " + eventSubscriber.getMethod(), exception);
        }
    }

    /** simple struct representing an event and it's subscriber */
    static class EventWithSubscriber
    {
        final Object event;
        final EventSubscriber subscriber;

        public EventWithSubscriber(Object event, EventSubscriber subscriber)
        {
            Preconditions.checkNotNull(event);
            Preconditions.checkNotNull(subscriber);
            this.event = event;
            this.subscriber = subscriber;
        }
    }

    private interface SubscriberFactory
    {

        /**
         * @param object
         * @param second
         * @return
         */
        EventSubscriber create(Object object, Method second);
        //  public
    }
}
