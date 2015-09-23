/**
 * 
 */

package cn.wagentim.homeapps.generics;

import java.util.Iterator;

/**
 * Simple wrapper which delegates everything.
 * 
 * @author yuku8413
 * 
 */
class GIteratorWrapper<T> implements GIterator<T>
{
    private final Iterator iterator;

    GIteratorWrapper(Iterator pIterator)
    {
        iterator = pIterator;
    }

    /**
     * @return
     * @see java.util.Iterator#hasNext()
     */
    public boolean hasNext()
    {
        return iterator.hasNext();
    }

    /**
     * @return
     * @see java.util.Iterator#next()
     */
    public T next()
    {
        return (T) iterator.next();
    }

    /**
     * 
     * @see java.util.Iterator#remove()
     */
    public void remove()
    {
        iterator.remove();
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o)
    {
        return iterator.equals(o);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        return iterator.hashCode();
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return iterator.toString();
    }
}
