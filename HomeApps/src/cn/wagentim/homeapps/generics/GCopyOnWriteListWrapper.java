/**
 * 
 */

package cn.wagentim.homeapps.generics;

import cn.wagentim.homeapps.utils.CopyOnWriteArrayList;

/**
 * A simple wrapper which delegates everything to the underlying list.
 * 
 * @author yuku8413
 * 
 */
class GCopyOnWriteListWrapper<T> extends GListWrapper<T> implements GCopyOnWriteList<T>
{
    private final CopyOnWriteArrayList copyOnWriteArrayList;

    /**
     * @param pList
     */
    public GCopyOnWriteListWrapper(CopyOnWriteArrayList pCopyOnWriteArrayList)
    {
        super(pCopyOnWriteArrayList);
        copyOnWriteArrayList = pCopyOnWriteArrayList;
    }

    /**
     * @see de.audi.atip.utils.generics.GCopyOnWriteList#addIfAbsent(java.lang.Object)
     */
    public boolean addIfAbsent(T object)
    {
        return copyOnWriteArrayList.addIfAbsent(object);
    }
}
