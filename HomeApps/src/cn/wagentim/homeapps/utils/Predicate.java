/**
 * 
 */
package cn.wagentim.homeapps.utils;

/**
 * A predicate can be used to filter collections based on some conditions.
 * 
 * <pre>
 * class IsPositivePredicate implements Predicate
 * {
 *     public boolean apply(Object object)
 *     {
 *         return ((Integer) object).intValue() &gt; 0;
 *     }
 * }
 * </pre>
 * 
 */
public interface Predicate<T>
{
    /**
     * Check if predicate applies to the given object
     * 
     * @param object
     * @return <code>true</code> if applies.
     */
    boolean apply(T object);
}