// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;

// Referenced classes of package com.google.zxing:
//            NotFoundException, LuminanceSource

public abstract class Binarizer
{

    protected Binarizer(LuminanceSource luminancesource)
    {
        if(luminancesource == null)
        {
            throw new IllegalArgumentException("Source must be non-null.");
        } else
        {
            source = luminancesource;
            return;
        }
    }

    public abstract Binarizer createBinarizer(LuminanceSource luminancesource);

    public abstract BitMatrix getBlackMatrix()
        throws NotFoundException;

    public abstract BitArray getBlackRow(int i, BitArray bitarray)
        throws NotFoundException;

    public LuminanceSource getLuminanceSource()
    {
        return source;
    }

    private final LuminanceSource source;
}
