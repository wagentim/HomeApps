package cn.wagentim.homeapps.eventbus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.wagentim.homeapps.generics.GIterator;
import cn.wagentim.homeapps.generics.GList;
import cn.wagentim.homeapps.generics.GMap;
import cn.wagentim.homeapps.generics.GPair;
import cn.wagentim.homeapps.generics.Generics;
import cn.wagentim.homeapps.utils.Preconditions;

/**
 * A class can be used to find and invoke methods using reflection. It works
 * similar to an {@link EventBus}. The difference is that it has only once
 * listener object, which is final. This makes it a lot more efficient compared
 * to an {@link EventBus} used with only one listener object.
 *
 * @author yuku8413
 */
public class ReflectionInvoker
{
    private final Object calee;
    private final GMap<Class, Method> methods;

    public ReflectionInvoker(Object calee, SubscriberFindingStrategy subscriberFindingStrategy)
    {
        Preconditions.checkNotNull(calee);
        this.calee = calee;

        GList<GPair<Class, Method>> findAllSubscribers = subscriberFindingStrategy.findAllSubscribers(calee);
        methods = Generics.newHashMapWithCapacity(findAllSubscribers.size());

        for (GIterator<GPair<Class, Method>> iterator = findAllSubscribers.iterator(); iterator.hasNext();)
        {
            iterator.next().second.setAccessible(true);
        }

        for (GIterator<GPair<Class, Method>> iterator = findAllSubscribers.iterator(); iterator.hasNext();)
        {
            GPair<Class, Method> entry = iterator.next();
            entry.second.setAccessible(true);
            methods.put(entry.first, entry.second);
        }
    }

    /**
     * Finds and invokes a corresponding method passing the event as a argument.
     *
     * @param event
     * @return
     * @throws InvocationTargetException
     */
    public boolean invoke(Object event) throws InvocationTargetException
    {
        try
        {
            Method method = methods.get(event.getClass());
            if (method != null)
            {
                method.invoke(calee, new Object[]{event});
                return true;
            }
            else
            {
                return false;
            }
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
            if (e.getCause() instanceof Error)
            {
                throw (Error) e.getCause();
            }
            throw e;
        }
    }
}
