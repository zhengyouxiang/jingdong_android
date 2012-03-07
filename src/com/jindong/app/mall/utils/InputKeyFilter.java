// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.jindong.app.mall.utils;

import android.text.method.NumberKeyListener;

public class InputKeyFilter extends NumberKeyListener
{

    public InputKeyFilter()
    {
    }

    protected char[] getAcceptedChars()
    {
        char ac[] = new char[38];
        ac[0] = 'q';
        ac[1] = 'w';
        ac[2] = 'e';
        ac[3] = 'r';
        ac[4] = 't';
        ac[5] = 'y';
        ac[6] = 'u';
        ac[7] = 'i';
        ac[8] = 'o';
        ac[9] = 'p';
        ac[10] = 'l';
        ac[11] = 'k';
        ac[12] = 'j';
        ac[13] = 'h';
        ac[14] = 'g';
        ac[15] = 'f';
        ac[16] = 'd';
        ac[17] = 's';
        ac[18] = 'a';
        ac[19] = 'z';
        ac[20] = 'x';
        ac[21] = 'c';
        ac[22] = 'v';
        ac[23] = 'b';
        ac[24] = 'n';
        ac[25] = 'm';
        ac[26] = '1';
        ac[27] = '2';
        ac[28] = '3';
        ac[29] = '4';
        ac[30] = '5';
        ac[31] = '6';
        ac[32] = '7';
        ac[33] = '8';
        ac[34] = '9';
        ac[35] = '0';
        ac[36] = '_';
        ac[37] = '\n';
        return ac;
    }

    public int getInputType()
    {
        return 0;
    }
}
