package com.anosym.common.converter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 *
 * @author marembo
 * @param <F>
 * @param <T>
 */
public interface Converter<F, T> {

    /**
     * Converts the specified value to.
     *
     * @param value
     * @param params
     * @return
     */
    T to(@Nonnull final F value, @Nullable final String... params) throws ConverterException;

    /**
     * The value to convert from.
     *
     * @param value
     * @param params
     * @return
     */
    F from(@Nonnull final T value, @Nullable final String... params) throws ConverterException;
}
