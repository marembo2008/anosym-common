package com.anosym.common;

import com.google.common.annotations.VisibleForTesting;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.lang.String.format;

/**
 * Maintains the amount to the nearest hundred cents.
 * @author marembo
 */
public final class Amount implements Comparable<Amount>, Serializable {

    private static final long serialVersionUID = 12282948294L;
    @VisibleForTesting
    static final String GLOBAL_CENT_ROUNDING_MODE_PROPERTY = "com.anosym.amount.centRoundingMode";

    @Nonnull
    private final Currency currency;
    @Nonnull
    private final Integer valueInCents;
    private final CentRoundingMode centRoundingMode;

    public Amount(@Nonnull final Currency currency, @Nonnull final Integer valueInCents) {
        this(currency, valueInCents, defaultCentRoundingMode());
    }

    public Amount(@Nonnull final Currency currency, @Nonnull final Integer valueInCents, @Nonnull final CentRoundingMode centRoundingMode) {
        this.currency = checkNotNull(currency, "The currency must not be null");
        this.centRoundingMode = checkNotNull(centRoundingMode, "The centRoundingMode must not be null");
        this.valueInCents = normalize(checkNotNull(valueInCents, "The value must not be null"));
    }

    public Amount(@Nonnull final Currency currency, @Nonnull final BigDecimal valueWithDecimalCents) {
        this(currency, valueWithDecimalCents.doubleValue(), defaultCentRoundingMode());
    }

    public Amount(@Nonnull final Currency currency, @Nonnull final Double valueWithDecimalCents) {
        this(currency, valueWithDecimalCents, defaultCentRoundingMode());
    }

    public Amount(@Nonnull final Currency currency, @Nonnull final Double valueWithDecimalCents, @Nonnull final CentRoundingMode centRoundingMode) {
        this.currency = checkNotNull(currency, "The currency must not be null");
        this.centRoundingMode = checkNotNull(centRoundingMode, "The centRoundingMode must not be null");
        final int truncatedCents = (int) (checkNotNull(valueWithDecimalCents, "The valueWithDecimalCents must not be null") * 100);
        this.valueInCents = normalize(truncatedCents);
    }

    private Integer normalize(@Nonnull final Integer valueInCents) {
        //Only if the centRoundingMode=NEAREST_TEN_CENTS;
        switch (centRoundingMode) {
            case NEAREST_HUNDRED_CENTS: {
                final Integer cents = valueInCents % 100;
                final Integer roundedCents = cents < 50 ? 0 : 100;
                return (valueInCents / 100) * 100 + roundedCents;
            }
            case NEAREST_FIFTY_CENTS: {
                final Integer cents = valueInCents % 100;
                final Integer roundedCents = cents > 0 ? (cents < 50 ? 50 : 100) : 0;
                return (valueInCents / 100) * 100 + roundedCents;
            }
            case NEAREST_TEN_CENTS: {
                final Integer cents = valueInCents % 100;
                final Integer wholeAmount = valueInCents / 100;
                final Integer lastCentDigit = cents % 10;
                final Integer roundedLastCentDigit = lastCentDigit < 5 ? 0 : 10;
                final Integer roundedCents = (cents / 10) * 10 + roundedLastCentDigit;
                return wholeAmount * 100 + roundedCents;
            }
            case NEAREST_FIVE_CENTS: {
                final Integer cents = valueInCents % 100;
                final Integer wholeAmount = valueInCents / 100;
                final Integer lastCentDigit = cents % 10;
                final Integer roundedLastCentDigit = lastCentDigit > 0 ? (lastCentDigit <= 5 ? 5 : 10) : 0;
                final Integer roundedCents = (cents / 10) * 10 + roundedLastCentDigit;
                return wholeAmount * 100 + roundedCents;
            }
            default:
                return valueInCents;
        }
    }

    /**
     * We cant make it a static constant as it may change during the execution of the program,
     * or be set late after the Amount.class has been initialized.
     */
    private static CentRoundingMode defaultCentRoundingMode() {
        return CentRoundingMode.valueOf(System.getProperty(GLOBAL_CENT_ROUNDING_MODE_PROPERTY, "NEAREST_CENT").toUpperCase());
    }

    @Nonnull
    public Currency getCurrency() {
        return currency;
    }

    @Nonnull
    public Integer getValueInCents() {
        return valueInCents;
    }

    @Nonnull
    public CentRoundingMode getCentRoundingMode() {
        return centRoundingMode;
    }

    @Nonnull
    public BigDecimal getValue() {
        final double cents = valueInCents;
        final double valueWithCents = cents / 100.0;
        return BigDecimal.valueOf(valueWithCents).setScale(2, RoundingMode.UP);
    }

    @Nonnull
    public final Amount add(@Nonnull final Amount amountToAdd) {
        return add(amountToAdd, centRoundingMode);
    }

    @Nonnull
    public final Amount add(@Nonnull final Amount amountToAdd, @Nonnull final CentRoundingMode centRoundingMode) {
        checkNotNull(amountToAdd, "The amount to add must be specified");
        checkNotNull(centRoundingMode, "The centRoundingMode must be specified");
        checkState(this.currency == amountToAdd.currency, "Cannot add amounts with differing currency");

        return new Amount(this.currency, this.valueInCents + amountToAdd.valueInCents);
    }

    @Nonnull
    public final Amount subtract(@Nonnull final Amount amountToSubtract) {
        return add(amountToSubtract, centRoundingMode);
    }

    @Nonnull
    public final Amount subtract(@Nonnull final Amount amountToSubtract, @Nonnull final CentRoundingMode centRoundingMode) {
        checkNotNull(amountToSubtract, "The amount to subtract must be specified");
        checkNotNull(centRoundingMode, "The centRoundingMode must be specified");
        checkState(this.currency == amountToSubtract.currency, "Cannot subtract amounts with differing currency");

        return new Amount(this.currency, this.valueInCents - amountToSubtract.valueInCents);
    }

    @Nonnull
    public final Amount multiply(@Nonnull final int multiplicant) {

        return new Amount(this.currency, this.valueInCents * multiplicant);
    }

    @Nonnull
    public final Amount multiply(@Nonnull final Number multiplicant) {

        final Integer valueInCents = (int) (this.valueInCents * multiplicant.doubleValue());
        return new Amount(this.currency, valueInCents);
    }

    @Nonnull
    public final Amount divide(@Nonnull final int dividor) {

        return new Amount(this.currency, this.valueInCents / dividor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, valueInCents, centRoundingMode);
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
        return this.currency == other.currency
                && Objects.equals(this.valueInCents, other.valueInCents)
                && Objects.equals(this.centRoundingMode, other.centRoundingMode);
    }

    private String getToString() {
        final Integer wholeAmount = valueInCents / 100;
        final Integer cents = valueInCents % 100;
        return format("%d.%02d", wholeAmount, cents);
    }

    @Override
    public String toString() {
        return format("%s %s", this.currency.name(), getToString());
    }

    @Override
    public int compareTo(Amount o) {
        checkState(this.currency == o.currency, "Cannot compare two amounts with different currencies");

        return this.valueInCents.compareTo(o.valueInCents);
    }

    public static enum CentRoundingMode {

        NEAREST_CENT,
        NEAREST_FIVE_CENTS,
        NEAREST_TEN_CENTS,
        NEAREST_FIFTY_CENTS,
        NEAREST_HUNDRED_CENTS;
    }
}
