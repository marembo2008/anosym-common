package com.anosym.common;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 *
 * @author marembo
 */
public class PrintableTest {

    @Test
    public void testEqual() {
        final PrintableExample example1 = new PrintableExample("example", 3333);
        final PrintableExample example2 = new PrintableExample("example", 3333);
        assertThat(example1, is(example2));
        assertThat(example1.hashCode(), is(example2.hashCode()));
    }

    private static class PrintableExample extends Printable {

        private final String value;
        private final int index;

        public PrintableExample(String value, int index) {
            this.value = value;
            this.index = index;
        }

    }
}
