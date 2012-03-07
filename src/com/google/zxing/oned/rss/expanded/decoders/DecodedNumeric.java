// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned.rss.expanded.decoders;


// Referenced classes of package com.google.zxing.oned.rss.expanded.decoders:
//            DecodedObject

final class DecodedNumeric extends DecodedObject
{

    DecodedNumeric(int i, int j, int k)
    {
        super(i);
        firstDigit = j;
        secondDigit = k;
        if(firstDigit < 0 || firstDigit > 10)
            throw new IllegalArgumentException("Invalid firstDigit: " + j);
        if(secondDigit < 0 || secondDigit > 10)
            throw new IllegalArgumentException("Invalid secondDigit: " + k);
        else
            return;
    }

    int getFirstDigit()
    {
        return firstDigit;
    }

    int getSecondDigit()
    {
        return secondDigit;
    }

    int getValue()
    {
        return 10 * firstDigit + secondDigit;
    }

    boolean isAnyFNC1()
    {
        boolean flag;
        if(firstDigit == 10 || secondDigit == 10)
            flag = true;
        else
            flag = false;
        return flag;
    }

    boolean isFirstDigitFNC1()
    {
        boolean flag;
        if(firstDigit == 10)
            flag = true;
        else
            flag = false;
        return flag;
    }

    boolean isSecondDigitFNC1()
    {
        boolean flag;
        if(secondDigit == 10)
            flag = true;
        else
            flag = false;
        return flag;
    }

    static final int FNC1 = 10;
    private final int firstDigit;
    private final int secondDigit;
}
