package com.anosym.common;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;

/**
 *
 * @author marembo
 */
public enum Country {

    AFGHANISTAN("AF", "AFG", "Afghanistan", "93"), // 0
    ALBANIA("AL", "ALB", "Albania", "355"), // 1
    ALGERIA("DZ", "DZA", "Algeria", "213"), // 2
    AMERICAN_SAMOA("AS", "ASM", "American Samoa", "1 684"), // 3
    ANDORRA("AD", "AND", "Andorra", "376"), // 4
    ANGOLA("AO", "AGO", "Angola", "244"), // 5
    ANGUILLA("AI", "AIA", "Anguilla", "1 264"), // 6
    ANTARCTICA("AQ", "ATA", "Antarctica", "672"), // 7
    ANTIGUA_AND_BARBUDA("AG", "ATG", "Antigua and Barbuda", "1 268"), // 8
    ARGENTINA("AR", "ARG", "Argentina", "54"), // 9
    ARMENIA("AM", "ARM", "Armenia", "374"), // 10
    ARUBA("AW", "ABW", "Aruba", "297"), // 11
    AUSTRALIA("AU", "AUS", "Australia", "61"), // 12
    AUSTRIA("AT", "AUT", "Austria", "43"), // 13
    AZERBAIJAN("AZ", "AZE", "Azerbaijan", "994"), // 14
    BAHAMAS("BS", "BHS", "Bahamas", "1 242"), // 15
    BAHRAIN("BH", "BHR", "Bahrain", "973"), // 16
    BANGLADESH("BD", "BGD", "Bangladesh", "880"), // 17
    BARBADOS("BB", "BRB", "Barbados", "1 246"), // 18
    BELARUS("BY", "BLR", "Belarus", "375"), // 19
    BELGIUM("BE", "BEL", "Belgium", "32"), // 20
    BELIZE("BZ", "BLZ", "Belize", "501"), // 21
    BENIN("BJ", "BEN", "Benin", "229"), // 22
    BERMUDA("BM", "BMU", "Bermuda", "1 441"), // 23
    BHUTAN("BT", "BTN", "Bhutan", "975"), // 24
    BOLIVIA("BO", "BOL", "Bolivia", "591"), // 25
    BOSNIA_AND_HERZEGOVINA("BA", "BIH", "Bosnia and Herzegovina", "387"), // 26
    BOTSWANA("BW", "BWA", "Botswana", "267"), // 27
    BRAZIL("BR", "BRA", "Brazil", "55"), // 28
    BRITISH_INDIAN_OCEAN_TERRITORY("IO", "IOT", "British Indian Ocean Territory", " "), // 29
    BRITISH_VIRGIN_ISLANDS("VG", "VGB", "British Virgin Islands", "1 284"), // 30
    BRUNEI("BN", "BRN", "Brunei", "673"), // 31
    BULGARIA("BG", "BGR", "Bulgaria", "359"), // 32
    BURKINA_FASO("BF", "BFA", "Burkina Faso", "226"), // 33
    REPUBLIC_OF_THE_UNION_OF_MYANMAR("MM", "MMR", "Burma (Republic of the Union of Myanmar)", "95"), // 34
    BURUNDI("BI", "BDI", "Burundi", "257"), // 35
    CAMBODIA("KH", "KHM", "Cambodia", "855"), // 36
    CAMEROON("CM", "CMR", "Cameroon", "237"), // 37
    CANADA("CA", "CAN", "Canada", "1"), // 38
    CAPE_VERDE("CV", "CPV", "Cape Verde", "238"), // 39
    CAYMAN_ISLANDS("KY", "CYM", "Cayman Islands", "1 345"), // 40
    CENTRAL_AFRICAN_REPUBLIC("CF", "CAF", "Central African Republic", "236"), // 41
    CHAD("TD", "TCD", "Chad", "235"), // 42
    CHILE("CL", "CHL", "Chile", "56"), // 43
    CHINA("CN", "CHN", "China", "86"), // 44
    CHRISTMAS_ISLAND("CX", "CXR", "Christmas Island", "61"), // 45
    COCOS_KEELING_ISLANDS("CC", "CCK", "Cocos (Keeling) Islands", "61"), // 46
    COLOMBIA("CO", "COL", "Colombia", "57"), // 47
    COMOROS("KM", "COM", "Comoros", "269"), // 48
    COOK_ISLANDS("CK", "COK", "Cook Islands", "682"), // 49
    COSTA_RICA("CR", "CRC", "Costa Rica", "506"), // 50
    CROATIA("HR", "HRV", "Croatia", "385"), // 51
    CUBA("CU", "CUB", "Cuba", "53"), // 52
    CYPRUS("CY", "CYP", "Cyprus", "357"), // 53
    CZECH_REPUBLIC("CZ", "CZE", "Czech Republic", "420"), // 54
    DEMOCRATIC_REPUBLIC_OF_THE_CONGO("CD", "COD", "Democratic Republic of the Congo", "243"), // 55
    DENMARK("DK", "DNK", "Denmark", "45"), // 56
    DJIBOUTI("DJ", "DJI", "Djibouti", "253"), // 57
    DOMINICA("DM", "DMA", "Dominica", "1 767"), // 58
    DOMINICAN_REPUBLIC("DO", "DOM", "Dominican Republic", "1 809"), // 59
    ECUADOR("EC", "ECU", "Ecuador", "593"), // 60
    EGYPT("EG", "EGY", "Egypt", "20"), // 61
    EL_SALVADOR("SV", "SLV", "El Salvador", "503"), // 62
    EQUATORIAL_GUINEA("GQ", "GNQ", "Equatorial Guinea", "240"), // 63
    ERITREA("ER", "ERI", "Eritrea", "291"), // 64
    ESTONIA("EE", "EST", "Estonia", "372"), // 65
    ETHIOPIA("ET", "ETH", "Ethiopia", "251"), // 66
    FALKLAND_ISLANDS("FK", "FLK", "Falkland Islands", "500"), // 67
    FAROE_ISLANDS("FO", "FRO", "Faroe Islands", "298"), // 68
    FIJI("FJ", "FJI", "Fiji", "679"), // 69
    FINLAND("FI", "FIN", "Finland", "358"), // 70
    FRANCE("FR", "FRA", "France", "33"), // 71
    FRENCH_POLYNESIA("PF", "PYF", "French Polynesia", "689"), // 72
    GABON("GA", "GAB", "Gabon", "241"), // 73
    GAMBIA("GM", "GMB", "Gambia", "220"), // 74
    GAZA_STRIP(" ", " ", "Gaza Strip", "970"), // 75
    GEORGIA("GE", "GEO", "Georgia", "995"), // 76
    GERMANY("DE", "DEU", "Germany", "49"), // 77
    GHANA("GH", "GHA", "Ghana", "233"), // 78
    GIBRALTAR("GI", "GIB", "Gibraltar", "350"), // 79
    GREECE("GR", "GRC", "Greece", "30"), // 80
    GREENLAND("GL", "GRL", "Greenland", "299"), // 81
    GRENADA("GD", "GRD", "Grenada", "1 473"), // 82
    GUAM("GU", "GUM", "Guam", "1 671"), // 83
    GUATEMALA("GT", "GTM", "Guatemala", "502"), // 84
    GUINEA("GN", "GIN", "Guinea", "224"), // 85
    GUINEA_BISSAU("GW", "GNB", "Guinea-Bissau", "245"), // 86
    GUYANA("GY", "GUY", "Guyana", "592"), // 87
    HAITI("HT", "HTI", "Haiti", "509"), // 88
    HOLY_SEE_VATICAN_CITY_("VA", "VAT", "Holy See (Vatican City)", "39"), // 89
    HONDURAS("HN", "HND", "Honduras", "504"), // 90
    HONG_KONG("HK", "HKG", "Hong Kong", "852"), // 91
    HUNGARY("HU", "HUN", "Hungary", "36"), // 92
    ICELAND("IS", "IS", "Iceland", "354"), // 93
    INDIA("IN", "IND", "India", "91"), // 94
    INDONESIA("ID", "IDN", "Indonesia", "62"), // 95
    IRAN("IR", "IRN", "Iran", "98"), // 96
    IRAQ("IQ", "IRQ", "Iraq", "964"), // 97
    IRELAND("IE", "IRL", "Ireland", "353"), // 98
    ISLE_OF_MAN("IM", "IMN", "Isle of Man", "44"), // 99
    ISRAEL("IL", "ISR", "Israel", "972"), // 100
    ITALY("IT", "ITA", "Italy", "39"), // 101
    IVORY_COAST("CI", "CIV", "Ivory Coast", "225"), // 102
    JAMAICA("JM", "JAM", "Jamaica", "1 876"), // 103
    JAPAN("JP", "JPN", "Japan", "81"), // 104
    JERSEY("JE", "JEY", "Jersey", " "), // 105
    JORDAN("JO", "JOR", "Jordan", "962"), // 106
    KAZAKHSTAN("KZ", "KAZ", "Kazakhstan", "7"), // 107
    KENYA("KE", "KEN", "Kenya", "254"), // 108
    KIRIBATI("KI", "KIR", "Kiribati", "686"), // 109
    KOSOVO(" ", " ", "Kosovo", "381"), // 110
    KUWAIT("KW", "KWT", "Kuwait", "965"), // 111
    KYRGYZSTAN("KG", "KGZ", "Kyrgyzstan", "996"), // 112
    LAOS("LA", "LAO", "Laos", "856"), // 113
    LATVIA("LV", "LVA", "Latvia", "371"), // 114
    LEBANON("LB", "LBN", "Lebanon", "961"), // 115
    LESOTHO("LS", "LSO", "Lesotho", "266"), // 116
    LIBERIA("LR", "LBR", "Liberia", "231"), // 117
    LIBYA("LY", "LBY", "Libya", "218"), // 118
    LIECHTENSTEIN("LI", "LIE", "Liechtenstein", "423"), // 119
    LITHUANIA("LT", "LTU", "Lithuania", "370"), // 120
    LUXEMBOURG("LU", "LUX", "Luxembourg", "352"), // 121
    MACAU("MO", "MAC", "Macau", "853"), // 122
    MACEDONIA("MK", "MKD", "Macedonia", "389"), // 123
    MADAGASCAR("MG", "MDG", "Madagascar", "261"), // 124
    MALAWI("MW", "MWI", "Malawi", "265"), // 125
    MALAYSIA("MY", "MYS", "Malaysia", "60"), // 126
    MALDIVES("MV", "MDV", "Maldives", "960"), // 127
    MALI("ML", "MLI", "Mali", "223"), // 128
    MALTA("MT", "MLT", "Malta", "356"), // 129
    MARSHALL_ISLANDS("MH", "MHL", "Marshall Islands", "692"), // 130
    MAURITANIA("MR", "MRT", "Mauritania", "222"), // 131
    MAURITIUS("MU", "MUS", "Mauritius", "230"), // 132
    MAYOTTE("YT", "MYT", "Mayotte", "262"), // 133
    MEXICO("MX", "MEX", "Mexico", "52"), // 134
    MICRONESIA("FM", "FSM", "Micronesia", "691"), // 135
    MOLDOVA("MD", "MDA", "Moldova", "373"), // 136
    MONACO("MC", "MCO", "Monaco", "377"), // 137
    MONGOLIA("MN", "MNG", "Mongolia", "976"), // 138
    MONTENEGRO("ME", "MNE", "Montenegro", "382"), // 139
    MONTSERRAT("MS", "MSR", "Montserrat", "1 664"), // 140
    MOROCCO("MA", "MAR", "Morocco", "212"), // 141
    MOZAMBIQUE("MZ", "MOZ", "Mozambique", "258"), // 142
    NAMIBIA("NA", "NAM", "Namibia", "264"), // 143
    NAURU("NR", "NRU", "Nauru", "674"), // 144
    NEPAL("NP", "NPL", "Nepal", "977"), // 145
    NETHERLANDS("NL", "NLD", "Netherlands", "31"), // 146
    NETHERLANDS_ANTILLES("AN", "ANT", "Netherlands Antilles", "599"), // 147
    NEW_CALEDONIA("NC", "NCL", "New Caledonia", "687"), // 148
    NEW_ZEALAND("NZ", "NZL", "New Zealand", "64"), // 149
    NICARAGUA("NI", "NIC", "Nicaragua", "505"), // 150
    NIGER("NE", "NER", "Niger", "227"), // 151
    NIGERIA("NG", "NGA", "Nigeria", "234"), // 152
    NIUE("NU", "NIU", "Niue", "683"), // 153
    NORFOLK_ISLAND(" ", "NFK", "Norfolk Island", "672"), // 154
    NORTH_KOREA("KP", "PRK", "North Korea", "850"), // 155
    NORTHERN_MARIANA_ISLANDS("MP", "MNP", "Northern Mariana Islands", "1 670"), // 156
    NORWAY("NO", "NOR", "Norway", "47"), // 157
    OMAN("OM", "OMN", "Oman", "968"), // 158
    PAKISTAN("PK", "PAK", "Pakistan", "92"), // 159
    PALAU("PW", "PLW", "Palau", "680"), // 160
    PANAMA("PA", "PAN", "Panama", "507"), // 161
    PAPUA_NEW_GUINEA("PG", "PNG", "Papua New Guinea", "675"), // 162
    PARAGUAY("PY", "PRY", "Paraguay", "595"), // 163
    PERU("PE", "PER", "Peru", "51"), // 164
    PHILIPPINES("PH", "PHL", "Philippines", "63"), // 165
    PITCAIRN_ISLANDS("PN", "PCN", "Pitcairn Islands", "870"), // 166
    POLAND("PL", "POL", "Poland", "48"), // 167
    PORTUGAL("PT", "PRT", "Portugal", "351"), // 168
    PUERTO_RICO("PR", "PRI", "Puerto Rico", "1"), // 169
    QATAR("QA", "QAT", "Qatar", "974"), // 170
    REPUBLIC_OF_THE_CONGO("CG", "COG", "Republic of the Congo", "242"), // 171
    ROMANIA("RO", "ROU", "Romania", "40"), // 172
    RUSSIA("RU", "RUS", "Russia", "7"), // 173
    RWANDA("RW", "RWA", "Rwanda", "250"), // 174
    SAINT_BARTHELEMY("BL", "BLM", "Saint Barthelemy", "590"), // 175
    SAINT_HELENA("SH", "SHN", "Saint Helena", "290"), // 176
    SAINT_KITTS_AND_NEVIS("KN", "KNA", "Saint Kitts and Nevis", "1 869"), // 177
    SAINT_LUCIA("LC", "LCA", "Saint Lucia", "1 758"), // 178
    SAINT_MARTIN("MF", "MAF", "Saint Martin", "1 599"), // 179
    SAINT_PIERRE_AND_MIQUELON("PM", "SPM", "Saint Pierre and Miquelon", "508"), // 180
    SAINT_VINCENT_AND_THE_GRENADINES("VC", "VCT", "Saint Vincent and the Grenadines", "1 784"), // 181
    SAMOA("WS", "WSM", "Samoa", "685"), // 182
    SAN_MARINO("SM", "SMR", "San Marino", "378"), // 183
    SAO_TOME_AND_PRINCIPE("ST", "STP", "Sao Tome and Principe", "239"), // 184
    SAUDI_ARABIA("SA", "SAU", "Saudi Arabia", "966"), // 185
    SENEGAL("SN", "SEN", "Senegal", "221"), // 186
    SERBIA("RS", "SRB", "Serbia", "381"), // 187
    SEYCHELLES("SC", "SYC", "Seychelles", "248"), // 188
    SIERRA_LEONE("SL", "SLE", "Sierra Leone", "232"), // 189
    SINGAPORE("SG", "SGP", "Singapore", "65"), // 190
    SLOVAKIA("SK", "SVK", "Slovakia", "421"), // 191
    SLOVENIA("SI", "SVN", "Slovenia", "386"), // 192
    SOLOMON_ISLANDS("SB", "SLB", "Solomon Islands", "677"), // 193
    SOMALIA("SO", "SOM", "Somalia", "252"), // 194
    SOUTH_AFRICA("ZA", "ZAF", "South Africa", "27"), // 195
    SOUTH_KOREA("KR", "KOR", "South Korea", "82"), // 196
    SOUTH_SUDAN("SS", "SSD", "South Sudan", "211"), // 197
    SPAIN("ES", "ESP", "Spain", "34"), // 198
    SRI_LANKA("LK", "LKA", "Sri Lanka", "94"), // 199
    SUDAN("SD", "SDN", "Sudan", "249"), // 200
    SURINAME("SR", "SUR", "Suriname", "597"), // 201
    SVALBARD("SJ", "SJM", "Svalbard", " "), // 202
    SWAZILAND("SZ", "SWZ", "Swaziland", "268"), // 203
    SWEDEN("SE", "SWE", "Sweden", "46"), // 204
    SWITZERLAND("CH", "CHE", "Switzerland", "41"), // 205
    SYRIA("SY", "SYR", "Syria", "963"), // 206
    TAIWAN("TW", "TWN", "Taiwan", "886"), // 207
    TAJIKISTAN("TJ", "TJK", "Tajikistan", "992"), // 208
    TANZANIA("TZ", "TZA", "Tanzania", "255"), // 209
    THAILAND("TH", "THA", "Thailand", "66"), // 210
    TIMOR_LESTE("TL", "TLS", "Timor-Leste", "670"), // 211
    TOGO("TG", "TGO", "Togo", "228"), // 212
    TOKELAU("TK", "TKL", "Tokelau", "690"), // 213
    TONGA("TO", "TON", "Tonga", "676"), // 214
    TRINIDAD_AND_TOBAGO("TT", "TTO", "Trinidad and Tobago", "1 868"), // 215
    TUNISIA("TN", "TUN", "Tunisia", "216"), // 216
    TURKEY("TR", "TUR", "Turkey", "90"), // 217
    TURKMENISTAN("TM", "TKM", "Turkmenistan", "993"), // 218
    TURKS_AND_CAICOS_ISLANDS("TC", "TCA", "Turks and Caicos Islands", "1 649"), // 219
    TUVALU("TV", "TUV", "Tuvalu", "688"), // 220
    UGANDA("UG", "UGA", "Uganda", "256"), // 221
    UKRAINE("UA", "UKR", "Ukraine", "380"), // 222
    UNITED_ARAB_EMIRATES("AE", "ARE", "United Arab Emirates", "971"), // 223
    UNITED_KINGDOM("GB", "GBR", "United Kingdom", "44"), // 224
    UNITED_STATES("US", "USA", "United States", "1"), // 225
    URUGUAY("UY", "URY", "Uruguay", "598"), // 226
    US_VIRGIN_ISLANDS("VI", "VIR", "US Virgin Islands", "1 340"), // 227
    UZBEKISTAN("UZ", "UZB", "Uzbekistan", "998"), // 228
    VANUATU("VU", "VUT", "Vanuatu", "678"), // 229
    VENEZUELA("VE", "VEN", "Venezuela", "58"), // 230
    VIETNAM("VN", "VNM", "Vietnam", "84"), // 231
    WALLIS_AND_FUTUNA("WF", "WLF", "Wallis and Futuna", "681"), // 232
    WEST_BANK(" ", " ", "West Bank", "970"), // 233
    WESTERN_SAHARA("EH", "ESH", "Western Sahara", " "), // 234
    YEMEN("YE", "YEM", "Yemen", "967"), // 235
    ZAMBIA("ZM", "ZMB", "Zambia", "260"), // 236
    ZIMBABWE("ZW", "ZWE", "Zimbabwe", "263"), // 237
    //Special handling of non-country regions
    EUROPEAN_UNION("EU", "EU", "European Union", "300");

