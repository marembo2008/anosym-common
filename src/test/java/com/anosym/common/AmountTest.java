package com.anosym.common;

import org.junit.After;
import org.junit.Test;

import static com.anosym.common.Amount.CentRoundingMode.NEAREST_FIFTY_CENTS;
import static com.anosym.common.Amount.CentRoundingMode.NEAREST_FIVE_CENTS;
import static com.anosym.common.Amount.CentRoundingMode.NEAREST_HUNDRED_CENTS;
import static com.anosym.common.Amount.CentRoundingMode.NEAREST_TEN_CENTS;
import static com.anosym.common.Amount.CentRoundingMode.SPECIFIED_ACCURACY;
import static com.anosym.common.Amount.GLOBAL_CENT_ROUNDING_MODE_PROPERTY;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author marembo
 */
public class AmountTest {

    @After
    public void tearDown() {
        System.clearProperty(GLOBAL_CENT_ROUNDING_MODE_PROPERTY);
    }

    @Test
    public void testNearestCent() {
        final Amount amount = new Amount(Currency.AED, 233444);
        assertThat(amount.getValueInCents(), is(233444));
    }

    @Test
    public void testNearestFiftyCent() {
        final Amount amount = new Amount(Currency.AED, 233444, NEAREST_FIFTY_CENTS);
        assertThat(amount.getValueInCents(), is(233450));
    }

    @Test
    public void testNearestFiftyCentToHundrend() {
        final Amount amount = new Amount(Currency.AED, 233468, NEAREST_FIFTY_CENTS);
        assertThat(amount.getValueInCents(), is(233500));
    }

    @Test
    public void testNearestFiveCent() {
        final Amount amount = new Amount(Currency.AED, 233444, NEAREST_FIVE_CENTS);
        assertThat(amount.getValueInCents(), is(233445));
    }

    @Test
    public void testNearestFiveCentToTen() {
        final Amount amount = new Amount(Currency.AED, 233448, NEAREST_FIVE_CENTS);
        assertThat(amount.getValueInCents(), is(233450));
    }

    @Test
    public void testNearestHundrenCent() {
        final Amount amount = new Amount(Currency.AED, 233444, NEAREST_HUNDRED_CENTS);
        assertThat(amount.getValueInCents(), is(233400));
    }

    @Test
    public void testNearestHundrenCentToHundrend() {
        final Amount amount = new Amount(Currency.AED, 233468, NEAREST_HUNDRED_CENTS);
        assertThat(amount.getValueInCents(), is(233500));
    }

    @Test
    public void testNearestTenCent() {
        final Amount amount = new Amount(Currency.AED, 233444, NEAREST_TEN_CENTS);
        assertThat(amount.getValueInCents(), is(233440));
    }

    @Test
    public void testNearestTenCentToTen() {
        final Amount amount = new Amount(Currency.AED, 233448, NEAREST_TEN_CENTS);
        assertThat(amount.getValueInCents(), is(233450));
    }

    @Test
    public void testNearestCentDouble() {
        final Amount amount = new Amount(Currency.AED, 2334.44);
        assertThat(amount.getValueInCents(), is(233444));
    }

    @Test
    public void testNearestFiftyCentDouble() {
        final Amount amount = new Amount(Currency.AED, 2334.44, NEAREST_FIFTY_CENTS);
        assertThat(amount.getValueInCents(), is(233450));
    }

    @Test
    public void testNearestFiftyCentToHundrendDouble() {
        final Amount amount = new Amount(Currency.AED, 2334.68, NEAREST_FIFTY_CENTS);
        assertThat(amount.getValueInCents(), is(233500));
    }

    @Test
    public void testNearestFiveCentDouble() {
        final Amount amount = new Amount(Currency.AED, 2334.44, NEAREST_FIVE_CENTS);
        assertThat(amount.getValueInCents(), is(233445));
    }

    @Test
    public void testNearestFiveCentToTenDouble() {
        final Amount amount = new Amount(Currency.AED, 2334.48, NEAREST_FIVE_CENTS);
        assertThat(amount.getValueInCents(), is(233450));
    }

    @Test
    public void testNearestHundrenCentDouble() {
        final Amount amount = new Amount(Currency.AED, 2334.44, NEAREST_HUNDRED_CENTS);
        assertThat(amount.getValueInCents(), is(233400));
    }

    @Test
    public void testNearestHundrenCentToHundrendDouble() {
        final Amount amount = new Amount(Currency.AED, 2334.68, NEAREST_HUNDRED_CENTS);
        assertThat(amount.getValueInCents(), is(233500));
    }

    @Test
    public void testNearestTenCentDouble() {
        final Amount amount = new Amount(Currency.AED, 2334.44, NEAREST_TEN_CENTS);
        assertThat(amount.getValueInCents(), is(233440));
    }

    @Test
    public void testNearestTenCentToTenDouble() {
        final Amount amount = new Amount(Currency.AED, 2334.48, NEAREST_TEN_CENTS);
        assertThat(amount.getValueInCents(), is(233450));
    }

    @Test
    public void testNearestTenCentToTenDoubleFromGlobalCentRoundingMode() {
        System.setProperty(GLOBAL_CENT_ROUNDING_MODE_PROPERTY, "NEAREST_TEN_CENTS");
        final Amount amount = new Amount(Currency.AED, 2334.48);
        assertThat(amount.getValueInCents(), is(233450));
    }

    @Test
    public void testNearestFiftyCentToTenDoubleFromGlobalCentRoundingMode() {
        System.setProperty(GLOBAL_CENT_ROUNDING_MODE_PROPERTY, "NEAREST_FIFTY_CENTS");
        final Amount amount = new Amount(Currency.AED, 2334.20);
        assertThat(amount.getValueInCents(), is(233450));
    }

    @Test
    public void testToString() {
        final Amount amount = new Amount(Currency.KES, 233448, NEAREST_TEN_CENTS);
        assertThat(amount.toString(), is(equalTo("KES 2334.50")));
    }

    @Test
    public void testToStringNearestFiveCents() {
        final Amount amount = new Amount(Currency.KES, 233403, NEAREST_FIVE_CENTS);
        assertThat(amount.toString(), is(equalTo("KES 2334.05")));
    }

    @Test
    public void testMultiplyByDouble() {
        final Amount amount = new Amount(Currency.KES, 450080);
        final Amount expected = new Amount(Currency.KES, 153027);
        final Amount result = amount.multiply(0.34);
        assertThat(result, is(expected));
    }

    @Test
    public void testSpecifiedAccuracy() {
        final Amount amount = new Amount(Currency.KES, 553.566224, SPECIFIED_ACCURACY, 10000);
        final String expected = "KES 553.5662";
        assertThat(amount.toString(), is(expected));
    }
}
