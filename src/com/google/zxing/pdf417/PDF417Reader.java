// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.pdf417;

import com.google.zxing.*;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.pdf417.decoder.Decoder;
import com.google.zxing.pdf417.detector.Detector;
import com.google.zxing.qrcode.QRCodeReader;
import java.util.Hashtable;

public final class PDF417Reader
    implements Reader
{

    public PDF417Reader()
    {
    }

    public Result decode(BinaryBitmap binarybitmap)
        throws NotFoundException, FormatException
    {
        return decode(binarybitmap, null);
    }

    public Result decode(BinaryBitmap binarybitmap, Hashtable hashtable)
        throws NotFoundException, FormatException
    {
        DecoderResult decoderresult;
        ResultPoint aresultpoint[];
        if(hashtable != null && hashtable.containsKey(DecodeHintType.PURE_BARCODE))
        {
            com.google.zxing.common.BitMatrix bitmatrix = QRCodeReader.extractPureBits(binarybitmap.getBlackMatrix());
            DecoderResult decoderresult1 = decoder.decode(bitmatrix);
            ResultPoint aresultpoint1[] = NO_POINTS;
            decoderresult = decoderresult1;
            aresultpoint = aresultpoint1;
        } else
        {
            DetectorResult detectorresult = (new Detector(binarybitmap)).detect();
            decoderresult = decoder.decode(detectorresult.getBits());
            aresultpoint = detectorresult.getPoints();
        }
        return new Result(decoderresult.getText(), decoderresult.getRawBytes(), aresultpoint, BarcodeFormat.PDF417);
    }

    public void reset()
    {
    }

    private static final ResultPoint NO_POINTS[] = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

}
