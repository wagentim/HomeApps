/**
 * 
 */

package cn.wagentim.homeapps.generics;

/**
 * An operation on a given object which does not produce any result. Can be used to modify objects or operate vie side-effects (notify listeners, etc.).
 */
public interface Consumer<T>
{
    public void accept(T object);
}
