package cn.wagentim.homeapps.utils;
/*
 * Copyright (C) 2007 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


/**
 * Static convenience methods that help a method or constructor check whether it
 * was invoked correctly (whether its <i>preconditions</i> have been met). These
 * methods generally accept a {@code boolean} expression which is expected to be {@code true} (or in the case of {@code checkNotNull}, an object reference
 * which is expected to be non-null). When {@code false} (or {@code null}) is
 * passed instead, the {@code Preconditions} method throws an unchecked
 * exception, which helps the calling method communicate to <i>its</i> caller
 * that <i>that</i> caller has made a mistake. Example:
 *
 * <pre>
 * {@code
 *
 *   /**
 *    * Returns the positive square root of the given value.
 *    *
 *    * @throws IllegalArgumentException if the value is negative
 *    *}{@code /
 *   public static double sqrt(double value) {
 *     Preconditions.checkArgument(value >= 0.0, "negative value");
 *     // calculate the square root
 *   }
 *
 *   void exampleBadCaller() {
 *     double d = sqrt(-1.0);
 *   }}
 * </pre>
 *
 * In this example, {@code checkArgument} throws an {@code IllegalArgumentException} to indicate that {@code exampleBadCaller} made an error in <i>its</i> call to {@code sqrt}.
 *
 * <h3>Warning about performance</h3>
 *
 * <p>
 * The goal of this class is to improve readability of code, but in some circumstances this may come at a significant performance cost. Remember that parameter values for message construction must all be computed eagerly, and autoboxing may happen as well, even when the precondition check then succeeds (as it should almost always do in production). In some circumstances these wasted CPU cycles and allocations can add up to a real problem. Performance-sensitive precondition checks can always be converted to the customary form:
 *
 * <pre>
 * {@code
 *
 *   if (value < 0.0) {
 *     throw new IllegalArgumentException("negative value: " + value);
 *   }}
 * </pre>
 *
 * imported from Guava
 *
 * @author yuku8413
 */
public class Preconditions
{
    /**
     * checks if {@code obj} is null, and throws NullPointerException if it is.
     * @throws NullPointerException with null as message
     */

    public static void checkNotNull(Object obj)
    {
        Preconditions.checkNotNull(obj, (Object[])null);
    }

    /**
     * checks if {@code obj} is null, and throws NullPointerException if it is.
     *
     * @throws NullPointerException with a {@code message} build upon {@code messageParts}
     *                              (concatenates messagePart.toString() )
     */
    public static void checkNotNull(Object obj, Object... messageParts)
    {
        if ( obj == null )
        {
            throwNullPointerException(messageParts);
        }
    }

    /**
     * checks if {@code obj} is null, and throws NullPointerException if it is.
     *
     * @param objName a name of the tested instance. Used as a exception message.
     *
     * @throws NullPointerException with a {@code message} build upon {@code messageParts}
     *                              (concatenates messagePart.toString() )
     */
    public static void checkNotNull(Object obj, String objName)
    {
        if ( obj == null )
        {
            throw new NullPointerException(objName);// NOPMD
        }
    }

    /**
     * Checks if the {@code array} is null or empty, and if it is, throws
     * {@link NullPointerException} with a message build upon {@code messageParts}
     *
     * @param array an array to check
     * @param messageParts a parts of a exception message
     *
     * @throw NullPointerException
     */
    public static void checkNotNullOrEmpty(byte[] array, Object... messageParts)
    {
        if(array == null || array.length == 0)
        {
            throwNullPointerException(messageParts);
        }
    }

    /**
     * Checks if the {@code array} is null or empty, and if it is, throws
     * {@link NullPointerException} with a message build upon {@code messageParts}
     *
     * @param array an array to check
     * @param messageParts a parts of a exception message
     *
     * @throw NullPointerException
     */
    public static void checkNotNullOrEmpty(int[] array, Object... messageParts)
    {
        if(array == null || array.length == 0)
        {
            throwNullPointerException(messageParts);
        }
    }

    private static void throwNullPointerException(Object... messageParts)
    {
        if(messageParts.length == 0)
        {
            throw new NullPointerException(); // NOPMD
        }
        throw new NullPointerException(UtilText.buildString(messageParts)); // NOPMD
    }

    /**
     * Checks if the {@code array} of objects is null or empty, and if it is, throws
     * {@link NullPointerException} with a message build upon {@code messageParts}
     *
     * @param array an array to check
     * @param messageParts a parts of a exception message
     *
     * @throw NullPointerException
     */
    public static <T> void checkNotNullOrEmpty(T[] array, Object... messageParts)
    {
        if(array == null || array.length == 0)
        {
            throwNullPointerException(messageParts);
        }
    }

