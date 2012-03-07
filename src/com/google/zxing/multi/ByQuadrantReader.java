// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.multi;

import com.google.zxing.*;
import java.util.Hashtable;

public final class ByQuadrantReader
    implements Reader
{

    public ByQuadrantReader(Reader reader)
    {
        _flddelegate = reader;
    }

    public Result decode(BinaryBitmap binarybitmap)
        throws NotFoundException, ChecksumException, FormatException
    {
        return decode(binarybitmap, null);
    }

    public Result decode(BinaryBitmap binarybitmap, Hashtable hashtable)
        throws NotFoundException, ChecksumException, FormatException
    {
        int k;
        int l;
        BinaryBitmap binarybitmap1;
        int i = binarybitmap.getWidth();
        int j = binarybitmap.getHeight();
        k = i / 2;
        l = j / 2;
        binarybitmap1 = binarybitmap.crop(0, 0, k, l);
        Result result4 = _flddelegate.decode(binarybitmap1, hashtable);
        Result result = result4;
_L2:
        return result;
        NotFoundException notfoundexception;
        notfoundexception;
        BinaryBitmap binarybitmap2 = binarybitmap.crop(k, 0, k, l);
        Result result3 = _flddelegate.decode(binarybitmap2, hashtable);
        result = result3;
        continue; /* Loop/switch isn't completed */
        NotFoundException notfoundexception1;
        notfoundexception1;
        BinaryBitmap binarybitmap3 = binarybitmap.crop(0, l, k, l);
        Result result2 = _flddelegate.decode(binarybitmap3, hashtable);
        result = result2;
        continue; /* Loop/switch isn't completed */
        NotFoundException notfoundexception2;
        notfoundexception2;
        BinaryBitmap binarybitmap4 = binarybitmap.crop(k, l, k, l);
        Result result1 = _flddelegate.decode(binarybitmap4, hashtable);
        result = result1;
        continue; /* Loop/switch isn't completed */
        NotFoundException notfoundexception3;
        notfoundexception3;
        BinaryBitmap binarybitmap5 = binarybitmap.crop(k / 2, l / 2, k, l);
        result = _flddelegate.decode(binarybitmap5, hashtable);
        if(true) goto _L2; else goto _L1
_L1:
    }

    public void reset()
    {
        _flddelegate.reset();
    }

    private final Reader _flddelegate;
}
