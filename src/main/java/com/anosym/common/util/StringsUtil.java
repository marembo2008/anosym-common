package com.anosym.common.util;

import com.google.common.base.Joiner;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.CharMatcher.WHITESPACE;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.nullToEmpty;

/**
 *
 * @author marembo
 */
public final class StringsUtil {

    private StringsUtil() {
    }

    /**
     * Null-Safe equality.
     * @param first  possible null first string
     * @param second possible null second string
     * @return true if the strings are equal, ignoring case.
     */
    public static boolean equalsIgnoreCase(@Nullable final String first, @Nullable String second) {
        return nullToEmpty(first).equalsIgnoreCase(nullToEmpty(second));
    }

    /**
     * Null-Safe equality.
     * @param first  possible null first string
     * @param second possible null second string
     * @return true if the strings are equal, ignoring case.
     */
    public static boolean equalsIgnoreCaseAndEndWhitespaces(@Nullable final String first, @Nullable String second) {
        return WHITESPACE.trimFrom(nullToEmpty(first)).equalsIgnoreCase(WHITESPACE.trimFrom(nullToEmpty(second)));
    }

    /**
     * Groups the sequence string into groups of size defined by the count, groups separated by the specified separator.
     * @param sequence
     * @param separator
     * @param count
     * @return
     */
    @Nonnull
    public static String group(@Nonnull final String sequence, final String separator, final int count) {
        checkNotNull(sequence, "the sequence must be specified");
        checkNotNull(separator, "the separator must be specified");
        checkArgument(count > 0, "the count must be greater than zero");

        final int sequenceLength = sequence.length();
        if (sequenceLength <= count) {
            return sequence;
        }
        final Deque<String> deque = new ArrayDeque<>(sequenceLength / count + 1);
        int offset = sequenceLength - count;
        int endset = sequenceLength;
        do {
            final String part = sequence.substring(offset, endset);
            deque.push(part);
            endset = offset;
            offset = Math.max(0, offset - count);
        } while (endset > 0);
        final Joiner joiner = Joiner.on(separator);
        return joiner.join(deque);
    }
}
