// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing;

import java.util.Hashtable;

public final class ResultMetadataType
{

    private ResultMetadataType(String s)
    {
        name = s;
        VALUES.put(s, this);
    }

    public static ResultMetadataType valueOf(String s)
    {
        if(s == null || s.length() == 0)
            throw new IllegalArgumentException();
        ResultMetadataType resultmetadatatype = (ResultMetadataType)VALUES.get(s);
        if(resultmetadatatype == null)
            throw new IllegalArgumentException();
        else
            return resultmetadatatype;
    }

    public String getName()
    {
        return name;
    }

    public String toString()
    {
        return name;
    }

    public static final ResultMetadataType BYTE_SEGMENTS = new ResultMetadataType("BYTE_SEGMENTS");
    public static final ResultMetadataType ERROR_CORRECTION_LEVEL = new ResultMetadataType("ERROR_CORRECTION_LEVEL");
    public static final ResultMetadataType ISSUE_NUMBER = new ResultMetadataType("ISSUE_NUMBER");
    public static final ResultMetadataType ORIENTATION = new ResultMetadataType("ORIENTATION");
    public static final ResultMetadataType OTHER = new ResultMetadataType("OTHER");
    public static final ResultMetadataType POSSIBLE_COUNTRY = new ResultMetadataType("POSSIBLE_COUNTRY");
    public static final ResultMetadataType SUGGESTED_PRICE = new ResultMetadataType("SUGGESTED_PRICE");
    private static final Hashtable VALUES = new Hashtable();
    private final String name;

}
