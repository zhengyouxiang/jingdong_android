// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing;


public abstract class LuminanceSource
{

    protected LuminanceSource(int i, int j)
    {
        width = i;
        height = j;
    }

    public LuminanceSource crop(int i, int j, int k, int l)
    {
        throw new RuntimeException("This luminance source does not support cropping.");
    }

    public final int getHeight()
    {
        return height;
    }

    public abstract byte[] getMatrix();

    public abstract byte[] getRow(int i, byte abyte0[]);

    public final int getWidth()
    {
        return width;
    }

    public boolean isCropSupported()
    {
        return false;
    }

    public boolean isRotateSupported()
    {
        return false;
    }

    public LuminanceSource rotateCounterClockwise()
    {
        throw new RuntimeException("This luminance source does not support rotation.");
    }

    private final int height;
    private final int width;
}
