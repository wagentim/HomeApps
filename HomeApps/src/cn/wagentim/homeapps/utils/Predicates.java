package cn.wagentim.homeapps.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;

import cn.wagentim.homeapps.generics.GCollection;
import cn.wagentim.homeapps.generics.GIterator;
import cn.wagentim.homeapps.generics.GList;
import cn.wagentim.homeapps.generics.Generics;

/**
 * Provides static methods for working with Collection and {@link GCollection} instances using {@link Predicate}.
 * 
 * @author yuku8413
 * 
 */
public class Predicates
{
    private Predicates()
    {
    }

    /**
     * Applies if all added predicates apply. This is a logic AND. Not immutable
     * on purpose. If not desired, create a builder.
     * 
     */
    public static class AndCompoundPredicate<T> implements Predicate<T>
    {
        private final GList<Predicate<T>> predicates;

        public AndCompoundPredicate()
        {
            predicates = Generics.newArrayList();
        }

        public void add(Predicate<T> predicate)
        {
            predicates.add(predicate);
        }

        public boolean apply(T object)
        {
            for (int i = 0; i < predicates.size(); i++)
            {
                if (!predicates.get(i).apply(object))
                {
                    return false;
                }
            }
            // all predicates applied
            return true;
        }
    }

    /**
     * Applies if all added predicates apply. This is a logic AND. Not immutable
     * on purpose. If not desired, create a builder.
     * 
     */
    public static class OrCompoundPredicate<T> implements Predicate<T>
    {
        private final GList<Predicate<T>> predicates;

        public OrCompoundPredicate()
        {
            predicates = Generics.newArrayList();
        }

        public void add(Predicate<T> predicate)
        {
            predicates.add(predicate);
        }

        public boolean apply(T object)
        {
            for (int i = 0; i < predicates.size(); i++)
            {
                if (predicates.get(i).apply(object))
                {
                    return true;
                }
            }
            // none of predicates applied
            return false;
        }
    }

    public static <T> Predicate<T> or(Predicate<T> first, Predicate<T> second)//NOPMD "Avoid using short method names" - this name is OK, yuku8413
    {
        OrCompoundPredicate<T> or = new OrCompoundPredicate<T>();
        or.add(first);
        or.add(second);
        return or;
    }

    public static <T> Predicate<T> and(Predicate<T> first, Predicate<T> second)
    {
        AndCompoundPredicate<T> and = new AndCompoundPredicate<T>();
        and.add(first);
        and.add(second);
        return and;
    }

    /**
     * Filter a collection and return a collection containing elements to which
     * a given {@link Predicate} applies.
     * 
     * This method can throw a {@link ConcurrentModificationException} if
     * filtered collection is modified elsewhere.
     * 
     * @param collection
     * @param predicate
     * @return
     */
    public static <T> Collection filter(Collection collection, Predicate<T> predicate)
    {
        ArrayList filteredCollection = new ArrayList(collection.size());
        for (GIterator<T> iterator = Generics.wrap(collection.iterator()); iterator.hasNext();)
        {
            T object = iterator.next();
            if (predicate.apply(object))
            {
                filteredCollection.add(object);
            }
        }
        filteredCollection.trimToSize();
        return filteredCollection;
    }

    /**
     * Filter a collection and return a collection containing elements to which
     * a given {@link Predicate} applies.
     * 
     * This method can throw a {@link ConcurrentModificationException} if
     * filtered collection is modified elsewhere.
     * 
     * @param <T>
     * 
     * @param collection
     * @param predicate
     * @return
     */
    public static <T> GCollection<T> filter(GCollection<? extends T> collection, Predicate<T> predicate)
    {
        ArrayList list = new ArrayList(collection.size());
        GList<T> filteredCollection = Generics.wrap(list);
        for (GIterator<? extends T> iterator = collection.iterator(); iterator.hasNext();)
        {
            T object = iterator.next();
            if (predicate.apply(object))
            {
                filteredCollection.add(object);
            }
        }
        list.trimToSize();
        return filteredCollection;
    }

    public static <T> Optional<T> tryFind(Collection collection, Predicate<T> predicate)
    {
        T result = null;
        for (GIterator<T> iterator = Generics.wrap(collection.iterator()); iterator.hasNext();)
        {
            T next = iterator.next();
            if (predicate.apply(next))
            {
                result = next;
            }
        }
        return new Optional<T>(result);
    }

    public static <T> Optional<T> tryFind(GCollection<? extends T> collection, Predicate<T> predicate)
    {
        T result = null;
        for (GIterator<? extends T> iterator = collection.iterator(); iterator.hasNext();)
        {
            T next = iterator.next();
            if (predicate.apply(next))
            {
                result = next;
            }
        }
        return new Optional<T>(result);
    }
}
