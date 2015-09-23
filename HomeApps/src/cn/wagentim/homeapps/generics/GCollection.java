/**
 * 
 */

package cn.wagentim.homeapps.generics;

import java.util.Collection;
import java.util.Collections;

/**
 * Same as {@link Collection} but generic.
 * 
 * @author yuku8413
 * 
 */
public interface GCollection<T>
{
    /**
     * @see java.util.Collection#add(java.lang.Object)
     */
    public boolean add(T object);

    /**
     * @see java.util.Collection#addAll(java.util.Collection)
     */
    public boolean addAll(GCollection<T> collection);

    /**
     * @see java.util.Collection#clear()
     */
    public void clear();

    /**
     * @see java.util.Collection#contains(java.lang.Object)
     */
    public boolean contains(T object);

    /**
     * @see java.util.Collection#containsAll(java.util.Collection)
     */
    public boolean containsAll(GCollection<T> collection);

    /**
     * @see java.util.Collection#isEmpty()
     */
    public boolean isEmpty();

    /**
     * @see java.util.Collection#iterator()
     */
    public GIterator<T> iterator();

    /**
     * @see java.util.Collection#remove(java.lang.Object)
     */
    public boolean remove(T object);

    /**
     * @see java.util.Collection#removeAll(java.util.Collection)
     */
    public boolean removeAll(GCollection<T> collection);

    /**
     * @see java.util.Collection#retainAll(java.util.Collection)
     */
    public boolean retainAll(GCollection<T> collection);

    /**
     * @see java.util.Collection#size()
     */
    public int size();

    /**
     * @see java.util.Collection#toArray()
     */
    public Object[] toArray();

    /**
     * @see java.util.Collection#toArray(java.lang.Object[])
     */
    public T[] toArray(T[] array);

    /**
     * Provides access to backing up collection to use with incompatible
     * classes, e.g. {@link Collections}
     * 
     * @return backing Collection
     */
    public Collection getBackingCollection();

}
