// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;

// Referenced classes of package com.google.zxing:
//            Binarizer, LuminanceSource, NotFoundException

public final class BinaryBitmap
{

    public BinaryBitmap(Binarizer binarizer1)
    {
        if(binarizer1 == null)
        {
            throw new IllegalArgumentException("Binarizer must be non-null.");
        } else
        {
            binarizer = binarizer1;
            matrix = null;
            return;
        }
    }

    public BinaryBitmap crop(int i, int j, int k, int l)
    {
        LuminanceSource luminancesource = binarizer.getLuminanceSource().crop(i, j, k, l);
        return new BinaryBitmap(binarizer.createBinarizer(luminancesource));
    }

    public BitMatrix getBlackMatrix()
        throws NotFoundException
    {
        if(matrix == null)
            matrix = binarizer.getBlackMatrix();
        return matrix;
    }

    public BitArray getBlackRow(int i, BitArray bitarray)
        throws NotFoundException
    {
        return binarizer.getBlackRow(i, bitarray);
    }

    public int getHeight()
    {
        return binarizer.getLuminanceSource().getHeight();
    }

    public int getWidth()
    {
        return binarizer.getLuminanceSource().getWidth();
    }

    public boolean isCropSupported()
    {
        return binarizer.getLuminanceSource().isCropSupported();
    }

    public boolean isRotateSupported()
    {
        return binarizer.getLuminanceSource().isRotateSupported();
    }

    public BinaryBitmap rotateCounterClockwise()
    {
        LuminanceSource luminancesource = binarizer.getLuminanceSource().rotateCounterClockwise();
        return new BinaryBitmap(binarizer.createBinarizer(luminancesource));
    }

    private final Binarizer binarizer;
    private BitMatrix matrix;
}
