/**
 * 
 */

package cn.wagentim.homeapps.generics;

import java.util.Collection;
import java.util.Set;

/**
 * A simple wrapper, which delegates everything.
 * 
 * @author yuku8413
 * 
 */
public class GSetWrapper<T> implements GSet<T>
{
    private final Set backingSet;

    /**
     * 
     */
    public GSetWrapper(Set pSet)
    {
        backingSet = pSet;
    }

    /**
     * @param object
     * @return
     * @see java.util.Collection#add(java.lang.Object)
     */
    public boolean add(T object)
    {
        return backingSet.add(object);
    }

    /**
     * @param backingSet
     * @return
     * @see java.util.Collection#addAll(java.util.Collection)
     */
    public boolean addAll(GCollection<T> collection)
    {
        return backingSet.addAll(collection.getBackingCollection());
    }

    /**
     * 
     * @see java.util.Collection#clear()
     */
    public void clear()
    {
        backingSet.clear();
    }

    /**
     * @param object
     * @return
     * @see java.util.Collection#contains(java.lang.Object)
     */
    public boolean contains(T object)
    {
        return backingSet.contains(object);
    }

    /**
     * @param backingSet
     * @return
     * @see java.util.Collection#containsAll(java.util.Collection)
     */
    public boolean containsAll(GCollection<T> collection)
    {
        return backingSet.containsAll(collection.getBackingCollection());
    }

    /**
     * @return
     * @see java.util.Collection#isEmpty()
     */
    public boolean isEmpty()
    {
        return backingSet.isEmpty();
    }

    /**
     * @return
     * @see java.util.Collection#iterator()
     */
    public GIterator<T> iterator()
    {
        return new GIteratorWrapper<T>(backingSet.iterator());
    }

    /**
     * @param object
     * @return
     * @see java.util.Collection#remove(java.lang.Object)
     */
    public boolean remove(T object)
    {
        return backingSet.remove(object);
    }

    /**
     * @param backingSet
     * @return
     * @see java.util.Collection#removeAll(java.util.Collection)
     */
    public boolean removeAll(GCollection<T> collection)
    {
        return backingSet.removeAll(collection.getBackingCollection());
    }

    /**
     * @param backingSet
     * @return
     * @see java.util.Collection#retainAll(java.util.Collection)
     */
    public boolean retainAll(GCollection<T> collection)
    {
        return backingSet.retainAll(collection.getBackingCollection());
    }

    /**
     * @return
     * @see java.util.Collection#size()
     */
    public int size()
    {
        return backingSet.size();
    }

    /**
     * @return
     * @see java.util.Collection#toArray()
     */
    public Object[] toArray()
    {
        return backingSet.toArray();
    }

    /**
     * @param array
     * @return
     * @see java.util.Collection#toArray(java.lang.Object[])
     */
    public T[] toArray(T[] array)
    {
        return (T[]) backingSet.toArray(array);
    }

    /**
     * @param object
     * @return
     * @see java.util.Collection#equals(java.lang.Object)
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
        try
        {
            GSetWrapper<?> other = (GSetWrapper<?>) obj;
            if (backingSet == null)
            {
                if (other.backingSet != null)
                {
                    return false;
                }
            }
            else if (!backingSet.equals(other.backingSet))
            {
                return false;
            }
            return true;
        }
        catch (ClassCastException e)
        {
            return false;
        }
    }

    /**
     * @return
     * @see java.util.Collection#hashCode()
     */
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (backingSet == null) ? 0 : backingSet.hashCode());
        return result;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return backingSet.toString();
    }

    /**
     * @see de.audi.app.media.util.GCollection#getBackingCollection()
     */
    public Collection getBackingCollection()
    {
        return backingSet;
    }

}
