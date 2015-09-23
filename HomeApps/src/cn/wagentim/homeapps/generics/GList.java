/**
 * 
 */

package cn.wagentim.homeapps.generics;

import java.util.Collection;
import java.util.List;

/**
 * Same as the {@link List}, but generic.
 * 
 * @author yuku8413
 * 
 */
public interface GList<T> extends GCollection<T>
{
    /**
     * @see java.util.List#add(int, java.lang.Object)
     */
    public void add(int location, T object);

    /**
     * @see java.util.List#add(java.lang.Object)
     */
    public boolean add(T t);

    /**
     * @see java.util.List#addAll(int, java.util.Collection)
     */
    public boolean addAll(int location, GCollection<T> collection);

    /**
     * @see java.util.List#addAll(java.util.Collection)
     */
    public boolean addAll(GCollection<T> collection);

    /**
     * @see java.util.List#addAll(java.util.Collection)
     */
    public boolean addAll(Collection collection);

    /**
     * @see java.util.List#clear()
     */
    public void clear();

    /**
     * @see java.util.List#contains(java.lang.Object)
     */
    public boolean contains(T object);

    /**
     * @see java.util.List#containsAll(java.util.Collection)
     */
    public boolean containsAll(GCollection<T> collection);

    /**
     * @see java.util.List#get(int)
     */
    public T get(int location);

    /**
     * @see java.util.List#indexOf(java.lang.Object)
     */
    public int indexOf(T object);

    /**
     * @see java.util.List#isEmpty()
     */
    public boolean isEmpty();

    /**
     * @see java.util.List#iterator()
     */
    public GIterator<T> iterator();

    /**
     * @see java.util.List#lastIndexOf(java.lang.Object)
     */
    public int lastIndexOf(T object);

    /**
     * @see java.util.List#listIterator()
     */
    //TODO public ListIterator listIterator();

    /**
     * @see java.util.List#listIterator(int)
     */
    //TODO public ListIterator listIterator(int location);

    /**
     * @see java.util.List#remove(int)
     */
    public T remove(int location);

    /**
     * @see java.util.List#remove(java.lang.Object)
     */
    public boolean remove(T object);

    /**
     * @see java.util.List#removeAll(java.util.Collection)
     */
    public boolean removeAll(GCollection<T> collection);

    /**
     * @see java.util.List#retainAll(java.util.Collection)
     */
    public boolean retainAll(GCollection<T> collection);

    /**
     * @see java.util.List#set(int, java.lang.Object)
     */
    public T set(int location, T object);

    /**
     * @see java.util.List#size()
     */
    public int size();

    /**
     * @see java.util.List#subList(int, int)
     */
    public GList<T> subList(int start, int end);

    /**
     * @see java.util.List#toArray()
     */
    public Object[] toArray();

    /**
     * @see java.util.List#toArray(java.lang.Object[])
     */
    public T[] toArray(T[] array);

}
