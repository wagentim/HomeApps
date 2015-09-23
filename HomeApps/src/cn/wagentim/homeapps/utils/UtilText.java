package cn.wagentim.homeapps.utils;

import de.esolutions.fw.util.commons.Buffer;

public final class UtilText
{
    private UtilText()
    {
        // no instances
    }

    /**
     * Build a string from provided parts.
     * It invokes toString on each param and append it to the string builder.
     * if an param is null pointer, then a string "_null_" will be used
     *
     * @param params the list of parts to build the String upon. Can't be null!
     * @return String builded upon {@code params} or empty String if {@code params} is empty.
     * @throws NullPointerException when {@code params} array is null.
     */
    public static <T> String buildString(T... params)
    {
        if ( params.length == 0 )
        {
            return "";
        }

        Buffer buffer = new Buffer(20 * params.length);

        for ( int i = 0; i < params.length; i++ )
        {
            String stringPart = params[i] == null ? "_null_" : params[i].toString();
            buffer.append(stringPart);
        }

        return buffer.toString();
    }

    /**
     * @return <code>true</code> if the given String <code>str</code> is null
     * or has a length of 0.
     */
    public static boolean isEmpty(String str)
    {
        return ( ( str == null ) || ( str.length() == 0 ) || ( UtilText.hasStringOnlyWhitespaces(str) ) );
    }

    /**
     * Checks if a {@code str} consists only of whitespaces.
     * Introduced to not call str.trim().length() anymore as this creates
     * a new String object every time it's called. Instead we just use
     * what we already have and don't create new objects.
     *
     * @param str a string to check
     *
     * @return true if string consist only of whitespaces. Otherwise false.
     *
     * @throw NullPointerException if {@code str} is null
     */
    public static boolean hasStringOnlyWhitespaces(String str)
    {
        for ( int i = 0; i < str.length(); i++ )
        {
            if ( !Character.isWhitespace(str.charAt(i)) )
            {
                return false;
            }
        }

        return true;
    }

}
