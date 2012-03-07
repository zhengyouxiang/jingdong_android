// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.lib.zxing.client.android.wifi;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class NetworkUtil
{

    private NetworkUtil()
    {
    }

    static String convertToQuotedString(String s)
    {
        String s1;
        if(s == null)
            s1 = null;
        else
        if(TextUtils.isEmpty(s))
        {
            s1 = "";
        } else
        {
            int i = s.length() - 1;
            if(i < 0 || s.charAt(0) == '"' && s.charAt(i) == '"')
                s1 = s;
            else
                s1 = (new StringBuilder(String.valueOf('"'))).append(s).append('"').toString();
        }
        return s1;
    }

    static boolean isHexWepKey(CharSequence charsequence)
    {
        boolean flag;
        if(charsequence == null)
        {
            flag = false;
        } else
        {
            int i = charsequence.length();
            if((i == 10 || i == 26 || i == 58) && HEX_DIGITS.matcher(charsequence).matches())
                flag = true;
            else
                flag = false;
        }
        return flag;
    }

    private static final Pattern HEX_DIGITS = Pattern.compile("[0-9A-Fa-f]+");

}