    /**
     * Checks if provided {@code value} is null or empty and if it is,
     * throws {@code NullPointerException} without.
     *
     * @param value A String to check
     *
     * @throws NullPointerException if {@code value} is null or empty
     */
    public static void checkNotNullOrEmpty(String value)
    {
        if ( UtilText.isEmpty(value) )
        {
            throwNullPointerException();
        }
    }

    /**
     * Checks if provided {@code value} is null or empty and if it is,
     * throws {@code NullPointerException} with a message build upon {@code messageParts}.
     *
     * @param value A String to check
     * @param messageParts Message parts to build the exception message upon.
     *                     Not null! See {@link UtilText#buildString(Object...)}.
     *
     * @throws NullPointerException if {@code value} is null or empty
     */
    public static void checkNotNullOrEmpty(String value, Object... messageParts)
    {
        if ( UtilText.isEmpty(value) )
        {
            throwNullPointerException(messageParts);
        }
    }



    /**
     * Ensures the truth of an {@code expression} involving one or more parameters to
     * the calling method. If {@code expression} is false, an {@link IllegalArgumentException}
     * without a message will be thrown.
     *
     * @param expression a boolean expression
     *
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression)
    {
        if ( !expression )
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Ensures the truth of an {@code expression} involving one or more parameters to
     * the calling method. If {@code expression} is false, an {@link IllegalArgumentException}
     * with a message build upon {@code messageParts} will be thrown.
     *
     * @param expression a boolean expression
     * @param messageParts parts of a message. See {@link UtilText#buildString(Object...)}.
     *
     * @throws IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(boolean expression, Object... messageParts)
    {
        if ( !expression )
        {
            if ( messageParts.length == 0 )
            {
                throw new IllegalArgumentException();
            }
            throw new IllegalArgumentException(UtilText.buildString(messageParts));
        }
    }

    /**
     * Checks if {@code min} <= {@code value} <= {@code max} and if not, throws {@link IllegalArgumentException)
     * with a default message.
     *
     * @param value a value to be checked
     * @param min lower boundary (including) of a range
     * @param max upper boundary (including) of a range
     *
     * @throws IllegalArgumentException with a standard message
     */
    public static void checkRange(long value, long min, long max)
    {
        Preconditions.checkRange(value, min, max, "value ", value, " is not in a range of <", min, ",", max, ">");
    }

    /**
     * Checks if {@code min} <= {@code value} <= {@code max} and if not, throws {@link IllegalArgumentException)
     * with a default message.
     *
     * @param value a value to be checked
     * @param min lower boundary (including) of a range
     * @param max upper boundary (including) of a range
     * @param valueName the name of the tested value, used in a message
     *
     * @throws IllegalArgumentException with a standard message
     */
    public static void checkRange(long value, long min, long max, String valueName)
    {
        Preconditions.checkRange(value, min, max, valueName, ": ", value, " is not in a range of <", min, ",", max, ">");
    }

    /**
     * Checks if {@code min} <= {@code value} <= {@code max} and if not,
     * throws an {@link IllegalArgumentException} with a message build upon {@code messageParts}
     *
     * @param value a value to check
     * @param min lower boundary of a range (including)
     * @param max upper boundary of a range (including)
     * @param messageParts a parts of a exception message. Not null! See {@link UtilText#buildString(Object...)}.
     *
     * @throws IllegalArgumentException with a message build upon {@code messageParts}
     */
    public static void checkRange(long value, long min, long max, Object... messageParts)
    {
        if ( value < min || value > max )
        {
            throw new IllegalArgumentException(UtilText.buildString(messageParts));
        }
    }

    /**
     * Checks if the {@code expression} is true, and if not, throws IllegalStateException
     * with a message build upon {@code messageParts} if {@code messageParts} is not null.
     * Use it to check the state of the instance.
     *
     * @param expression to evaluate
     * @param messageParts a parts of a exception message. See {@link UtilText#buildString(Object...)}.
     *
     * @throws IllegalStateException
     */
    public static void checkState(boolean expression, Object... messageParts)
    {
        if ( !expression )
        {
            throwIllegalStateException(messageParts);
        }
    }

    /**
     * Checks if the {@code expression} is true, and if not, throws {@link IllegalStateException} with a default message.
     * Use it to check the state of the instance.
     *
     * @param expression to evaluate
     *
     * @throws IllegalStateException
     */
    public static void checkState(boolean expression)
    {
        if(!expression)
        {
            throwIllegalStateException();
        }
    }

    private static void throwIllegalStateException(Object... messageParts)
    {
        if(messageParts.length == 0)
        {
            throw new IllegalStateException();
        }
        throw new IllegalStateException(UtilText.buildString(messageParts));
    }

}
