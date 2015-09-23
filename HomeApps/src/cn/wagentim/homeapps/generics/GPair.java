package cn.wagentim.homeapps.generics;

/**
 * Container to ease passing around a tuple of two objects. This object provides
 * a sensible implementation of equals(), returning true if equals() is true on
 * each of the contained objects.
 * 
 * @author yuku8413
 */
public class GPair<F, S>
{
    public final F first;
    public final S second;

    /**
     * Constructor for a GPair.
     * 
     * @param first
     *        the first object in the pair
     * @param second
     *        the second object in the pair
     */
    public GPair(F first, S second)
    {
        this.first = first;
        this.second = second;
    }

    /**
     * Compute a hash code using the hash codes of the underlying objects
     * 
     * @return a hashcode of the GPair
     */
    public int hashCode()
    {
        return (first == null ? 0 : first.hashCode()) ^ (second == null ? 0 : second.hashCode());
    }

    /**
     * Checks the two objects for equality by delegating to their respective {@link Object#equals(Object)} methods.
     * 
     * @param o
     *        the {@link GPair} to which this one is to be checked for
     *        equality
     * @return true if the underlying objects of the GPair are both considered
     *         equal
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
        GPair<?, ?> other = (GPair<?, ?>) obj;
        if (first == null)
        {
            if (other.first != null)
            {
                return false;
            }
        }
        else if (!first.equals(other.first))
        {
            return false;
        }
        if (second == null)
        {
            if (other.second != null)
            {
                return false;
            }
        }
        else if (!second.equals(other.second))
        {
            return false;
        }
        return true;
    }

    public String toString()
    {
        return "GPair [first=" + first + ", second=" + second + "]";
    }

    /**
     * Convenience method for creating an appropriately typed pair.
     * 
     * @param a
     *        the first object in the pair
     * @param b
     *        the second object in the pair
     * @return a GPair that is templatized with the types of a and b
     */
    public static <A, B> GPair<A, B> create(A a, B b)
    {
        return new GPair<A, B>(a, b);
    }
}