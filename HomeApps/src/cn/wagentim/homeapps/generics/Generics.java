package cn.wagentim.homeapps.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.wagentim.homeapps.utils.CopyOnWriteArrayList;
import cn.wagentim.homeapps.utils.Preconditions;

/**
 * Static utility methods to create generic lists, sets and maps or wrap
 * existing collections into generic wrappers. Examples of use.
 * <p>
 * Create a CopyOnWrite ArrayList and initialize it with 3 elements: <code><p>GList&ltString&gt strings = Generics.newCopyOnWriteList("some", "given", "elements");</code> <code><p>GList&ltString&gt strings = Generics.newCopyOnWriteList(new String[]{"this", "is", "the", "same"});</code>
 * <p>
 * Wrap an existing LInkedList into a generic wrapper: <code>
 * <p> List list = new LinkedList();
 * <p>GList&ltString&gt genericList = Generics.wrap(list);</code>
 * <p>
 * Create a HashMap: <code><p>GMap&ltString, GList&ltString&gt&gt map = Generics.newHashMapWithCapacity(INITIAL_CAPACITY);</code>
 *
 * @author yuku8413
 */
public class Generics
{
    /**
     * @return an empty and unmodifiable {@link GCollection}.
     */
    public static <T> GCollection<T> emptyCollection()
    {
        return emptyList();
    }

    /**
     * @return an empty and unmodifiable {@link GList}.
     */
    public static <T> GList<T> emptyList()
    {
        return new GListWrapper<T>(Collections.EMPTY_LIST);
    }

    /**
     * @return an empty and unmodifiable {@link GSet}.
     */
    public static <T> GSet<T> emptySet()
    {
        return new GSetWrapper<T>(Collections.EMPTY_SET);
    }

    /**
     * @return an empty and unmodifiable {@link GMap}.
     */
    public static <K, V> GMap<K, V> emptyMap()
    {
        return new GMapWrapper<K, V>(Collections.EMPTY_MAP);
    }  /**
     * Returns a generic {@link GCollection} backed by the {@link Collection} passed as an argument.
     */
    public static final <T> GCollection<T> wrap(Collection collection)
    {
        Preconditions.checkNotNull(collection);
        return new GCollectionWrapper<T>(collection);
    }

    /**
     * Returns a generic {@link GList} backed by the {@link List} passed as an
     * argument.
     */
    public static final <T> GList<T> wrap(List list)
    {
        Preconditions.checkNotNull(list);
        return new GListWrapper<T>(list);
    }

    /**
     * Returns a generic {@link GIterator} backed by the {@link Iterator} passed
     * as an argument.
     */
    public static final <T> GIterator<T> wrap(Iterator iterator)
    {
        Preconditions.checkNotNull(iterator);
        return new GIteratorWrapper<T>(iterator);
    }

    /**
     * Returns a generic {@link GMap} backed by the {@link Map} passed as an
     * argument.
     */
    public static final <K, V> GMap<K, V> wrap(Map map)
    {
        Preconditions.checkNotNull(map);
        return new GMapWrapper<K, V>(map);
    }

    /**
     * Returns a generic {@link GSet} backed by the {@link Set} passed as an
     * argument.
     */
    public static final <T> GSet<T> wrap(Set set)
    {
        Preconditions.checkNotNull(set);
        return new GSetWrapper<T>(set);
    }

    /**
     * Creates a new {@link GList} with initial capacity of {@link ArrayList} and backed by {@link ArrayList}.
     */
    public static final <T> GList<T> newArrayList()
    {
        return new GListWrapper<T>(new ArrayList());
    }

    /**
     * Creates a new {@link GList} backed by {@link ArrayList} and initialized
     * it with elements from the given {@link Collection}.
     */
    public static final <T> GList<T> newArrayList(Collection collection)
    {
        return new GListWrapper<T>(new ArrayList(collection));
    }

    /**
     * Creates a new {@link GList} backed by {@link ArrayList} and initialized
     * it with elements from the given {@link GCollection}.
     */
    public static final <T> GList<T> newArrayList(GCollection<T> collection)
    {
        return new GListWrapper<T>(new ArrayList(collection.getBackingCollection()));
    }

    /**
     * Creates a new {@link GList} backed by {@link ArrayList} and with the
     * given initial capacity.
     */
    public static final <T> GList<T> newArrayListWithCapacity(int initialSize)
    {
        return new GListWrapper<T>(new ArrayList(initialSize));
    }

    /**
     * Creates a new {@link GList} backed by {@link ArrayList} and initializes
     * it with the given elements. Example of use: <code><p>GList&ltString&gt strings = Generics.newArrayList("some", "given", "elements");</code> <code><p>GList&ltString&gt strings = Generics.newArrayList(new String[]{"this", "is", "the", "same"});</code>
     */
    public static final <T> GList<T> newArrayList(T... elements)
    {
        ArrayList list = new ArrayList(elements.length);
        for (int i = 0; i < elements.length; i++)
        {
            list.add(elements[i]);
        }
        return new GListWrapper<T>(list);
    }

    /**
     * @deprecated use {@link #newArrayList()} instead.
     */
    public static <T> GList<T> createArrayList()
    {
        return newArrayList();
    }

