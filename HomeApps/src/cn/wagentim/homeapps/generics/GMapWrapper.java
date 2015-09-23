/**
 *
 */

package cn.wagentim.homeapps.generics;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.wagentim.homeapps.utils.Preconditions;

/**
 * A simple wrapper, which delegates everything.
 *
 * @author yuku8413
 *
 */
public class GMapWrapper<K, V> implements GMap<K, V>
{
    private static class GEntryWrapper<K, V> implements GEntry<K, V>
    {
        private final Entry entry;

        GEntryWrapper(Entry pEntry)
        {
            entry = pEntry;
        }

        /**
         * @return
         * @see java.util.Map.Entry#getKey()
         */
        public K getKey()
        {
            return (K) entry.getKey();
        }

        /**
         * @return
         * @see java.util.Map.Entry#getValue()
         */
        public V getValue()
        {
            return (V) entry.getValue();
        }

        /**
         * @param object
         * @return
         * @see java.util.Map.Entry#setValue(java.lang.Object)
         */
        public V setValue(V object)
        {
            return (V) entry.setValue(object);
        }
    }

    protected final Map backingMap;

    public GMapWrapper(Map pMap)
    {
        Preconditions.checkNotNull(pMap);
        backingMap = pMap;
    }

    /**
     *
     * @see java.util.Map#clear()
     */
    public void clear()
    {
        backingMap.clear();
    }

    /**
     * @param key
     * @return
     * @see java.util.Map#containsKey(java.lang.Object)
     */
    public boolean containsKey(K key)
    {
        return backingMap.containsKey(key);
    }

    /**
     * @param value
     * @return
     * @see java.util.Map#containsValue(java.lang.Object)
     */
    public boolean containsValue(V value)
    {
        return backingMap.containsValue(value);
    }

    /**
     * @return
     * @see java.util.Map#entrySet()
     */
    public GSet<GEntry<K, V>> entrySet()
    {
        Set backingEntrySet = backingMap.entrySet();

        GSet<GEntry<K, V>> entrySet = new GSetWrapper<GEntry<K, V>>(new HashSet(backingEntrySet.size()));

        for (Iterator iterator = backingEntrySet.iterator(); iterator.hasNext();)
        {
            Entry entry = (Entry) iterator.next();
            entrySet.add(new GEntryWrapper<K, V>(entry));
        }
        return entrySet;
    }

    /**
     * @see de.audi.atip.utils.generics.GMap#get(java.lang.Object)
     */
    public V get(K key)
    {
        return (V) backingMap.get(key);
    }

    /**
     * @return
     * @see java.util.Map#isEmpty()
     */
    public boolean isEmpty()
    {
        return backingMap.isEmpty();
    }

    /**
     * @return
     * @see java.util.Map#keySet()
     */
    public GSet<K> keySet()
    {
        return new GSetWrapper<K>(backingMap.keySet());
    }

    /**
     * @param key
     * @param value
     * @return
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    public V put(K key, V value)
    {
        return (V) backingMap.put(key, value);
    }

    /**
     * @param backingMap
     * @see java.util.Map#putAll(java.util.Map)
     */
    public void putAll(GMap<K, V> map)
    {
        Preconditions.checkNotNull(map);
        Map backingMap = map.getBackingMap();
        Preconditions.checkNotNull(backingMap);
        backingMap.putAll(backingMap);
    }

    /**
     * @param key
     * @return
     * @see java.util.Map#remove(java.lang.Object)
     */
    public V remove(K key)
    {
        return (V) backingMap.remove(key);
    }

    /**
     * @return
     * @see java.util.Map#size()
     */
    public int size()
    {
        return backingMap.size();
    }

    /**
     * @return
     * @see java.util.Map#values()
     */
    public GCollection<V> values()
    {
        return new GCollectionWrapper<V>(backingMap.values());
    }

    /**
     * @see de.audi.atip.utils.generics.GMap#getBackingMap()
     */
    public Map getBackingMap()
    {
        return backingMap;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        GMapWrapper<?, ?> other = (GMapWrapper<?, ?>) obj;
        if (backingMap == null)
        {
            if (other.backingMap != null)
            {
                return false;
            }
        }
        else if (!backingMap.equals(other.backingMap))
        {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (backingMap == null) ? 0 : backingMap.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return backingMap.toString();
    }
}
