// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.multi;

import com.google.zxing.*;
import java.util.Hashtable;

public interface MultipleBarcodeReader
{

    public abstract Result[] decodeMultiple(BinaryBitmap binarybitmap)
        throws NotFoundException;

    public abstract Result[] decodeMultiple(BinaryBitmap binarybitmap, Hashtable hashtable)
        throws NotFoundException;
}
