// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Encrypt
{

    public Md5Encrypt()
    {
    }

    public static char[] encodeHex(byte abyte0[])
    {
        int i = abyte0.length;
        char ac[] = new char[i << 1];
        int j = 0;
        int k = 0;
        do
        {
            if(j >= i)
                return ac;
            int l = k + 1;
            ac[k] = DIGITS[(0xf0 & abyte0[j]) >>> 4];
            k = l + 1;
            ac[l] = DIGITS[0xf & abyte0[j]];
            j++;
        } while(true);
    }

    public static String md5(String s)
    {
        MessageDigest messagedigest;
        try
        {
            messagedigest = MessageDigest.getInstance("MD5");
        }
        catch(NoSuchAlgorithmException nosuchalgorithmexception)
        {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        }
        try
        {
            messagedigest.update(s.getBytes("utf-8"));
        }
        catch(UnsupportedEncodingException unsupportedencodingexception)
        {
            throw new IllegalStateException("System doesn't support your  EncodingException.");
        }
        return new String(encodeHex(messagedigest.digest()));
    }

    private static final char DIGITS[];

    static 
    {
        char ac[] = new char[16];
        ac[0] = '0';
        ac[1] = '1';
        ac[2] = '2';
        ac[3] = '3';
        ac[4] = '4';
        ac[5] = '5';
        ac[6] = '6';
        ac[7] = '7';
        ac[8] = '8';
        ac[9] = '9';
        ac[10] = 'a';
        ac[11] = 'b';
        ac[12] = 'c';
        ac[13] = 'd';
        ac[14] = 'e';
        ac[15] = 'f';
        DIGITS = ac;
    }
}
