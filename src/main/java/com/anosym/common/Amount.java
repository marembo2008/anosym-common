package com.anosym.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;
import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.math.BigDecimal.valueOf;

/**
 *
 * @author marembo
 */
public final class Amount implements Comparable<Amount>, Serializable {

    private static final long serialVersionUID = 12282948294L;
    @Nonnull
    private final Currency currency;
    @Nonnull
    private final BigDecimal value;

    public Amount(@Nonnull final Currency currency, @Nonnull final BigDecimal value) {
        checkNotNull(currency, "The currency must not be null");
        checkNotNull(value, "The value must not be null");

        this.currency = currency;
        this.value = value;
    }

    @Nonnull
    public Currency getCurrency() {
        return currency;
    }

    @Nonnull
    public BigDecimal getValue() {
        return value;
    }

    @Nonnull
    public final Amount add(@Nonnull final Amount amountToAdd) {
        checkNotNull(amountToAdd, "The amount to add must be specified");
        checkState(this.currency == amountToAdd.currency, "Cannot add amounts with differing currency");

        return new Amount(this.currency, this.value.add(amountToAdd.value));
    }

    @Nonnull
    public final Amount subtract(@Nonnull final Amount amountToSubtract) {
        checkNotNull(amountToSubtract, "The amount to subtract must be specified");
        checkState(this.currency == amountToSubtract.currency, "Cannot subtract amounts with differing currency");

        return new Amount(this.currency, this.value.subtract(amountToSubtract.value));
    }

    @Nonnull
    public final Amount multiply(@Nonnull final int multiplicant) {

        return new Amount(this.currency, this.value.multiply(valueOf(multiplicant), MathContext.DECIMAL32));
    }

    @Nonnull
    public final Amount divide(@Nonnull final int dividor) {

        return new Amount(this.currency, this.value.divide(valueOf(dividor), MathContext.DECIMAL32));
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.currency);
        hash = 53 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Amount other = (Amount) obj;
        if (this.currency != other.currency) {
            return false;
        }
        return Objects.equals(this.value, other.value);
    }

    @Override
    public String toString() {
        return this.currency.getSymbol() + value.setScale(2, RoundingMode.UP).toString();
    }

    @Override
    public int compareTo(Amount o) {
        checkState(this.currency == o.currency, "Cannot compare two amounts with different currencies");

        return this.value.compareTo(o.value);
    }

}
