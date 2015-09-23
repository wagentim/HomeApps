/**
 * 
 */

package cn.wagentim.homeapps.generics;

/**
 * Transforms an object of type T into an object of type R. For example:
 * 
 * <pre>
 * {@code
 *     fluentCollection.transform(new TransformFunction<Integer, String>()
 *     {
 *         public String from(Integer integer)
 *         {
 *             return Integer.toBinaryString(integer);
 *         }
 *     });
 * </pre>
 * 
 */
public interface Function<T, R>
{
    /**
     * Returns an object of type O created from an input object of type T
     */
    public R apply(T input);
}
