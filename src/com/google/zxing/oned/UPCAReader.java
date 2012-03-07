// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.oned;

import com.google.zxing.*;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing.oned:
//            UPCEANReader, EAN13Reader

public final class UPCAReader extends UPCEANReader
{

    public UPCAReader()
    {
    }

    private static Result maybeReturnResult(Result result)
        throws FormatException
    {
        String s = result.getText();
        if(s.charAt(0) == '0')
            return new Result(s.substring(1), null, result.getResultPoints(), BarcodeFormat.UPC_A);
        else
            throw FormatException.getFormatInstance();
    }

    public Result decode(BinaryBitmap binarybitmap)
        throws NotFoundException, FormatException
    {
        return maybeReturnResult(ean13Reader.decode(binarybitmap));
    }

    public Result decode(BinaryBitmap binarybitmap, Hashtable hashtable)
        throws NotFoundException, FormatException
    {
        return maybeReturnResult(ean13Reader.decode(binarybitmap, hashtable));
    }

    protected int decodeMiddle(BitArray bitarray, int ai[], StringBuffer stringbuffer)
        throws NotFoundException
    {
        return ean13Reader.decodeMiddle(bitarray, ai, stringbuffer);
    }

    public Result decodeRow(int i, BitArray bitarray, Hashtable hashtable)
        throws NotFoundException, FormatException, ChecksumException
    {
        return maybeReturnResult(ean13Reader.decodeRow(i, bitarray, hashtable));
    }

    public Result decodeRow(int i, BitArray bitarray, int ai[], Hashtable hashtable)
        throws NotFoundException, FormatException, ChecksumException
    {
        return maybeReturnResult(ean13Reader.decodeRow(i, bitarray, ai, hashtable));
    }

    BarcodeFormat getBarcodeFormat()
    {
        return BarcodeFormat.UPC_A;
    }

    private final UPCEANReader ean13Reader = new EAN13Reader();
}