    private final String isoCode;
    private final String isoCode2;
    private final String name;
    private final String dialingCode;
    private final List<Language> languages;

    private Country(@Nonnull final String isoCode,
                    @Nonnull final String isoCode2,
                    @Nonnull final String name,
                    @Nonnull final String dialingCode,
                    @Nonnull final Language... languages) {
        this.isoCode = isoCode;
        this.isoCode2 = isoCode2;
        this.name = name;
        this.dialingCode = dialingCode;
        this.languages = ImmutableList.copyOf(languages);
    }

    @Nonnull
    public String getIsoCode() {
        return this.isoCode;
    }

    @Nonnull
    public String getIsoCode2() {
        return this.isoCode2;
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    @Nonnull
    public String getDialingCode() {
        return this.dialingCode;
    }

    /**
     * Unmodifieble view of this countries spoken languages.
     * <p>
     * @return
     */
    @Nonnull
    public List<Language> getLanguages() {
        return languages;
    }

    @Nonnull
    public static Country fromIsoCode(@Nonnull final String isoCode) {
        checkArgument(!Strings.isNullOrEmpty(isoCode), "The isocode must not be null nor empty");

        for (final Country c : values()) {
            if (c.isoCode.equalsIgnoreCase(isoCode)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unsupported country iso-code: " + isoCode);
    }

    @Nonnull
    public static Country fromIsoCode2(@Nonnull final String isoCode2) {
        checkArgument(!Strings.isNullOrEmpty(isoCode2), "The isocode must not be null nor empty");

        for (final Country c : values()) {
            if (c.isoCode2.equalsIgnoreCase(isoCode2)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Unsupported country iso-code2: " + isoCode2);
    }

    /**
     * Returns sorted sorted, cannot be modified.
     * <p>
     * @return
     */
    public static Set<Country> countries() {
        @SuppressWarnings("SetReplaceableByEnumSet")
        SortedSet<Country> countries = new TreeSet<>(new Comparator<Country>() {

            @Override
            public int compare(Country o1, Country o2) {
                return o1.name().compareToIgnoreCase(o2.name());
            }
        });
        countries.addAll(Arrays.asList(values()));
        return Collections.unmodifiableSortedSet(countries);
    }

    @Override
    public String toString() {
        return name;
    }

}
