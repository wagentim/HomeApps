/**
 * 
 */

package cn.wagentim.homeapps.generics;

import java.util.Collection;
import java.util.List;

/**
 * A simple wrapper, which delegates everything.
 * 
 * @author yuku8413
 * 
 */
class GListWrapper<T> implements GList<T>, GCollection<T>
{
    protected final List list;

    /**
     * 
     */
    public GListWrapper(List pList)
    {
        list = pList;
    }

    /**
     * @param location
     * @param object
     * @see java.util.List#add(int, java.lang.Object)
     */
    public void add(int location, T object)
    {
        list.add(location, object);
    }

    /**
     * @param object
     * @return
     * @see java.util.List#add(java.lang.Object)
     */
    public boolean add(T object)
    {
        return list.add(object);
    }

    /**
     * @param location
     * @param collection
     * @return
     * @see java.util.List#addAll(int, java.util.Collection)
     */
    public boolean addAll(int location, GCollection<T> collection)
    {
        return list.addAll(location, collection.getBackingCollection());
    }

    /**
     * @param collection
     * @return
     * @see java.util.List#addAll(java.util.Collection)
     */
    public boolean addAll(GCollection<T> collection)
    {
        return list.addAll(collection.getBackingCollection());
    }

    /**
     * @param collection
     * @return
     * @see java.util.List#addAll(java.util.Collection)
     */
    public boolean addAll(Collection collection)
    {
        return list.addAll(collection);
    }

    /**
     * 
     * @see java.util.List#clear()
     */
    public void clear()
    {
        list.clear();
    }

    /**
     * @param object
     * @return
     * @see java.util.List#contains(java.lang.Object)
     */
    public boolean contains(T object)
    {
        return list.contains(object);
    }

    /**
     * @param collection
     * @return
     * @see java.util.List#containsAll(java.util.Collection)
     */
    public boolean containsAll(GCollection<T> collection)
    {
        return list.containsAll(collection.getBackingCollection());
    }

    /**
     * @param location
     * @return
     * @see java.util.List#get(int)
     */
    public T get(int location)
    {
        return (T) list.get(location);
    }

    /**
     * @param object
     * @return
     * @see java.util.List#indexOf(java.lang.Object)
     */
    public int indexOf(T object)
    {
        return list.indexOf(object);
    }

    /**
     * @return
     * @see java.util.List#isEmpty()
     */
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    /**
     * @return
     * @see java.util.List#iterator()
     */
    public GIterator<T> iterator()
    {
        return new GIteratorWrapper<T>(list.iterator());
    }

    /**
     * @param object
     * @return
     * @see java.util.List#lastIndexOf(java.lang.Object)
     */
    public int lastIndexOf(T object)
    {
        return list.lastIndexOf(object);
    }

    /**
     * @param location
     * @return
     * @see java.util.List#remove(int)
     */
    public T remove(int location)
    {
        return (T) list.remove(location);
    }

    /**
     * @param object
     * @return
     * @see java.util.List#remove(java.lang.Object)
     */
    public boolean remove(T object)
    {
        return list.remove(object);
    }

    /**
     * @param collection
     * @return
     * @see java.util.List#removeAll(java.util.Collection)
     */
    public boolean removeAll(GCollection<T> collection)
    {
        return list.removeAll(collection.getBackingCollection());
    }

    /**
     * @param collection
     * @return
     * @see java.util.List#retainAll(java.util.Collection)
     */
    public boolean retainAll(GCollection<T> collection)
    {
        return list.retainAll(collection.getBackingCollection());
    }

    /**
     * @param location
     * @param object
     * @return
     * @see java.util.List#set(int, java.lang.Object)
     */
    public T set(int location, T object)
    {
        return (T) list.set(location, object);
    }

    /**
     * @return
     * @see java.util.List#size()
     */
    public int size()
    {
        return list.size();
    }

    /**
     * @param start
     * @param end
     * @return
     * @see java.util.List#subList(int, int)
     */
    public GList<T> subList(int start, int end)
    {
        return new GListWrapper<T>(list.subList(start, end));
    }

    /**
     * @return
     * @see java.util.List#toArray()
     */
    public Object[] toArray()
    {
        return list.toArray();
    }

    /**
     * @param array
     * @return
     * @see java.util.List#toArray(java.lang.Object[])
     */
    public T[] toArray(T[] array)
    {
        return (T[]) list.toArray(array);
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( (list == null) ? 0 : list.hashCode());
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
        try
        {
            GListWrapper<?> other = (GListWrapper<?>) obj;
            if (list == null)
            {
                if (other.list != null)
                {
                    return false;
                }
            }
            else if (!list.equals(other.list))
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
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return list.toString();
    }

    /**
     * @see de.audi.atip.utils.generics.GCollection#getBackingCollection()
     */
    public Collection getBackingCollection()
    {
        return list;
    }
}
