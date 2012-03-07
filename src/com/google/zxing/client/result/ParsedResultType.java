// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result;


public final class ParsedResultType
{

    private ParsedResultType(String s)
    {
        name = s;
    }

    public String toString()
    {
        return name;
    }

    public static final ParsedResultType ADDRESSBOOK = new ParsedResultType("ADDRESSBOOK");
    public static final ParsedResultType ANDROID_INTENT = new ParsedResultType("ANDROID_INTENT");
    public static final ParsedResultType CALENDAR = new ParsedResultType("CALENDAR");
    public static final ParsedResultType EMAIL_ADDRESS = new ParsedResultType("EMAIL_ADDRESS");
    public static final ParsedResultType GEO = new ParsedResultType("GEO");
    public static final ParsedResultType ISBN = new ParsedResultType("ISBN");
    public static final ParsedResultType MOBILETAG_RICH_WEB = new ParsedResultType("MOBILETAG_RICH_WEB");
    public static final ParsedResultType NDEF_SMART_POSTER = new ParsedResultType("NDEF_SMART_POSTER");
    public static final ParsedResultType PRODUCT = new ParsedResultType("PRODUCT");
    public static final ParsedResultType SMS = new ParsedResultType("SMS");
    public static final ParsedResultType TEL = new ParsedResultType("TEL");
    public static final ParsedResultType TEXT = new ParsedResultType("TEXT");
    public static final ParsedResultType URI = new ParsedResultType("URI");
    public static final ParsedResultType WIFI = new ParsedResultType("WIFI");
    private final String name;

}
