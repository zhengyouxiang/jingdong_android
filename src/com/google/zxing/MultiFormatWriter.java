// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.oned.Code39Writer;
import com.google.zxing.oned.EAN13Writer;
import com.google.zxing.oned.EAN8Writer;
import com.google.zxing.oned.ITFWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.Hashtable;

// Referenced classes of package com.google.zxing:
//            Writer, WriterException, BarcodeFormat

public final class MultiFormatWriter
    implements Writer
{

    public MultiFormatWriter()
    {
    }

    public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j)
        throws WriterException
    {
        return encode(s, barcodeformat, i, j, null);
    }

    public BitMatrix encode(String s, BarcodeFormat barcodeformat, int i, int j, Hashtable hashtable)
        throws WriterException
    {
        Object obj;
        if(barcodeformat == BarcodeFormat.EAN_8)
            obj = new EAN8Writer();
        else
        if(barcodeformat == BarcodeFormat.EAN_13)
            obj = new EAN13Writer();
        else
        if(barcodeformat == BarcodeFormat.QR_CODE)
            obj = new QRCodeWriter();
        else
        if(barcodeformat == BarcodeFormat.CODE_39)
            obj = new Code39Writer();
        else
        if(barcodeformat == BarcodeFormat.CODE_128)
            obj = new Code128Writer();
        else
        if(barcodeformat == BarcodeFormat.ITF)
            obj = new ITFWriter();
        else
            throw new IllegalArgumentException("No encoder available for format " + barcodeformat);
        return ((Writer) (obj)).encode(s, barcodeformat, i, j, hashtable);
    }
}
