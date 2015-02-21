package com.anosym.common;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author marembo
 */
public enum Currency {

    AED("UAE Dirham", "784", Country.UNITED_ARAB_EMIRATES),
    AFN("Afghani", "؋", Country.AFGHANISTAN),
    ALL("Lek", "Lek", Country.ALBANIA),
    AMD("Armenian Dram", "51", Country.ARMENIA),
    ANG("Netherlands Antillan Guilder", "ƒ", Country.NETHERLANDS_ANTILLES),
    AOA("Kwanza", "973", Country.ANGOLA),
    ARS("Argentine Peso", "$", Country.ARGENTINA),
    AUD("Australian Dollar", "$", Country.AUSTRALIA, Country.KIRIBATI, Country.NAURU, Country.TUVALU),
    AWG("Aruban Guilder", "ƒ", Country.ARUBA),
    AZN("Azerbaijan Manat;", "ман", Country.AZERBAIJAN),
    BAM("Convertible Marks", "KM", Country.BOSNIA_AND_HERZEGOVINA),
    BBD("Barbados Dollar", "$", Country.BARBADOS),
    BDT("Taka", "50", Country.BANGLADESH),
    BGN("Bulgarian Lev", "лв", Country.BULGARIA),
    BHD("Bahraini Dinar", "48", Country.BAHRAIN),
    BIF("Burundi Franc", "108", Country.BURUNDI),
    BMD("Bermudian Dollar", "$"),
    BND("Brunei Dollar", "$", Country.BRUNEI),
    BOB("Boliviano", "$b", Country.BOLIVIA),
    BRL("Brazilian Real", "R$", Country.BRAZIL),
    BSD("Bahamian Dollar", "$", Country.BAHAMAS),
    BTN("Ngultrum", "064", Country.BHUTAN),
    BWP("Pula", "P", Country.BOTSWANA),
    BYR("Belarussian Ruble", "p.    ", Country.BELARUS),
    BZD("Belize Dollar", "BZ$", Country.BELIZE),
    CAD("Canadian Dollar", "$", Country.CANADA),
    CDF("Congolese Franc", "---", Country.DEMOCRATIC_REPUBLIC_OF_THE_CONGO),
    CHF("Swiss Franc", "CHF", Country.SWITZERLAND, Country.LIECHTENSTEIN),
    CLP("Chilean Peso", "$", Country.CHILE),
    CNY("Yuan Renminbi", "¥", Country.CHINA),
    COP("Colombian Peso", "$", Country.COLOMBIA),
    CRC("Costa Rican Colon", "₡", Country.COSTA_RICA),
    CSD("Serbian Dinar", "891"),
    CUC("Cuban Convertible Peso", "192", Country.CUBA),
    CUP("Cuban Peso", "₱", Country.CUBA),
    CVE("Cape Verde Escudo", "132", Country.CAPE_VERDE),
    CZK("Czech Koruna", "Kč", Country.CZECH_REPUBLIC),
    DJF("Djibouti Franc", "262", Country.DJIBOUTI),
    DKK("Danish Krone", "kr", Country.DENMARK),
    DOP("Dominican Peso", "RD$", Country.DOMINICAN_REPUBLIC),
    DZD("Algerian Dinar", "12", Country.ALGERIA),
    EEK("Kroon", "kr", Country.ESTONIA),
    EGP("Egyptian Pound", "£", Country.EGYPT),
    ERN("Nakfa", "232", Country.ERITREA),
    ETB("Ethiopian Birr", "230", Country.ETHIOPIA),
    EUR("Euro", "€",
        Country.ANDORRA, Country.AUSTRIA, Country.BELGIUM, Country.CYPRUS, Country.ESTONIA, Country.FINLAND,
        Country.FRANCE, Country.GERMANY, Country.GREECE, Country.IRELAND, Country.ITALY, Country.KOSOVO, Country.LATVIA,
        Country.LITHUANIA, Country.LUXEMBOURG, Country.MALTA, Country.MONACO, Country.MONTENEGRO, Country.NETHERLANDS,
        Country.PORTUGAL, Country.SAN_MARINO, Country.SLOVAKIA, Country.SLOVENIA, Country.SPAIN, Country.HOLY_SEE_VATICAN_CITY_,
        //Even though not a country, but a region.
        Country.EUROPEAN_UNION),
    FJD("Fiji Dollar", "$", Country.FIJI),
    FKP("Falkland Islands pound", "£", Country.FALKLAND_ISLANDS),
    GBP("Pound Sterling", "£", Country.UNITED_KINGDOM),
    GEL("Lari", "981", Country.GEORGIA),
    GHS("Cedi", "GH₵", Country.GHANA),
    GIP("Gibraltar Pound", "£", Country.GIBRALTAR),
    GMD("Dalasi", "270", Country.GAMBIA),
    GNF("Guinea Franc", "324", Country.GUINEA),
    GTQ("Quetzal", "Q", Country.GUATEMALA),
    GWP("Guinea-Bissau Peso", "624"),
    GYD("Guyana Dollar", "$", Country.GUYANA),
    HKD("Hong Kong Dollar", "$", Country.HONG_KONG),
    HNL("Lempira", "L", Country.HONDURAS),
    HRK("Croatian kuna", "kn", Country.CROATIA),
    HTG("Gourde", "332", Country.HAITI),
    HUF("Forint", "Ft", Country.HUNGARY),
    IDR("Rupiah", "Rp", Country.INDONESIA),
    ILS("New Israeli Sheqel", "₪", Country.ISRAEL),
    INR("Indian Rupee", "₹", Country.INDIA),
    IQD("Iraqi Dinar", "368", Country.IRAQ),
    IRR("Iranian Rial", "﷼", Country.IRAN),
    ISK("Iceland Krona", "kr", Country.ICELAND),
    JMD("Jamaican Dollar", "J$", Country.JAMAICA),
    JOD("Jordanian Dinar", "400", Country.JORDAN),
    JPY("Yen", "¥", Country.JAPAN),
    KES("Kenyan Shilling", "KSh", Country.KENYA),
    KGS("Som", "лв", Country.KYRGYZSTAN),
    KHR("Riel", "៛", Country.CAMBODIA),
    KMF("Comoro Franc", "174", Country.COMOROS),
    KPW("North Korean won", "₩", Country.NORTH_KOREA),
    KRW("South Korean won", "₩", Country.SOUTH_KOREA),
    KWD("Kuwaiti Dinar", "414", Country.KUWAIT),
    KYD("Cayman Islands Dollar", "$", Country.CAYMAN_ISLANDS),
    KZT("Tenge", "лв", Country.KAZAKHSTAN),
    LAK("Laos kip", "₭", Country.LAOS),
    LBP("Lebanese Pound", "£", Country.LEBANON),
    LKR("Sri Lanka Rupee", "₨", Country.SRI_LANKA),
    LRD("Liberian Dollar", "$", Country.LIBERIA),
    LSL("Lesotho Loti", "--", Country.LESOTHO),
    LTL("Lithuanian Litas", "Lt", Country.LITHUANIA),
    LVL("Latvian Lats", "Ls", Country.LATVIA),
    MAD("Moroccan Dirham", "504", Country.MOROCCO),
    MDL("Moldovan leu", "---", Country.MOLDOVA),
    MGA("Malagasy ariary", "450", Country.MADAGASCAR),
    MKD("Macedonian denar", "ден", Country.MACEDONIA),
    MMK("Myanma kyat", "---", Country.REPUBLIC_OF_THE_UNION_OF_MYANMAR),
    MNT("Tugrik (Tugrug)", "₮", Country.MONGOLIA),
    MOP("Pataca", "446", Country.MACAU),
    MRO("Ouguiya", "478", Country.MAURITANIA),
    MTL("Maltese Lira", "470"),
    MUR("Mauritius Rupee", "₨", Country.MAURITIUS),
    MVR("Rufiyaa", "462", Country.MALDIVES),
    MWK("Kwacha", "454", Country.MALAWI),
    MXN("Mexican Peso", "$", Country.MEXICO),
    MYR("Malaysian Ringgit", "RM", Country.MALAYSIA),
    MZM("Metical", "MT", Country.MOZAMBIQUE),
    NAD("Namibian dollar", "$", Country.NAMIBIA),
    NGN("Naira", "₦", Country.NIGERIA),
    NIO("Cordoba Oro", "C$", Country.NICARAGUA),
    NOK("Norwegian Krone", "kr", Country.NORWAY),
    NPR("Nepalese Rupee", "₨", Country.NEPAL),
    NZD("New Zealand Dollar", "$", Country.NEW_ZEALAND),
    OMR("Rial Omani", "﷼", Country.OMAN),
    PAB("Balboa", "B/.", Country.PANAMA),
    PEN("Nuevo Sol", "S/.", Country.PERU),
    PGK("Kina", "598", Country.PAPUA_NEW_GUINEA),
    PHP("Philippine Peso", "₱", Country.PHILIPPINES),
    PKR("Pakistan Rupee", "₨", Country.PAKISTAN),
    PLN("Zloty", "zł", Country.POLAND),
    PYG("Guarani", "Gs", Country.PARAGUAY),
    QAR("Qatari Rial", "﷼", Country.QATAR),
    RON("Leu", "lei", Country.ROMANIA),
    RSD("Serbian dinar", "Дин.", Country.SERBIA),
    RUB("Russian ruble", "руб", Country.RUSSIA),
    RWF("Rwanda Franc", "646", Country.RWANDA),
    SAR("Saudi Riyal", "﷼", Country.SAUDI_ARABIA),
    SBD("Solomon Islands Dollar", "$", Country.SOLOMON_ISLANDS),
    SCR("Seychelles Rupee", "₨", Country.SEYCHELLES),
    SDG("Sudanese pound ", "---", Country.SUDAN),
    SEK("Swedish Krona", "kr", Country.SWEDEN),
    SGD("Singapore Dollar", "$", Country.SINGAPORE),
    SHP("Saint Helena Pound", "£", Country.SAINT_HELENA),
    SLL("Leone", "694", Country.SIERRA_LEONE),
    SKK("Slovak koruna", "---", Country.SLOVAKIA),
    SOS("Somali Shilling", "S", Country.SOMALIA),
    SRD("Surinamese dollar", "$", Country.SURINAME),
    SRG("Suriname Guilder", "740"),
    SSP("South Sudanese pound", "---", Country.SOUTH_SUDAN),
    STD("Dobra", "678", Country.SAO_TOME_AND_PRINCIPE),
    SVC("El Salvador Colon", "$"),
    SYP("Syrian pound", "£", Country.SYRIA),
    SZL("Lilangeni", "748", Country.SWAZILAND),
    THB("Baht", "฿", Country.THAILAND),
    TJS("Somoni", "972", Country.TAJIKISTAN),
    TMM("Manat", "795", Country.TURKMENISTAN),
    TND("Tunisian Dinar", "788", Country.TUNISIA),
    TOP("Pa¥anga", "776", Country.TONGA),
    TRY("Yeni Türk Liras (YTL)", "\u20BA", Country.TURKEY),
    TTD("Trinidad and Tobago Dollar", "TT$", Country.TRINIDAD_AND_TOBAGO),
    TVD("Tuvalu dollar", "$", Country.TUVALU),
    TWD("New Taiwan dollar", "NT$", Country.TAIWAN),
    TZS("Tanzanian Shilling", "834", Country.TANZANIA),
    UAH("Hryvnia", "₴", Country.UKRAINE),
    UGX("Uganda Shilling", "800", Country.UGANDA),
    USD("US Dollar", "$", Country.UNITED_STATES, Country.ZIMBABWE, Country.TIMOR_LESTE, Country.ECUADOR,
        Country.EL_SALVADOR, Country.MICRONESIA, Country.BRITISH_VIRGIN_ISLANDS, Country.PALAU, Country.PUERTO_RICO),
    UYU("Peso Uruguayo", "$U", Country.URUGUAY),
    UZS("Uzbekistan Sum", "лв", Country.UZBEKISTAN),
    VEF("Bolívar fuerte venezolano", "Bs", Country.VENEZUELA),
    VND("Viatmese dong", "₫", Country.VIETNAM),
    VUV("Vatu", "548", Country.VANUATU),
    WST("Tala", "882", Country.SAMOA),
    XAF("CFA Franc BEAC (Central African CFA franc)", "950", Country.CAMEROON, Country.CENTRAL_AFRICAN_REPUBLIC,
        Country.CHAD, Country.REPUBLIC_OF_THE_CONGO, Country.EQUATORIAL_GUINEA, Country.GABON),
    XCD("East Carribbean Dollar", "$", Country.ANGUILLA, Country.ANTIGUA_AND_BARBUDA, Country.DOMINICA,
        Country.GRENADA, Country.MONTSERRAT, Country.SAINT_KITTS_AND_NEVIS, Country.SAINT_LUCIA,
        Country.SAINT_VINCENT_AND_THE_GRENADINES),
    XDR("Special Drawinf Rights (International Monetary Fund)", "--"),
    XOF("CFA Franc BCEAO", "952", Country.BENIN, Country.BURKINA_FASO, Country.IVORY_COAST, Country.GUINEA_BISSAU,
        Country.MALI, Country.NIGER, Country.SENEGAL, Country.TOGO),
    XPF("CFP Franc", "953", Country.FRENCH_POLYNESIA, Country.NEW_CALEDONIA, Country.WALLIS_AND_FUTUNA),
    YER("Yemeni Rial", "﷼", Country.YEMEN),
    ZAR("Rand", "S", Country.SOUTH_AFRICA),
    ZMK("Kwacha", "894", Country.ZAMBIA);

