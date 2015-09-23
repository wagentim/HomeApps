package cn.wagentim.homeapps.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/**
 * A thread-safe random-access list. List implementation targeted at Lists,
 * which are used concurrently, and which change not as frequently as they are
 * traversed (e.g. lists of callback listeners). Iterators returned by this list
 * and its sub lists (created with {@link #subList(int, int)}) cannot modify the
 * underlying list. Operations remove(), add(E) and set(E) all throw
 * UnsupportedOperationException. Sublists and Iterators may become outdated -
 * they are not consistent with the changes done to the the list after they were
 * created. Thread visibility is guaranteed.
 * 
 * @author yuku8413
 * 
 */
public class CopyOnWriteArrayList implements List, RandomAccess
{
    /**
     * List iterator which does not support remove(), add(E) and set(E)
     * 
     */
    private class CopyOnWriteListIterator implements ListIterator
    {
        private final ListIterator realListIterator;

        public CopyOnWriteListIterator(ListIterator pRealListIterator)
        {
            realListIterator = pRealListIterator;
        }

        /**
         * @see java.util.ListIterator#add(java.lang.Object)
         */
        public void add(Object object)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteArrayList");
        }

        /**
         * @see java.util.ListIterator#hasNext()
         */
        public boolean hasNext()
        {
            return realListIterator.hasNext();
        }

        /**
         * @see java.util.ListIterator#hasPrevious()
         */
        public boolean hasPrevious()
        {
            return realListIterator.hasPrevious();
        }

        /**
         * @see java.util.ListIterator#next()
         */
        public Object next()
        {
            return realListIterator.next();
        }

        /**
         * @see java.util.ListIterator#nextIndex()
         */
        public int nextIndex()
        {
            return realListIterator.nextIndex();
        }

        /**
         * @see java.util.ListIterator#previous()
         */
        public Object previous()
        {
            return realListIterator.previous();
        }

        /**
         * @see java.util.ListIterator#previousIndex()
         */
        public int previousIndex()
        {
            return realListIterator.previousIndex();
        }

