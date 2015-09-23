/**
 * 
 */

package cn.wagentim.homeapps.generics;

/**
 * Comparator is used to compare two objects to determine their ordering in
 * respect to each other.
 */

public interface GComparator<T>
{
    /**
     * Compare the two objects to determine the relative ordering.
     * 
     * @return an int < 0 if object1 is less than object2, 0 if they are equal,
     *         and > 0 if object1 is greater
     */
    public int compare(T first, T second);
}
