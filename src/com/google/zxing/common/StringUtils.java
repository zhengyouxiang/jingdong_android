// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;

import com.google.zxing.DecodeHintType;
import java.util.Hashtable;

public final class StringUtils
{

    private StringUtils()
    {
    }

    public static String guessEncoding(byte abyte0[], Hashtable hashtable)
    {
        if(hashtable == null) goto _L2; else goto _L1
_L1:
        String s1 = (String)hashtable.get(DecodeHintType.CHARACTER_SET);
        if(s1 == null) goto _L2; else goto _L3
_L3:
        String s = s1;
_L4:
        return s;
_L2:
label0:
        {
            if(abyte0.length <= 3 || abyte0[0] != -17 || abyte0[1] != -69 || abyte0[2] != -65)
                break label0;
            s = "UTF8";
        }
          goto _L4
        int i;
        boolean flag;
        int j;
        boolean flag1;
        boolean flag2;
        int k;
        int l;
        boolean flag3;
        int i1;
        boolean flag4;
        boolean flag5;
        i = abyte0.length;
        flag = true;
        j = 0;
        flag1 = true;
        flag2 = false;
        k = 0;
        l = 0;
        flag3 = false;
        i1 = 0;
        flag4 = false;
        flag5 = true;
_L9:
        if(j >= i || !flag && !flag5 && !flag1) goto _L6; else goto _L5
_L5:
        int j1 = 0xff & abyte0[j];
        if(j1 < 128 || j1 > 191) goto _L8; else goto _L7
_L7:
        if(i1 > 0)
            i1--;
_L11:
        int k1;
        if((j1 == 194 || j1 == 195) && j < i - 1)
        {
            int j2 = 0xff & abyte0[j + 1];
            if(j2 <= 191 && (j1 == 194 && j2 >= 160 || j1 == 195 && j2 >= 128))
                flag3 = true;
        }
        if(j1 >= 127 && j1 <= 159)
            flag = false;
        if(j1 >= 161 && j1 <= 223 && !flag4)
            l++;
        if(!flag4 && (j1 >= 240 && j1 <= 255 || j1 == 128 || j1 == 160))
            flag5 = false;
        int l1;
        if(j1 >= 129 && j1 <= 159 || j1 >= 224 && j1 <= 239)
        {
            if(flag4)
            {
                flag4 = false;
            } else
            {
                flag4 = true;
                if(j >= abyte0.length - 1)
                {
                    flag5 = false;
                } else
                {
                    int i2 = 0xff & abyte0[j + 1];
                    if(i2 < 64 || i2 > 252)
                        flag5 = false;
                    else
                        k++;
                }
            }
        } else
        {
            flag4 = false;
        }
        j++;
          goto _L9
_L8:
        if(i1 > 0)
            flag1 = false;
        if(j1 < 192 || j1 > 253) goto _L11; else goto _L10
_L10:
        flag2 = true;
        k1 = i1;
        for(l1 = j1; (l1 & 0x40) != 0; l1 <<= 1)
            k1++;

        break MISSING_BLOCK_LABEL_576;
_L6:
        boolean flag6;
        if(i1 > 0)
            flag6 = false;
        else
            flag6 = flag1;
        if(flag5 && ASSUME_SHIFT_JIS)
            s = "SJIS";
        else
        if(flag6 && flag2)
            s = "UTF8";
        else
        if(flag5 && (k >= 3 || l * 20 > i))
            s = "SJIS";
        else
        if(!flag3 && flag)
            s = "ISO8859_1";
        else
            s = PLATFORM_DEFAULT_ENCODING;
          goto _L4
        i1 = k1;
          goto _L11
    }

    private static final boolean ASSUME_SHIFT_JIS = false;
    private static final String EUC_JP = "EUC_JP";
    private static final String ISO88591 = "ISO8859_1";
    private static final String PLATFORM_DEFAULT_ENCODING = System.getProperty("file.encoding");
    public static final String SHIFT_JIS = "SJIS";
    private static final String UTF8 = "UTF8";

    static 
    {
        boolean flag;
        if("SJIS".equalsIgnoreCase(PLATFORM_DEFAULT_ENCODING) || "EUC_JP".equalsIgnoreCase(PLATFORM_DEFAULT_ENCODING))
            flag = true;
        else
            flag = false;
        ASSUME_SHIFT_JIS = flag;
    }
}
