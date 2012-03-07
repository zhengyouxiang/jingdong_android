// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing;

import java.util.Hashtable;

// Referenced classes of package com.google.zxing:
//            NotFoundException, ChecksumException, FormatException, BinaryBitmap, 
//            Result

public interface Reader
{

    public abstract Result decode(BinaryBitmap binarybitmap)
        throws NotFoundException, ChecksumException, FormatException;

    public abstract Result decode(BinaryBitmap binarybitmap, Hashtable hashtable)
        throws NotFoundException, ChecksumException, FormatException;

    public abstract void reset();
}
