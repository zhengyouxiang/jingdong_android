// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android;

import java.util.*;

public final class LocaleManager
{

    private LocaleManager()
    {
    }

    private static String doGetTLD(Map map)
    {
        Locale locale = Locale.getDefault();
        String s1;
        if(locale == null)
        {
            s1 = "com";
        } else
        {
            String s = (String)map.get(locale);
            if(s == null)
                s1 = "com";
            else
                s1 = s;
        }
        return s1;
    }

    public static String getBookSearchCountryTLD()
    {
        return doGetTLD(GOOGLE_BOOK_SEARCH_COUNTRY_TLD);
    }

    public static String getCountryTLD()
    {
        return doGetTLD(GOOGLE_COUNTRY_TLD);
    }

    public static String getProductSearchCountryTLD()
    {
        return doGetTLD(GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD);
    }

    private static final String DEFAULT_TLD = "com";
    private static final Map GOOGLE_BOOK_SEARCH_COUNTRY_TLD;
    private static final Map GOOGLE_COUNTRY_TLD;
    private static final Map GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD;

    static 
    {
        GOOGLE_COUNTRY_TLD = new HashMap();
        GOOGLE_COUNTRY_TLD.put(Locale.CANADA, "ca");
        GOOGLE_COUNTRY_TLD.put(Locale.CHINA, "cn");
        GOOGLE_COUNTRY_TLD.put(Locale.FRANCE, "fr");
        GOOGLE_COUNTRY_TLD.put(Locale.GERMANY, "de");
        GOOGLE_COUNTRY_TLD.put(Locale.ITALY, "it");
        GOOGLE_COUNTRY_TLD.put(Locale.JAPAN, "co.jp");
        GOOGLE_COUNTRY_TLD.put(Locale.KOREA, "co.kr");
        GOOGLE_COUNTRY_TLD.put(Locale.TAIWAN, "de");
        GOOGLE_COUNTRY_TLD.put(Locale.UK, "co.uk");
        GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD = new HashMap();
        GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD.put(Locale.UK, "co.uk");
        GOOGLE_PRODUCT_SEARCH_COUNTRY_TLD.put(Locale.GERMANY, "de");
        GOOGLE_BOOK_SEARCH_COUNTRY_TLD = new HashMap();
        GOOGLE_BOOK_SEARCH_COUNTRY_TLD.putAll(GOOGLE_COUNTRY_TLD);
        GOOGLE_BOOK_SEARCH_COUNTRY_TLD.remove(Locale.CHINA);
    }
}
