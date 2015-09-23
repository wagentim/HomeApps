package cn.wagentim.homeapps.eventbus;

import java.lang.reflect.Method;
import java.util.Collection;

import cn.wagentim.homeapps.generics.GIterator;
import cn.wagentim.homeapps.generics.GList;
import cn.wagentim.homeapps.generics.GPair;
import cn.wagentim.homeapps.generics.Generics;
import cn.wagentim.homeapps.utils.Predicate;
import cn.wagentim.homeapps.utils.Predicates;
import cn.wagentim.homeapps.utils.ReflectionUtils;

/**
 * A {@link SubscriberFindingStrategy} for collecting all event subscriber
 * methods that are marked with the {@link EventMarker} interface.
 * 
 * @author yuku8413
 */
public class MarkerInterfaceSubscriberFindingStrategy implements SubscriberFindingStrategy
{
    public GList<GPair<Class, Method>> findAllSubscribers(Object listener)
    {
        GList<GPair<Class, Method>> methodsInListener = Generics.newArrayList();
        Class clazz = listener.getClass();
        //TODO transform with a function
        for (GIterator<Method> iterator = getMarkedMethods(clazz).iterator(); iterator.hasNext();)
        {
            Method method = iterator.next();
            Class[] parameterTypes = method.getParameterTypes();
            Class eventType = parameterTypes[0];

            methodsInListener.add(GPair.create(eventType, method));
        }
        return methodsInListener;
    }

    private static GList<Method> getMarkedMethods(Class clazz)
    {
        Collection publicDeclaredMethods = ReflectionUtils.getPublicDeclaredMethods(clazz);
        return Generics.newArrayList(Predicates.filter(publicDeclaredMethods, new Predicate<Method>()
        {
            public boolean apply(Method method)
            {
                Class[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1)
                {
                    Class firstArguementClass = parameterTypes[0];
                    boolean assignableFrom = firstArguementClass != null && EventMarker.class.isAssignableFrom(firstArguementClass);
                    return assignableFrom;
                }
                else
                {
                    return false;
                }
            }
        }));
    }
}
