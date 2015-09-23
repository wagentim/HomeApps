/**
 * 
 */

package cn.wagentim.homeapps.generics;

/**
 * Same as the {@link Set}, but generic.
 * 
 * @author yuku8413
 * 
 */
public interface GSet<T> extends GCollection<T>
{
    /**
     * @see java.util.Set#add(java.lang.Object)
     */
    public boolean add(T object);

    /**
     * @see java.util.Set#addAll(java.util.Collection)
     */
    public boolean addAll(GCollection<T> collection);

    /**
     * @see java.util.Set#clear()
     */
    public void clear();

    /**
     * @see java.util.Set#contains(java.lang.Object)
     */
    public boolean contains(T object);

    /**
     * @see java.util.Set#containsAll(java.util.Collection)
     */
    public boolean containsAll(GCollection<T> collection);

    /**
     * @see java.util.Set#isEmpty()
     */
    public boolean isEmpty();

    /**
     * @see java.util.Set#iterator()
     */
    public GIterator<T> iterator();

    /**
     * @see java.util.Set#remove(java.lang.Object)
     */
    public boolean remove(T object);

    /**
     * @see java.util.Set#removeAll(java.util.Collection)
     */
    public boolean removeAll(GCollection<T> collection);

    /**
     * @see java.util.Set#retainAll(java.util.Collection)
     */
    public boolean retainAll(GCollection<T> collection);

    /**
     * @see java.util.Set#size()
     */
    public int size();

    /**
     * @see java.util.Set#toArray()
     */
    public Object[] toArray();

    /**
     * @see java.util.Set#toArray(java.lang.Object[])
     */
    public T[] toArray(T[] array);

}
