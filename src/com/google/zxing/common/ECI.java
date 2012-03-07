// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.common;


// Referenced classes of package com.google.zxing.common:
//            CharacterSetECI

public abstract class ECI
{

    ECI(int i)
    {
        value = i;
    }

    public static ECI getECIByValue(int i)
    {
        if(i < 0 || i > 0xf423f)
            throw new IllegalArgumentException("Bad ECI value: " + i);
        CharacterSetECI characterseteci;
        if(i < 900)
            characterseteci = CharacterSetECI.getCharacterSetECIByValue(i);
        else
            characterseteci = null;
        return characterseteci;
    }

    public int getValue()
    {
        return value;
    }

    private final int value;
}
