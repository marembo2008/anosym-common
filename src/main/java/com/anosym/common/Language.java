package com.anosym.common;

import com.google.common.collect.ImmutableList;
import java.util.List;

/**
 *
 * @author mochieng
 */
public enum Language {

    ENGLISH("en"),
    GERMAN("de"),
    SWAHILI("sw"),
    FRENCH("fr");

    private final String isoCode;
    private final List<Country> countries;

    private Language(String isoCode, final Country... countries) {
        this.isoCode = isoCode;
        this.countries = ImmutableList.copyOf(countries);
    }

    public String getIsoCode() {
        return isoCode;
    }

    public static Language fromIsoCode(String isoCode) {
        for (Language l : values()) {
            if (l.isoCode.equalsIgnoreCase(isoCode)) {
                return l;
            }
        }
        throw new IllegalArgumentException("There is no language defined for the specified isocode");
    }

    @Override
    public String toString() {
        return name() + " (" + isoCode + ")";
    }

}
