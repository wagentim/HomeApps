/**
 * 
 */

package cn.wagentim.homeapps.generics;

import java.util.Iterator;

/**
 * Same as {@link Iterator}, but generic.
 * 
 * @author yuku8413
 * 
 */
public interface GIterator<T>
{
    /**
     * @see java.util.Iterator#hasNext()
     */
    public boolean hasNext();

    /**
     * @see java.util.Iterator#next()
     */
    public T next();

    /**
     * @see java.util.Iterator#remove()
     */
    public void remove();
}
