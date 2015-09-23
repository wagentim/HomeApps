/**
 * 
 */

package cn.wagentim.homeapps.generics;

import cn.wagentim.homeapps.utils.CopyOnWriteArrayList;

/**
 * Same as {@link CopyOnWriteArrayList}, but generic.
 * 
 * @author yuku8413
 * 
 */
public interface GCopyOnWriteList<T> extends GList<T>
{
    /**
     * Atomic add an object if it is not yet contained in the list.
     * 
     * @note This is not very fast, underlying implementation uses {@link Object#equals(Object)} and not {@link Object#hashCode()}
     * 
     * @param pSlotListener
     * @return true if object was added, false otherwise
     */
    public boolean addIfAbsent(T object);
}
