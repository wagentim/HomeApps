/**
 * 
 */

package cn.wagentim.homeapps.generics;

import java.util.Map;

/**
 * Same as the {@link Map}, but generic.
 * 
 * @author yuku8413
 * 
 */
public interface GMap<K, V>
{
    public abstract static interface GEntry<K, V>
    {
        /**
         * Compares the specified object to this Map.Entry and answer if they
         * are equal. The object must be an instance of Map.Entry and have the
         * same key and value.
         * 
         * @version initial
         * 
         * @param object
         *        the object to compare with this object
         * @return true if the specified object is equal to this Map.Entry,
         *         false otherwise
         * 
         * @see #hashCode
         */
        public boolean equals(Object object);

        /**
         * Gets the key.
         * 
         * @version initial
         * 
         * @return the key
         */
        public K getKey();

        /**
         * Gets the value.
         * 
         * @version initial
         * 
         * @return the value
         */
        public V getValue();

        /**
         * Answers an integer hash code for the receiver. Objects which are
         * equal answer the same value for this method.
         * 
         * @version initial
         * 
         * @return the receiver's hash
         * 
         * @see #equals
         */
        public int hashCode();

        /**
         * Sets the value.
         * 
         * @version initial
         * 
         * @param object
         *        the old value
         */
        public V setValue(V object);
    };

    /**
     * @see java.util.Map#clear()
     */
    public void clear();

    /**
     * @see java.util.Map#containsKey(java.lang.Object)
     */
    public boolean containsKey(K key);

    /**
     * @see java.util.Map#containsValue(java.lang.Object)
     */
    public boolean containsValue(V value);

    /**
     * @see java.util.Map#entrySet()
     */
    public GSet<GEntry<K, V>> entrySet();

    /**
     * @see java.util.Map#get(java.lang.Object)
     */
    public V get(K key);

    /**
     * @see java.util.Map#isEmpty()
     */
    public boolean isEmpty();

    /**
     * @see java.util.Map#keySet()
     */
    public GSet<K> keySet();

    /**
     * @see java.util.Map#put(java.lang.Object, java.lang.Object)
     */
    public V put(K key, V value);

    /**
     * @see java.util.Map#putAll(java.util.Map)
     */
    public void putAll(GMap<K, V> map);

    /**
     * @see java.util.Map#remove(java.lang.Object)
     */
    public V remove(K key);

    /**
     * @see java.util.Map#size()
     */
    public int size();

    /**
     * @see java.util.Map#values()
     */
    public GCollection<V> values();

    /**
     * TODO
     * 
     * @return
     */
    public Map getBackingMap();
}