    private final String description;
    private final String symbol;
    //Countries whose official currency is this.
    private final List<Country> countries;

    private Currency(final String description, final String symbol) {
        this.description = description;
        this.symbol = symbol;
        this.countries = ImmutableList.of();
    }

    private Currency(String description, String symbol, Country... countries) {
        this.description = description;
        this.symbol = symbol;
        this.countries = ImmutableList.copyOf(countries);
    }

    @Nonnull
    public List<Country> getCountries() {
        return ImmutableList.copyOf(countries);
    }

    public String getDescription() {
        return this.description;
    }

    public String getSymbol() {
        //Temporaly until we refactor all.
        final String regex = "(\\d+)|(-+)";
        return Pattern.matches(regex, this.symbol) ? name() : this.symbol;
    }

    /**
     * A country can have more than one currency as its official currency (schweis for example).
     *
     * The following method returns the first of those.
     *
     * @param country
     * @return
     */
    @Nonnull
    public static Optional<Currency> fromCountry(@Nonnull Country country) {
        checkNotNull(country, "The country must not be null");

        for (Currency currency : values()) {
            if (currency.countries.contains(country)) {
                return Optional.of(currency);
            }
        }
        return Optional.absent();
    }

    /**
     * A country can have more than one currency as its official currency (schweis for example).
     *
     * @param country
     * @return
     */
    @Nonnull
    public static List<Currency> countryCurrencies(@Nonnull Country country) {
        checkNotNull(country, "The country must not be null");

        ImmutableList.Builder<Currency> builder = ImmutableList.builder();
        for (Currency currency : values()) {
            if (currency.countries.contains(country)) {
                builder.add(currency);
            }
        }
        return builder.build();
    }

    @Nonnull
    public static Currency fromIsoCode(@Nonnull final String isoCode) {
        checkNotNull(isoCode, "Isocode for the currency must not be null");

        return Currency.valueOf(isoCode.trim().toUpperCase());
    }

}