        /**
         * @see java.util.ListIterator#remove()
         */
        public void remove()
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteArrayList");
        }

        /**
         * @see java.util.ListIterator#set(java.lang.Object)
         */
        public void set(Object object)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteArrayList");
        }

    }

    private class CopyOnWriteSubList implements List, RandomAccess
    {

        private final List realSubList;

        /**
         * @param subList
         */
        public CopyOnWriteSubList(List subList)
        {
            realSubList = subList;
        }

        /**
         * @see java.util.List#add(int, java.lang.Object)
         */
        public void add(int location, Object object)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteArrayList");

        }

        /**
         * @see java.util.List#add(java.lang.Object)
         */
        public boolean add(Object object)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteArrayList");
        }

        /**
         * @see java.util.List#addAll(int, java.util.Collection)
         */
        public boolean addAll(int location, Collection collection)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteArrayList");
        }

        /**
         * @see java.util.List#addAll(java.util.Collection)
         */
        public boolean addAll(Collection collection)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteArrayList");
        }

        /**
         * @see java.util.List#clear()
         */
        public void clear()
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteArrayList");

        }

        /**
         * @see java.util.List#contains(java.lang.Object)
         */
        public boolean contains(Object object)
        {
            return realSubList.contains(object);
        }

        /**
         * @see java.util.List#containsAll(java.util.Collection)
         */
        public boolean containsAll(Collection collection)
        {
            return realSubList.containsAll(collection);
        }

        /**
         * @see java.util.List#get(int)
         */
        public Object get(int location)
        {
            return realSubList.get(location);
        }

        /**
         * @see java.util.List#indexOf(java.lang.Object)
         */
        public int indexOf(Object object)
        {
            return realSubList.indexOf(object);
        }

        /**
         * @see java.util.List#isEmpty()
         */
        public boolean isEmpty()
        {
            return realSubList.isEmpty();
        }

        /**
         * @see java.util.List#iterator()
         */
        public Iterator iterator()
        {
            return new CopyOnWriteListIterator(realSubList.listIterator());
        }

        /**
         * @see java.util.List#lastIndexOf(java.lang.Object)
         */
        public int lastIndexOf(Object object)
        {
            return realSubList.lastIndexOf(object);
        }

        /**
         * @see java.util.List#listIterator()
         */
        public ListIterator listIterator()
        {
            return new CopyOnWriteListIterator(realSubList.listIterator());
        }

        /**
         * @see java.util.List#listIterator(int)
         */
        public ListIterator listIterator(int location)
        {
            return new CopyOnWriteListIterator(realSubList.listIterator(location));
        }

        /**
         * @see java.util.List#remove(int)
         */
        public Object remove(int location)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteArrayList");
        }

        /**
         * @see java.util.List#remove(java.lang.Object)
         */
        public boolean remove(Object object)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteArrayList");
        }

        /**
         * @see java.util.List#removeAll(java.util.Collection)
         */
        public boolean removeAll(Collection collection)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteArrayList");
        }

        /**
         * @see java.util.List#retainAll(java.util.Collection)
         */
        public boolean retainAll(Collection collection)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteArrayList");
        }

        /**
         * @see java.util.List#set(int, java.lang.Object)
         */
        public Object set(int location, Object object)
        {
            throw new UnsupportedOperationException("not supported by CopyOnWriteArrayList");
        }

        /**
         * @see java.util.List#size()
         */
        public int size()
        {
            return realSubList.size();
        }

        /**
         * @see java.util.List#subList(int, int)
         */
        public List subList(int start, int end)
        {
            return new CopyOnWriteSubList(realSubList.subList(start, end));
        }

        /**
         * @see java.util.List#toArray()
         */
        public Object[] toArray()
        {
            return realSubList.toArray();
        }

        /**
         * @see java.util.List#toArray(java.lang.Object[])
         */
        public Object[] toArray(Object[] array)
        {
            return realSubList.toArray(array);
        }

    }

    private volatile ArrayList realList;//NOPMD "Avoid using implementation types like 'ArrayList'; use the interface instead" - ArrayList has wider interface than List, by yuku8413

    public CopyOnWriteArrayList()
    {
        realList = new ArrayList();
    }

    public CopyOnWriteArrayList(int capacity)
    {
        realList = new ArrayList(capacity);
    }

    public CopyOnWriteArrayList(Collection collection)
    {
        realList = new ArrayList(collection);
    }

    /**
     * @see java.util.List#add(int, java.lang.Object)
     */
    public synchronized void add(int location, Object object)
    {
        ArrayList copy = new ArrayList(realList);
        copy.add(location, object);
        //atomic reference change
        realList = copy;
    }

    /**
     * @see java.util.List#add(java.lang.Object)
     */
    public synchronized boolean add(Object object)
    {
        ArrayList copy = new ArrayList(realList.size() + 1);
        copy.addAll(realList);
        copy.add(object);
        //atomic reference change
        realList = copy;
        return true;
    }

    /**
     * @see java.util.List#addAll(int, java.util.Collection)
     */
    public synchronized boolean addAll(int location, Collection collection)
    {
        ArrayList copy = new ArrayList(realList);
        copy.addAll(location, collection);
        //atomic reference change
        realList = copy;
        //true if list was modified
        return !collection.isEmpty();
    }

    /**
     * @see java.util.List#addAll(java.util.Collection)
     */
    public synchronized boolean addAll(Collection collection)
    {
        ArrayList copy = new ArrayList(realList.size() + collection.size());
        copy.addAll(realList);
        copy.addAll(collection);
        //atomic reference change
        realList = copy;
        //true if list was modified
        return !collection.isEmpty();
    }

    /**
     * @see java.util.List#clear()
     */
    public synchronized void clear()
    {
        ArrayList copy = new ArrayList();
        copy.clear();
        copy.trimToSize();
        //atomic reference change
        realList = copy;
    }

    /**
     * @see java.util.List#contains(java.lang.Object)
     */
    public boolean contains(Object object)
    {
        return realList.contains(object);
    }

    /**
     * @see java.util.List#containsAll(java.util.Collection)
     */
    public boolean containsAll(Collection collection)
    {
        return realList.containsAll(collection);
    }

    /**
     * @see java.util.List#get(int)
     */
    public Object get(int location)
    {
        return realList.get(location);
    }

    /**
     * @see java.util.List#indexOf(java.lang.Object)
     */
    public int indexOf(Object object)
    {
        return realList.indexOf(object);
    }

    /**
     * @see java.util.List#isEmpty()
     */
    public boolean isEmpty()
    {
        return realList.isEmpty();
    }

    /**
     * @see java.util.List#iterator()
     */
    public Iterator iterator()
    {
        return new CopyOnWriteListIterator(realList.listIterator());
    }

    /**
     * @see java.util.List#lastIndexOf(java.lang.Object)
     */
    public int lastIndexOf(Object object)
    {
        return realList.lastIndexOf(object);
    }

    /**
     * @see java.util.List#listIterator()
     */
    public ListIterator listIterator()
    {
        return new CopyOnWriteListIterator(realList.listIterator());
    }

    /**
     * @see java.util.List#listIterator(int)
     */
    public ListIterator listIterator(int location)
    {
        return new CopyOnWriteListIterator(realList.listIterator(location));
    }

    /**
     * @see java.util.List#remove(int)
     */
    public synchronized Object remove(int location)
    {
        ArrayList copy = new ArrayList(realList);
        Object removedObject = copy.remove(location);
        copy.trimToSize();
        //atomic reference change
        realList = copy;
        return removedObject;
    }

    /**
     * @see java.util.List#remove(java.lang.Object)
     */
    public synchronized boolean remove(Object object)
    {
        ArrayList copy = new ArrayList(realList);
        boolean isModified = copy.remove(object);
        copy.trimToSize();
        //atomic reference change
        realList = copy;
        return isModified;
    }

    /**
     * @see java.util.List#removeAll(java.util.Collection)
     */
    public synchronized boolean removeAll(Collection collection)
    {
        ArrayList copy = new ArrayList(realList);
        boolean isModified = copy.removeAll(collection);
        copy.trimToSize();
        //atomic reference change
        realList = copy;
        return isModified;
    }

    /**
     * @see java.util.List#retainAll(java.util.Collection)
     */
    public synchronized boolean retainAll(Collection collection)
    {
        ArrayList copy = new ArrayList(realList);
        boolean isModified = copy.retainAll(collection);
        copy.trimToSize();
        //atomic reference change
        realList = copy;
        return isModified;
    }

    /**
     * @see java.util.List#set(int, java.lang.Object)
     */
    public synchronized Object set(int location, Object object)
    {
        ArrayList copy = new ArrayList(realList);
        Object previousElement = copy.set(location, object);
        //atomic reference change
        realList = copy;
        return previousElement;
    }

    /**
     * @see java.util.List#size()
     */
    public int size()
    {
        return realList.size();
    }

    /**
     * @see java.util.List#subList(int, int)
     */
    public List subList(int start, int end)
    {
        return new CopyOnWriteSubList(realList.subList(start, end));
    }

    /**
     * @see java.util.List#toArray()
     */
    public Object[] toArray()
    {
        return realList.toArray();
    }

    /**
     * @see java.util.List#toArray(java.lang.Object[])
     */
    public Object[] toArray(Object[] array)
    {
        return realList.toArray(array);
    }

    /**
     * @param minimumCapacity
     * @see java.util.ArrayList#ensureCapacity(int)
     */
    public synchronized void ensureCapacity(int minimumCapacity)
    {
        realList.ensureCapacity(minimumCapacity);
    }

    /**
     * 
     * @see java.util.ArrayList#trimToSize()
     */
    public synchronized void trimToSize()
    {
        realList.trimToSize();
    }

    /**
     * @return
     * @see java.util.AbstractCollection#toString()
     */
    public String toString()
    {
        return realList.toString();
    }

    /**
     * @param object
     * @return
     * @see java.util.List#equals(java.lang.Object)
     */
    public boolean equals(Object object)
    {
        return realList.equals(object);
    }

    /**
     * @return
     * @see java.util.List#hashCode()
     */
    public int hashCode()
    {
        return realList.hashCode();
    }

    /**
     * Atomic add an object if it is not yet contained in the list.
     * 
     * @note This is not very fast, underlying implementation uses {@link Object#equals(Object)} and not {@link Object#hashCode()}
     * 
     * @param pSlotListener
     * @return true if object was added, false otherwise
     */
    public synchronized boolean addIfAbsent(Object object)
    {
        if (!realList.contains(object))
        {
            add(object);
            return true;
        }
        else
        {
            return false;
        }
    }
}
