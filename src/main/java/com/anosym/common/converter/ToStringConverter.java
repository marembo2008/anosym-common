package com.anosym.common.converter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * In most cases, the object's toString method cannot give us the correct string representation of
 * the object we require, such as during language translation.
 * @author marembo
 * @param <T>
 */
public interface ToStringConverter<T> {

    @Nullable
    String toString(@Nonnull final T object);

}
