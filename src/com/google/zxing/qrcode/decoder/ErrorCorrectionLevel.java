// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode.decoder;


public final class ErrorCorrectionLevel
{

    private ErrorCorrectionLevel(int i, int j, String s)
    {
        ordinal = i;
        bits = j;
        name = s;
    }

    public static ErrorCorrectionLevel forBits(int i)
    {
        if(i < 0 || i >= FOR_BITS.length)
            throw new IllegalArgumentException();
        else
            return FOR_BITS[i];
    }

    public int getBits()
    {
        return bits;
    }

    public String getName()
    {
        return name;
    }

    public int ordinal()
    {
        return ordinal;
    }

    public String toString()
    {
        return name;
    }

    private static final ErrorCorrectionLevel FOR_BITS[];
    public static final ErrorCorrectionLevel H;
    public static final ErrorCorrectionLevel L;
    public static final ErrorCorrectionLevel M;
    public static final ErrorCorrectionLevel Q;
    private final int bits;
    private final String name;
    private final int ordinal;

    static 
    {
        L = new ErrorCorrectionLevel(0, 1, "L");
        M = new ErrorCorrectionLevel(1, 0, "M");
        Q = new ErrorCorrectionLevel(2, 3, "Q");
        H = new ErrorCorrectionLevel(3, 2, "H");
        ErrorCorrectionLevel aerrorcorrectionlevel[] = new ErrorCorrectionLevel[4];
        aerrorcorrectionlevel[0] = M;
        aerrorcorrectionlevel[1] = L;
        aerrorcorrectionlevel[2] = H;
        aerrorcorrectionlevel[3] = Q;
        FOR_BITS = aerrorcorrectionlevel;
    }
}
