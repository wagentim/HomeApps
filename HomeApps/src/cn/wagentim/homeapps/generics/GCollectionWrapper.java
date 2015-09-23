/**
 * 
 */

package cn.wagentim.homeapps.generics;

import java.util.Collection;

/**
 * A simple wrapper which delegates everything to the underlying {@link Collection}
 * 
 * @author yuku8413
 * 
 */
class GCollectionWrapper<T> implements GCollection<T>
{
    private final Collection backingCollection;

    public GCollectionWrapper(Collection pCollection)
    {
        backingCollection = pCollection;
    }

    /**
     * @param object
     * @return
     * @see java.util.Collection#add(java.lang.Object)
     */
    public boolean add(T object)
    {
        return backingCollection.add(object);
    }

    /**
     * @param backingCollection
     * @return
     * @see java.util.Collection#addAll(java.util.Collection)
     */
    public boolean addAll(GCollection<T> collection)
    {
        return backingCollection.addAll(collection.getBackingCollection());
    }

    /**
     * 
     * @see java.util.Collection#clear()
     */
    public void clear()
    {
        backingCollection.clear();
    }

    /**
     * @param object
     * @return
     * @see java.util.Collection#contains(java.lang.Object)
     */
    public boolean contains(T object)
    {
        return backingCollection.contains(object);
    }

    /**
     * @param backingCollection
     * @return
     * @see java.util.Collection#containsAll(java.util.Collection)
     */
    public boolean containsAll(GCollection<T> collection)
    {
        return backingCollection.containsAll(collection.getBackingCollection());
    }

    /**
     * @return
     * @see java.util.Collection#isEmpty()
     */
    public boolean isEmpty()
    {
        return backingCollection.isEmpty();
    }

    /**
     * @return
     * @see java.util.Collection#iterator()
     */
    public GIterator<T> iterator()
    {
        return new GIteratorWrapper<T>(backingCollection.iterator());
    }

    /**
     * @param object
     * @return
     * @see java.util.Collection#remove(java.lang.Object)
     */
    public boolean remove(T object)
    {
        return backingCollection.remove(object);
    }

    /**
     * @param backingCollection
     * @return
     * @see java.util.Collection#removeAll(java.util.Collection)
     */
    public boolean removeAll(GCollection<T> collection)
    {
        return backingCollection.removeAll(collection.getBackingCollection());
    }

    /**
     * @param backingCollection
     * @return
     * @see java.util.Collection#retainAll(java.util.Collection)
     */
    public boolean retainAll(GCollection<T> collection)
    {
        return backingCollection.retainAll(collection.getBackingCollection());
    }

    /**
     * @return
     * @see java.util.Collection#size()
     */
    public int size()
    {
        return backingCollection.size();
    }

    /**
     * @return
     * @see java.util.Collection#toArray()
     */
    public Object[] toArray()
    {
        return backingCollection.toArray();
    }

    /**
     * @param array
     * @return
     * @see java.util.Collection#toArray(java.lang.Object[])
     */
    public T[] toArray(T[] array)
    {
        return (T[]) backingCollection.toArray(array);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (backingCollection == null) ? 0 : backingCollection.hashCode());
        return result;
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
        GCollectionWrapper<?> other = (GCollectionWrapper<?>) obj;
        if (backingCollection == null)
        {
            if (other.backingCollection != null)
            {

                return false;
            }
        }
        else if (!backingCollection.equals(other.backingCollection))
        {
            return false;
        }
        return true;
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return backingCollection.toString();
    }

    /**
     * @see de.audi.atip.utils.generics.GCollection#getBackingCollection()
     */
    public Collection getBackingCollection()
    {
        return backingCollection;
    }

}
