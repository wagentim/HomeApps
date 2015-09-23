/**
 * 
 */
package cn.wagentim.homeapps.utils;

public class Optional<T>
{
    private final T object;

    public Optional(T object)
    {
        this.object = object;
    }

    public T get()
    {
        return object;
    }

    public boolean exists()
    {
        return object != null;
    }
}