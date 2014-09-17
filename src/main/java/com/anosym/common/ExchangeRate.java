package com.anosym.common;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessorType;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.math.BigDecimal.ONE;
import static javax.xml.bind.annotation.XmlAccessType.FIELD;

/**
 *
 * @author marembo
 */
@XmlAccessorType(FIELD)
@MappedSuperclass
public class ExchangeRate implements Serializable {

    private static final long serialVersionUID = 142844848920L;
    @Nonnull
    private Currency baseCurrency;
    @Nonnull
    private Currency foreignCurrency;
    /**
     * Base rate, normally specified by controlling agency.
     */
    @Nonnull
    private BigDecimal baseRate;
    /**
     * When money is being sent outside, this is the logical rate to use.
     *
     * If not specified, equals baseRate.
     */
    @Nonnull
    private BigDecimal sendRate;
    /**
     * When money is being received from outside, this is the logical rate to use.
     *
     * If not specified, equals baseRate.
     */
    @Nonnull
    private BigDecimal receiveRate;
    //The base units. e.g. 1USD or 100JPY
    @Nonnull
    private BigDecimal baseUnits;

    public ExchangeRate() {
        this.baseUnits = ONE;
    }

    public ExchangeRate(@Nonnull final Currency baseCurrency,
                        @Nonnull final Currency foreignCurrency,
                        @Nonnull final BigDecimal baseRate) {
        checkNotNull(baseCurrency, "Base currency must be specified");
        checkNotNull(foreignCurrency, "Foreign currency must be specified");
        checkNotNull(baseRate, "Base rate must be specified");

        this.baseCurrency = baseCurrency;
        this.foreignCurrency = foreignCurrency;
        this.baseRate = this.sendRate = this.receiveRate = baseRate;
        this.baseUnits = ONE;
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(@Nonnull final Currency baseCurrency) {
        checkNotNull(baseCurrency, "Base currency must be specified");
        this.baseCurrency = baseCurrency;
    }

    public Currency getForeignCurrency() {
        return foreignCurrency;
    }

    public void setForeignCurrency(@Nonnull final Currency foreignCurrency) {
        checkNotNull(foreignCurrency, "Foreign currency must be specified");
        this.foreignCurrency = foreignCurrency;
    }

    public BigDecimal getBaseRate() {
        return baseRate;
    }

    @SuppressWarnings("null")
    public void setBaseRate(@Nonnull final BigDecimal baseRate) {
        checkNotNull(baseRate, "Base rate must be specified");
        this.baseRate = baseRate;
        if (this.sendRate == null) {
            this.sendRate = baseRate;
        }
        if (this.receiveRate == null) {
            this.receiveRate = baseRate;
        }
    }

    public BigDecimal getSendRate() {
        return sendRate;
    }

    public void setSendRate(BigDecimal sendRate) {
        this.sendRate = sendRate;
    }

    public BigDecimal getReceiveRate() {
        return receiveRate;
    }

    public void setReceiveRate(BigDecimal receiveRate) {
        this.receiveRate = receiveRate;
    }

    public BigDecimal getBaseUnits() {
        return baseUnits;
    }

    public void setBaseUnits(BigDecimal baseUnits) {
        this.baseUnits = baseUnits;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.baseCurrency);
        hash = 37 * hash + Objects.hashCode(this.foreignCurrency);
        hash = 37 * hash + Objects.hashCode(this.baseRate);
        hash = 37 * hash + Objects.hashCode(this.sendRate);
        hash = 37 * hash + Objects.hashCode(this.receiveRate);
        hash = 37 * hash + Objects.hashCode(this.baseUnits);
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
        final ExchangeRate other = (ExchangeRate) obj;
        if (this.baseCurrency != other.baseCurrency) {
            return false;
        }
        if (this.foreignCurrency != other.foreignCurrency) {
            return false;
        }
        if (!Objects.equals(this.baseRate, other.baseRate)) {
            return false;
        }
        if (!Objects.equals(this.sendRate, other.sendRate)) {
            return false;
        }
        if (!Objects.equals(this.receiveRate, other.receiveRate)) {
            return false;
        }
        return Objects.equals(this.baseUnits, other.baseUnits);
    }

    @Override
    public String toString() {
        return "ExchangeRate{" + ""
                + "baseCurrency=" + baseCurrency + ", "
                + "foreignCurrency=" + foreignCurrency + ", "
                + "baseRate=" + baseRate + ", "
                + "sendRate=" + sendRate + ", "
                + "receiveRate=" + receiveRate + ", "
                + "baseUnits=" + baseUnits + '}';
    }

}
