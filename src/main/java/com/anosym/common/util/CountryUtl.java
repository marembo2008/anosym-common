package com.anosym.common.util;

import com.anosym.common.Currency;

/**
 *
 * @author marembo
 */
public class CountryUtl {

    public static void main(String[] args) {
        for (Currency c : Currency.values()) {
            System.out.println(c.ordinal() + ":\t" + c.name());
        }
    }
}
