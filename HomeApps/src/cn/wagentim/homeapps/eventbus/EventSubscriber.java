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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.wagentim.homeapps.utils.Preconditions;

/**
 * Wraps a single-argument subscriber method on a specific object and an
 * exception handler. In instance will be created for each applicable method in
 * the registered listener. {@link #handleEvent(Object)} invokes the method
 * synchronously. For asynchronous invocation see {@link AsyncEventSubscriber}.
 *
 * @author yuku8413
 */
class EventSubscriber
{

    /** Object sporting the subscriber method. */
    private final Object target;
    /** Subscriber method. */
    private final Method method;
    private final SubscriberExceptionHandler subscriberExceptionHandler;

    /**
     * Creates a new EventSubscriber to wrap {@code method} on @{code target}.
     *
     * @param target
     *        object to which the method applies.
     * @param method
     *        subscriber method.
     */
    EventSubscriber(Object target, Method method, SubscriberExceptionHandler subscriberExceptionHandler)
    {
        Preconditions.checkNotNull(target);
        Preconditions.checkNotNull(method);
        Preconditions.checkNotNull(subscriberExceptionHandler);
        this.target = target;
        this.method = method;
        this.subscriberExceptionHandler = subscriberExceptionHandler;
        method.setAccessible(true);
    }

    /**
     * Invokes the wrapped subscriber method to handle {@code event}.
     *
     * @param event
     *        event to handle
     * @throws InvocationTargetException
     *         if the wrapped method throws any {@link Throwable} that is
     *         not an {@link Error} ({@code Error} instances are propagated
     *         as-is).
     */
    public void handleEvent(Object event)
    {
        try
        {
            method.invoke(target, new Object[]{event});
        }
        catch (IllegalArgumentException e)
        {
            throw new Error("Method rejected target/argument: " + event, e);
        }
        catch (IllegalAccessException e)
        {
            throw new Error("Method became inaccessible: " + event, e);
        }
        catch (InvocationTargetException e)
        {
            subscriberExceptionHandler.handleException(e.getCause(), this);
        }
    }

    public String toString()
    {
        return "[wrapper " + method + "]";
    }

    public int hashCode()
    {
        final int PRIME = 31;
        return (PRIME + method.hashCode()) * PRIME + System.identityHashCode(target);
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof EventSubscriber)
        {
            EventSubscriber that = (EventSubscriber) obj;
            // Use == so that different equal instances will still receive events.
            // We only guard against the case that the same object is registered
            // multiple times
            return target == that.target && method.equals(that.method);
        }
        return false;
    }

    public Object getSubscriber()
    {
        return target;
    }

    public Method getMethod()
    {
        return method;
    }
}
