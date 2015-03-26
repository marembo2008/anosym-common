package com.anosym.common;

import com.google.common.annotations.VisibleForTesting;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import javax.annotation.Nonnull;

import static com.anosym.common.Amount.CentRoundingMode.SPECIFIED_ACCURACY;
import static com.anosym.common.util.StringsUtil.group;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.lang.Math.log10;
import static java.lang.String.format;

/**
 * Maintains the amount to the nearest hundred cents.
 * @author marembo
 */
public final class Amount implements Comparable<Amount>, Serializable {

    private static final long serialVersionUID = 12282948294L;
    @VisibleForTesting
    static final String GLOBAL_CENT_ROUNDING_MODE_PROPERTY = "com.anosym.amount.centRoundingMode";
    @VisibleForTesting
    static final String GLOBAL_ACCURACY_SCALE = "com.anosym.amount.accuracyScale";

    @Nonnull
    private final Currency currency;
    @Nonnull
    private final Integer valueInCents;
    private final CentRoundingMode centRoundingMode;
    private final Integer accuracyScale;

    public Amount(@Nonnull final Currency currency, @Nonnull final Integer valueInCents) {
        this(currency, valueInCents, defaultCentRoundingMode(), defaultAccuracyScale());
    }

    public Amount(@Nonnull final Currency currency,
                  @Nonnull final Integer valueInCents,
                  @Nonnull final CentRoundingMode centRoundingMode) {
        this(currency, valueInCents, centRoundingMode, defaultAccuracyScale());
    }

    public Amount(@Nonnull final Currency currency,
                  @Nonnull final Integer valueInCents,
                  @Nonnull final CentRoundingMode centRoundingMode,
                  @Nonnull final Integer accuracyScale) {
        this.currency = checkNotNull(currency, "The currency must not be null");
        this.centRoundingMode = checkNotNull(centRoundingMode, "The centRoundingMode must not be null");
        this.valueInCents = normalize(checkNotNull(valueInCents, "The value must not be null"));
        this.accuracyScale = normalize(checkNotNull(accuracyScale, "The accuracyScale must not be null"));
        checkAccuracyScaleAndMode();
    }

    public Amount(@Nonnull final Currency currency, @Nonnull final BigDecimal valueWithDecimalCents) {
        this(currency, valueWithDecimalCents.doubleValue(), defaultCentRoundingMode(), defaultAccuracyScale());
    }

    public Amount(@Nonnull final Currency currency, @Nonnull final Double valueWithDecimalCents) {
        this(currency, valueWithDecimalCents, defaultCentRoundingMode(), defaultAccuracyScale());
    }

    public Amount(@Nonnull final Currency currency,
                  @Nonnull final Double valueWithDecimalCents,
                  @Nonnull final CentRoundingMode centRoundingMode) {
        this(currency, valueWithDecimalCents, centRoundingMode, defaultAccuracyScale());
    }

    public Amount(@Nonnull final Currency currency,
                  @Nonnull final Double valueWithDecimalCents,
                  @Nonnull final CentRoundingMode centRoundingMode,
                  @Nonnull final Integer accuracyScale) {
        this.currency = checkNotNull(currency, "The currency must not be null");
        this.centRoundingMode = checkNotNull(centRoundingMode, "The centRoundingMode must not be null");
        this.accuracyScale = normalize(checkNotNull(accuracyScale, "The accuracyScale must not be null"));
        checkAccuracyScaleAndMode();

        final int truncatedCents = (int) (checkNotNull(valueWithDecimalCents, "The valueWithDecimalCents must not be null") * accuracyScale);
        this.valueInCents = normalize(truncatedCents);

    }

    public Amount(@Nonnull final Currency currency,
                  @Nonnull final Number valueWithDecimalCents,
                  @Nonnull final CentRoundingMode centRoundingMode,
                  @Nonnull final Integer accuracyScale) {
        this(currency, checkNotNull(valueWithDecimalCents, "The amount value must be specified").doubleValue(), centRoundingMode, accuracyScale);

    }

    private void checkAccuracyScaleAndMode() {
        final boolean invalidScale = centRoundingMode != SPECIFIED_ACCURACY && accuracyScale > 100;
        final boolean invalidMode = centRoundingMode == SPECIFIED_ACCURACY && accuracyScale <= 100;
        final boolean invalid = invalidScale || invalidMode;
        checkState(!invalid, "The accuracy scale and cent roundingmode are invalid: {%s != %s}", centRoundingMode, accuracyScale);
        checkState(accuracyScale % 10 == 0, "The accuracy scale {%s} must be in multiple of 10 cents, e.g. 10, 100, 1000", accuracyScale);
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

    private static Integer defaultAccuracyScale() {
        return Integer.parseInt(System.getProperty(GLOBAL_ACCURACY_SCALE, "100"));
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
    public Integer getIntegerValue() {
        return getValue().intValue();
    }

    @Nonnull
    public CentRoundingMode getCentRoundingMode() {
        return centRoundingMode;
    }

    @Nonnull
    public Integer getAccuracyScale() {
        return accuracyScale;
    }

    @Nonnull
    public BigDecimal getValue() {
        final double cents = valueInCents;
        final double valueWithCents = cents / accuracyScale;
        final int scale = (int) log10(accuracyScale);
        return BigDecimal.valueOf(valueWithCents).setScale(scale, RoundingMode.UP);
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
        final Integer wholeAmount = valueInCents / accuracyScale;
        final Integer cents = valueInCents % accuracyScale;
        final int scale = (int) log10(accuracyScale);
        return format("%s.%0" + scale + "d", group(String.valueOf(wholeAmount), ", ", 3), cents);
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

    public static Integer convertToCents(@Nonnull final Number amountsWithDecimalCents) {
        return new Amount(Currency.AED, amountsWithDecimalCents.doubleValue()).getValueInCents();
    }

    public static enum CentRoundingMode {

        SPECIFIED_ACCURACY, //Implies no rounding mode is done.
        NEAREST_CENT,
        NEAREST_FIVE_CENTS,
        NEAREST_TEN_CENTS,
        NEAREST_FIFTY_CENTS,
        NEAREST_HUNDRED_CENTS;
    }
}