    /**
     * Creates a new {@link GCopyOnWriteList} backed by {@link CopyOnWriteArrayList}.
     */
    public static final <T> GCopyOnWriteList<T> newCopyOnWriteList()
    {
        return new GCopyOnWriteListWrapper<T>(new CopyOnWriteArrayList());
    }

    /**
     * Creates a new {@link GCopyOnWriteList} backed by {@link CopyOnWriteArrayList} and initialized it with elements from the
     * given {@link Collection}.
     */
    public static final <T> GCopyOnWriteList<T> newCopyOnWriteList(Collection collection)
    {
        return new GCopyOnWriteListWrapper<T>(new CopyOnWriteArrayList(collection));
    }

    /**
     * Creates a new {@link GCopyOnWriteList} backed by {@link CopyOnWriteArrayList} and initialized it with elements from the
     * given {@link GCollection}.
     */
    public static final <T> GCopyOnWriteList<T> newCopyOnWriteList(GCollection<T> collection)
    {
        return new GCopyOnWriteListWrapper<T>(new CopyOnWriteArrayList(collection.getBackingCollection()));
    }

    /**
     * Creates a new {@link GCopyOnWriteList} backed by {@link CopyOnWriteArrayList} with the given initial capacity.
     */
    public static final <T> GCopyOnWriteList<T> newCopyOnWriteListWithCapacity(int initialSize)
    {
        return new GCopyOnWriteListWrapper<T>(new CopyOnWriteArrayList(initialSize));
    }

    /**
     * Creates a new {@link GList} backed by {@link CopyOnWriteArrayList} and
     * initializes it with the given elements. Example of use: <code><p>GList&ltString&gt strings = Generics.newCopyOnWriteList("some", "given", "elements");</code> <code><p>GList&ltString&gt strings = Generics.newCopyOnWriteList(new String[]{"this", "is", "the", "same"});</code>
     */
    public static final <T> GCopyOnWriteList<T> newCopyOnWriteList(T... elements)
    {
        ArrayList list = new ArrayList(elements.length);
        for (int i = 0; i < elements.length; i++)
        {
            list.add(elements[i]);
        }
        return new GCopyOnWriteListWrapper<T>(new CopyOnWriteArrayList(list));
    }

    /**
     * Creates a new {@link GMap} backed by {@link HashMap}.
     */
    public static final <K, V> GMap<K, V> newHashMap()
    {
        return new GMapWrapper<K, V>(new HashMap());
    }

    /**
     * Creates a new {@link GMap} backed by {@link HashMap}.
     */
    public static final <K, V> GMap<K, V> newHashMap(Map map)
    {
        return new GMapWrapper<K, V>(new HashMap(map));
    }

    /**
     * Creates a new {@link GMap} backed by {@link HashMap}.
     */
    public static final <K, V> GMap<K, V> newHashMap(GMap<K, V> map)
    {
        return new GMapWrapper<K, V>(new HashMap(map.getBackingMap()));
    }

    /**
     * Creates a new {@link GMap} backed by {@link HashMap}.
     */
    public static final <K, V> GMap<K, V> newHashMapWithCapacity(int initialSize)
    {
        return new GMapWrapper<K, V>(new HashMap(initialSize));
    }

    /**
     * Creates a new {@link GSet} backed by {@link HashSet}.
     */
    public static final <T> GSet<T> newHashSet()
    {
        return new GSetWrapper<T>(new HashSet());
    }

    /**
     * Creates a new {@link GSet} backed by {@link HashSet}.
     */
    public static final <T> GSet<T> newHashSet(Collection collection)
    {
        return new GSetWrapper<T>(new HashSet(collection));
    }

    /**
     * Creates a new {@link GSet} backed by {@link HashSet}.
     */
    public static final <T> GSet<T> newHashSet(GCollection<T> collection)
    {
        return new GSetWrapper<T>(new HashSet(collection.getBackingCollection()));
    }

    /**
     * Creates a new {@link GSet} backed by {@link HashSet}.
     */
    public static final <T> GSet<T> newHashSetWithCapacity(int initialSize)
    {
        return new GSetWrapper<T>(new HashSet(initialSize));
    }

    /**
     * Creates a new {@link GSet} backed by {@link HashSet} and initializes it
     * with the given elements. Example of use: <code><p>GSet&ltString&gt strings = Generics.newHashSet("some", "given", "elements");</code> <code><p>GSet&ltString&gt strings = Generics.newHashSet(new String[]{"this", "is", "the", "same"});</code>
     */
    public static final <T> GSet<T> newHashSet(T... elements)
    {
        HashSet set = new HashSet(elements.length);
        for (int i = 0; i < elements.length; i++)
        {
            set.add(elements[i]);
        }
        return new GSetWrapper<T>(set);
    }

    /**
     * Sorts the given {@link GList} using a given {@link GComparator}.
     * Comparator is generic and has to compare elements of the same class as
     * the {@link GList} contains.
     */
    public static <T> void sort(GList<T> list, final GComparator<T> comparator)
    {
        Collections.sort((List) list.getBackingCollection(), new Comparator()
        {
            public int compare(Object object1, Object object2)
            {
                return comparator.compare((T) object1, (T) object2);
            }
        });
    }
}
