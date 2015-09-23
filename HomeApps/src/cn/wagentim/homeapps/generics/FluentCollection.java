
package cn.wagentim.homeapps.generics;

import java.util.Collection;
import java.util.List;

import cn.wagentim.homeapps.utils.Optional;
import cn.wagentim.homeapps.utils.Preconditions;
import cn.wagentim.homeapps.utils.Predicate;
import cn.wagentim.homeapps.utils.Predicates;

/**
 * This class provides utility methods to perform operations on a collection
 * using chaining. Example of use:
 *
 * <pre>
 * {@code
 *     GList<Integer> listOfIntegers = Generics.newArrayList(11, 15, 2, 14);
 *     GList<String> listOfFilteredAndSortedStrings = FluentCollection
 *     .from(listOfIntegers)
 *     .filter(new Predicate<Integer>()
 *     {
 *         public boolean apply(Integer integer)
 *         {
 *             return integer > 10;
 *         }
 *     }).sort(new GComparator<Integer>()
 *     {
 *         public int compare(Integer first, Integer second)
 *         {
 *             return first.compareTo(second);
 *         }
 *     }).transform(new Function<Integer, String>()
 *     {
 *         public String apply(Integer integer)
 *         {
 *             return Integer.toBinaryString(integer);
 *         }
 *     }).toList();
 * </pre>
 *
 * @author yuku8413
 *
 */
public final class FluentCollection<T>
{
    private final GCollection<T> collection;

    private FluentCollection(GCollection<T> collection)
    {
        Preconditions.checkNotNull(collection);
        this.collection = collection;
    }

    /**
     * Returns new {@link FluentCollection} backed by the given {@link Collection}.
     */
    public static <T> FluentCollection<T> from(Collection collection)
    {
        return from(new GCollectionWrapper<T>(collection));
    }

    /**
     * Returns new {@link FluentCollection} backed by the given {@link GCollection}.
     */
    public static <T> FluentCollection<T> from(GCollection<T> collection)
    {
        return new FluentCollection<T>(Generics.newArrayList(collection));
    }

    /**
     * Returns new {@link FluentCollection} backed by the given Array.
     */
    public static <T> FluentCollection<T> from(T... elements)
    {
        return new FluentCollection<T>(Generics.newArrayList(elements));
    }

    /**
     * Returns new {@link FluentCollection} which is filtered and contains only
     * element to which the given {@link Predicate} applied.
     */
    public FluentCollection<T> filter(Predicate<T> predicate)
    {
        return from(Predicates.filter(collection, predicate));
    }

    /**
     * Returns a {@link FluentCollection} which is sorted using the given {@link GComparator}.
     */
    public FluentCollection<T> sort(GComparator<T> comparator)
    {
        Generics.sort((GList<T>)collection, comparator);
        return from(collection);
    }

    /**
     * Returns a {@link FluentCollection} which is transformed using the given {@link Function}.
     */
    public <R> FluentCollection<R> transform(Function<? super T, R> function)
    {
        GCollection<R> transformed = Generics.newArrayListWithCapacity(collection.size());

        for ( GIterator<T> iterator = collection.iterator(); iterator.hasNext(); )
        {
            transformed.add(function.apply(iterator.next()));
        }

        return from(transformed);
    }

    /**
     * Performs an operation specified by the given {@link Consumer} on each
     * element.
     */
    public FluentCollection<T> forEach(final Consumer<T> consumer)
    {
        for ( GIterator<T> iterator = collection.iterator(); iterator.hasNext(); )
        {
            consumer.accept(iterator.next());
        }
        return this;
    }

    /**
     * Returns a backing {@link GList}
     */
    public GList<T> toList()
    {
        return (GList<T>)collection;
    }

    /**
     * Returns a backing {@link List}
     */
    public List toListCompat()
    {
        return (List)collection.getBackingCollection();
    }

    public T[] toArray(T[] array)
    {
        return collection.toArray(array);
    }

    public GIterator<T> iterator()
    {
        return collection.iterator();
    }

    public Optional<T> first()
    {
        return new Optional<T>(collection.isEmpty() ? null : collection.iterator().next());
    }

    public String toString()
    {
        return collection.toString();
    }
}
