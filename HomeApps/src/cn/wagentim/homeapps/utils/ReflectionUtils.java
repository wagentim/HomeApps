package cn.wagentim.homeapps.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import cn.wagentim.homeapps.generics.GMap;
import cn.wagentim.homeapps.generics.GPair;
import cn.wagentim.homeapps.generics.Generics;

/**
 * @author yuku8413
 * 
 */
public class ReflectionUtils
{
    public static final List parseArgs(Object param)
    {
        LinkedList paramClasses = new LinkedList();
        LinkedList args = new LinkedList();

        if (param != null)
        {
            StringTokenizer params = new StringTokenizer((String) param, ",");
            while (params.hasMoreTokens())
            {
                String token = params.nextToken().trim();
                GPair<Class, Object> pair = parseToken(token);
                paramClasses.add(pair.first);
                args.add(pair.second);
            }
        }
        return args;
    }

    private static GPair<Class, Object> parseToken(String token)
    {
        // check Integer format
        try
        {
            int integer = Integer.parseInt(token);
            return new GPair<Class, Object>(int.class, new Integer(integer));
        }
        catch (NumberFormatException e)
        {
            try
            {
                // check Long format
                long longValue = Long.parseLong(token);
                return new GPair<Class, Object>(long.class, new Long(longValue));
            }
            catch (NumberFormatException e1)
            {
                // check Float format
                try
                {
                    float floatVal = Float.parseFloat(token);
                    return new GPair<Class, Object>(float.class, new Float(floatVal));
                }
                catch (NumberFormatException e2)
                {
                    try
                    {
                        // check Double format
                        double doubleValue = Double.parseDouble(token);
                        return new GPair<Class, Object>(double.class, new Double(doubleValue));
                    }
                    catch (NumberFormatException e3)
                    {
                        // check Boolean format
                        if ( (token != null) && ( (token.toLowerCase().equals("true")) || (token.toLowerCase().equals("false"))))
                        {
                            return new GPair<Class, Object>(boolean.class, Boolean.valueOf(token));
                        } // its String
                        else
                        {
                            return new GPair<Class, Object>(String.class, token);
                        }
                    }
                }
            }
        }
    }

    /**
     * @return
     */
    public static Collection getDeclaredFields(Class clazz)
    {
        return Predicates.filter(Arrays.asList(clazz.getDeclaredFields()), new Predicate<Field>()
        {
            public boolean apply(Field field)
            {
                boolean isPrivate = Modifier.isPrivate(field.getModifiers());
                boolean isEnclosingReference = field.getName().startsWith("this");
                return ! (isEnclosingReference || isPrivate);
            }
        });
    }

    /**
     * @return
     */
    public static Collection getPublicDeclaredMethods(Class clazz)
    {
        List declaredMethods = Arrays.asList(clazz.getDeclaredMethods());

        Predicates.AndCompoundPredicate<Method> andPredicate = new Predicates.AndCompoundPredicate<Method>();
        andPredicate.add(new IsNotPrivatePredicate());
        andPredicate.add(new IsNotEnclosingAccessPredicate());

        return Predicates.filter(declaredMethods, andPredicate);
    }

    /**
     * @return
     */
    public static String getSimpleName(Class clazz)
    {
        int lastIndexOfDelimeter = Math.max(clazz.getName().lastIndexOf('$'), clazz.getName().lastIndexOf('.'));
        return clazz.getName().substring(lastIndexOfDelimeter + 1);
    }

    public static boolean implementsInterface(Class clazz, Class interfaceToLookFor)
    {
        Class classToCheck = clazz;
        while (!classToCheck.equals(Object.class))
        {
            if (Arrays.asList(classToCheck.getInterfaces()).contains(interfaceToLookFor))
            {
                return true;
            }
            //find superclass
            classToCheck = classToCheck.getSuperclass();
        }
        return false;
    }

    private static class IsNotPrivatePredicate implements Predicate<Method>
    {
        public boolean apply(Method method)
        {
            return !Modifier.isPrivate(method.getModifiers());
        }
    }

    private static class IsNotEnclosingAccessPredicate implements Predicate<Method>
    {
        public boolean apply(Method method)
        {
            return !method.getName().startsWith("access");
        }
    }

    /**
     * Applies to {@link Method}s which are not overrides of Interface of
     * Superclass methods.
     * 
     */
    private static class IsNotOverridePredicate implements Predicate<Method>
    {
        Collection superClassAndInterfcaeMethods;

        public IsNotOverridePredicate(Class clazz)
        {
            Class classToAddInterfaces = clazz;
            //add all interfaces implemented by the class
            addInterfaces(classToAddInterfaces);
            while (!classToAddInterfaces.equals(Object.class))
            {
                //find superclass
                classToAddInterfaces = clazz.getSuperclass();
                //add all interfaces implemented by the superclass
                addInterfaces(classToAddInterfaces);
                //add all methods declared in the superclass
                addMethods(classToAddInterfaces);
            }
        }

        private void addInterfaces(Class clazz)
        {
            for (Iterator iterator = Arrays.asList(clazz.getInterfaces()).iterator(); iterator.hasNext();)
            {
                Class implementedInterface = (Class) iterator.next();
                addMethods(implementedInterface);
            }
        }

        private boolean addMethods(Class implementedInterface)
        {
            return superClassAndInterfcaeMethods.addAll(Arrays.asList(implementedInterface.getDeclaredMethods()));
        }

        /**
         * Applies of object is not contained {@link #superClassAndInterfcaeMethods} by name.
         * 
         */
        public boolean apply(Method method)
        {
            return Predicates.filter(superClassAndInterfcaeMethods, new EqualsByNamePredicate(method)).isEmpty();
        }

        private class EqualsByNamePredicate implements Predicate<Method>
        {
            private Method oneMethod;

            public EqualsByNamePredicate(final Method method)
            {
                this.oneMethod = method;
            }

            public boolean apply(Method otherMethod)
            {
                return otherMethod.getName().equals(oneMethod.getName());
            }
        }
    }

    public static Optional<Field> getFieldByName(Class clazz, final String name)
    {
        Optional<Field> tryFind = Predicates.tryFind(Generics.newArrayList(clazz.getDeclaredFields()), new Predicate<Field>()
        {
            public boolean apply(Field field)
            {
                return field.getName().equals(name);
            }
        });
        return tryFind;
    }

    public static GMap<Integer, Integer> createConstantsMap(final String class1, final String class2, final String stratsWith)
    {
        GMap<Integer, Integer> map = Generics.newHashMap();
        try
        {
            Class clazz1 = Class.forName(class1);
            Class clazz2 = Class.forName(class2);

            Field[] fields1 = clazz1.getDeclaredFields();

            for (Field field1 : fields1)
            {
                if (field1.getName().startsWith(stratsWith))
                {
                    Optional<Field> field2 = ReflectionUtils.getFieldByName(clazz2, field1.getName());
                    if (field2.exists() && field2.get().getName().startsWith(stratsWith))
                    {
                        map.put(field1.getInt(clazz1), field2.get().getInt(clazz2));
                    }
                    else
                    {
                        map.put(field1.getInt(clazz1), null);
                    }
                }
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
            return null;
        }
        return map;
    }

}
