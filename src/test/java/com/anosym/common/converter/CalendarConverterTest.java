package com.anosym.common.converter;

import java.util.Calendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 *
 * @author marembo
 */
public class CalendarConverterTest {

    public CalendarConverterTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConvertFrom() {
        final String format = "yyyy-MM-dd HH:mm";
        final String value = "2014-03-23 12:00";
        final Calendar expected = Calendar.getInstance();
        expected.setTimeInMillis(0);
        //months start from zero
        expected.set(2014, 02, 23, 12, 0);
        final CalendarConverter cc = new CalendarConverter();
        final Calendar actual = cc.from(value, format);
        assertThat(expected, is(actual));
    }

    @Test
    public void testConvertTo() {
        final String format = "yyyy-MM-dd HH:mm";
        final String value = "2014-03-23 12:00";
        final Calendar expected = Calendar.getInstance();
        expected.setTimeInMillis(0);
        //months start from zero
        expected.set(2014, 02, 23, 12, 0);
        final CalendarConverter cc = new CalendarConverter();
        final String actual = cc.to(expected, format);
        assertThat(value, is(actual));
    }

}
