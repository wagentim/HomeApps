package cn.wagentim.homeapps.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * A thread-safe Map. Map implementation targeted at maps, which are used
 * concurrently, and which change not as frequently as they are traversed (e.g.
 * maps of types to sources).
 * 
 * KeySet, ValueSet and Values and their respective iterators are all limited
 * and cannot modify the underlying Map. Operations remove(), add(), retain()
 * all throw UnsupportedOperationException. Sets and Iterators may become
 * outdated - they are not consistent with changes done to the the map after
 * they were created.
 * 
 * @author yuku8413
 * 
 */
public class CopyOnWriteMap implements Map
{
    private volatile Map realMap;

    private class CopyOnWriteIterator implements Iterator
    {
        private final Iterator realIterator;

        /**
         * @param iterator
         */
        public CopyOnWriteIterator(Iterator iterator)
        {
            realIterator = iterator;
        }

        /**
         * @see java.util.Iterator#hasNext()
         */
        public boolean hasNext()
        {
            return realIterator.hasNext();
        }

        /**
         * @see java.util.Iterator#next()
         */
        public Object next()
        {
            return realIterator.next();
        }

        /**
         * @see java.util.Iterator#remove()
         */
        public void remove()
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteMap");
        }

    }

    private class CopyOnWriteMapEntriesCollection implements Collection
    {
        private final Collection realCollection;

        /**
         * @param values
         */
        public CopyOnWriteMapEntriesCollection(Collection values)
        {
            realCollection = values;
        }

        /**
         * @see java.util.Collection#add(java.lang.Object)
         */
        public boolean add(Object object)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteMap");
        }

        /**
         * @see java.util.Collection#addAll(java.util.Collection)
         */
        public boolean addAll(Collection collection)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteMap");
        }

        /**
         * @see java.util.Collection#clear()
         */
        public void clear()
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteMap");

        }

        /**
         * @see java.util.Collection#contains(java.lang.Object)
         */
        public boolean contains(Object object)
        {
            return realCollection.contains(object);
        }

        /**
         * @see java.util.Collection#containsAll(java.util.Collection)
         */
        public boolean containsAll(Collection collection)
        {
            return realCollection.containsAll(collection);
        }

        /**
         * @see java.util.Collection#isEmpty()
         */
        public boolean isEmpty()
        {
            return realCollection.isEmpty();
        }

        /**
         * @see java.util.Collection#iterator()
         */
        public Iterator iterator()
        {
            return new CopyOnWriteIterator(realCollection.iterator());
        }

        /**
         * @see java.util.Collection#remove(java.lang.Object)
         */
        public boolean remove(Object object)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteMap");
        }

        /**
         * @see java.util.Collection#removeAll(java.util.Collection)
         */
        public boolean removeAll(Collection collection)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteMap");
        }

        /**
         * @see java.util.Collection#retainAll(java.util.Collection)
         */
        public boolean retainAll(Collection collection)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteMap");
        }

        /**
         * @see java.util.Collection#size()
         */
        public int size()
        {
            return realCollection.size();
        }

        /**
         * @see java.util.Collection#toArray()
         */
        public Object[] toArray()
        {
            return realCollection.toArray();
        }

        /**
         * @see java.util.Collection#toArray(java.lang.Object[])
         */
        public Object[] toArray(Object[] array)
        {
            return realCollection.toArray(array);
        }

    }

    private class CopyOnWriteMapSet implements Set
    {
        private final Set realSet;

        /**
         * @param set
         */
        public CopyOnWriteMapSet(Set set)
        {
            realSet = set;
        }

        /**
         * @see java.util.Set#add(java.lang.Object)
         */
        public boolean add(Object object)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteMap");
        }

        /**
         * @see java.util.Set#addAll(java.util.Collection)
         */
        public boolean addAll(Collection collection)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteMap");
        }

        /**
         * @see java.util.Set#clear()
         */
        public void clear()
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteMap");

        }

        /**
         * @see java.util.Set#contains(java.lang.Object)
         */
        public boolean contains(Object object)
        {
            return realSet.contains(object);
        }

        /**
         * @see java.util.Set#containsAll(java.util.Collection)
         */
        public boolean containsAll(Collection collection)
        {
            return realSet.containsAll(collection);
        }

        /**
         * @see java.util.Set#isEmpty()
         */
        public boolean isEmpty()
        {
            return realSet.isEmpty();
        }

        /**
         * @see java.util.Set#iterator()
         */
        public Iterator iterator()
        {
            return new CopyOnWriteIterator(realSet.iterator());
        }

        /**
         * @see java.util.Set#remove(java.lang.Object)
         */
        public boolean remove(Object object)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteMap");
        }

        /**
         * @see java.util.Set#removeAll(java.util.Collection)
         */
        public boolean removeAll(Collection collection)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteMap");
        }

        /**
         * @see java.util.Set#retainAll(java.util.Collection)
         */
        public boolean retainAll(Collection collection)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteMap");
        }

        /**
         * @see java.util.Set#size()
         */
        public int size()
        {
            return realSet.size();
        }

        /**
         * @see java.util.Set#toArray()
         */
        public Object[] toArray()
        {
            return realSet.toArray();
        }

        /**
         * @see java.util.Set#toArray(java.lang.Object[])
         */
        public Object[] toArray(Object[] array)
        {
            return realSet.toArray(array);
        }

    }

    /**
     * @param sources
     */
    public CopyOnWriteMap(int capacity)
    {
        realMap = new HashMap(capacity);
    }

    /**
     * @param sources
     */
    public CopyOnWriteMap(Map map)
    {
        realMap = new HashMap(map);
    }

    /**
     * 
     */
    public CopyOnWriteMap()
    {
        realMap = new HashMap();
    }

    /**
     * @see java.util.Map#clear()
     */
    public synchronized void clear()
    {
        HashMap copy = new HashMap();
        copy.clear();
        realMap = copy;
    }

    /**
     * @see java.util.Map#containsKey(java.lang.Object)
     */
    public boolean containsKey(Object key)
    {
        return realMap.containsKey(key);
    }

    /**
     * @see java.util.Map#containsValue(java.lang.Object)
     */
    public boolean containsValue(Object value)
    {
        return realMap.containsValue(value);
    }

    /**
     * @see java.util.Map#entrySet()
     */
    public Set entrySet()
    {
        return new CopyOnWriteMapSet(realMap.entrySet());
    }

    /**
     * @see java.util.Map#get(java.lang.Object)
     */
    public Object get(Object key)
    {
        return realMap.get(key);
    }

    /**
     * @see java.util.Map#isEmpty()
     */
    public boolean isEmpty()
    {
        return realMap.isEmpty();
    }

    /**
     * @see java.util.Map#keySet()
     */
    public Set keySet()
    {
        return new CopyOnWriteMapSet(realMap.keySet());
    }

    /**
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    public synchronized Object put(Object key, Object value)
    {
        HashMap copy = new HashMap(realMap);
        Object previousObject = copy.put(key, value);
        realMap = copy;
        return previousObject;
    }

    /**
     * @see java.util.Map#putAll(java.util.Map)
     */
    public synchronized void putAll(Map map)
    {
        HashMap copy = new HashMap(realMap);
        copy.putAll(map);
        realMap = copy;
    }

    /**
     * @see java.util.Map#remove(java.lang.Object)
     */
    public synchronized Object remove(Object key)
    {
        HashMap copy = new HashMap(realMap);
        Object removedObject = copy.remove(key);
        realMap = copy;
        return removedObject;
    }

    /**
     * @see java.util.Map#size()
     */
    public int size()
    {
        return realMap.size();
    }

    /**
     * @see java.util.Map#values()
     */
    public Collection values()
    {
        return new CopyOnWriteMapEntriesCollection(realMap.values());
    }

    /**
     * If the specified key is not already associated with a value, associate it
     * with the given value.
     * 
     * @param key
     * @param value
     */
    public synchronized void putIfAbsent(Object key, Object value)
    {
        if (!realMap.containsKey(key))
        {
            realMap.put(key, value);
        }
    }

    /**
     * @param object
     * @return
     * @see java.util.AbstractMap#equals(java.lang.Object)
     */
    public boolean equals(Object object)
    {
        return realMap.equals(object);
    }

    /**
     * @return
     * @see java.util.AbstractMap#hashCode()
     */
    public int hashCode()
    {
        return realMap.hashCode();
    }

    /**
     * @return
     * @see java.util.AbstractMap#toString()
     */
    public String toString()
    {
        return realMap.toString();
    }

}
