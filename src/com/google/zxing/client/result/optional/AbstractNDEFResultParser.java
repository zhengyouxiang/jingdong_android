// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.client.result.optional;

import com.google.zxing.client.result.ResultParser;
import java.io.UnsupportedEncodingException;

abstract class AbstractNDEFResultParser extends ResultParser
{

    AbstractNDEFResultParser()
    {
    }

    static String bytesToString(byte abyte0[], int i, int j, String s)
    {
        String s1;
        try
        {
            s1 = new String(abyte0, i, j, s);
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new RuntimeException("Platform does not support required encoding: " + unsupportedencodingexception);
        }
        return s1;
    }
}
