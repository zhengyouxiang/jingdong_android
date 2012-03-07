// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing;

import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing:
//            WriterException, BarcodeFormat

public interface Writer
{

    public abstract BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
        throws WriterException;

    public abstract BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Hashtable hashtable)
        throws WriterException;
}
