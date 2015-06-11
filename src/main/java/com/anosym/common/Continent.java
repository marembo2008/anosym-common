package com.anosym.common;

import javax.annotation.Nonnull;

/**
 *
 * @author marembo (marembo2008@gmail.com)
 * @since Jun 11, 2015, 10:54:31 PM
 */
public enum Continent {

    AFRICA("Africa"),
    NORTH_AMERICA("North America"),
    SOUTH_AMERICA("South America"),
    ASIA("Asia"),
    EUROPE("Europe"),
    AUSTRALIA("Australia"),
    ANTARCTICA("Antarctica");

    private final String name;

    private Continent(final String name) {
        this.name = name;
    }

    @Nonnull
    public String getName() {
        return name;
    }

}
