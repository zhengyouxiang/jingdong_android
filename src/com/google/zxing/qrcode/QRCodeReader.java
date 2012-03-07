// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.zxing.qrcode;

import com.google.zxing.*;
import com.google.zxing.common.*;
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.detector.Detector;
import java.util.Hashtable;

public class QRCodeReader
    implements Reader
{

    public QRCodeReader()
    {
    }

    public static BitMatrix extractPureBits(BitMatrix bitmatrix)
        throws NotFoundException
    {
        int i = bitmatrix.getHeight();
        int j = bitmatrix.getWidth();
        int k = Math.min(i, j);
        int ai[] = bitmatrix.getTopLeftOnBit();
        if(ai == null)
            throw NotFoundException.getNotFoundInstance();
        int l = ai[0];
        int i1 = ai[1];
        int j1 = l;
        int k1;
        for(k1 = i1; j1 < k && k1 < k && bitmatrix.get(j1, k1); k1++)
            j1++;

        if(j1 == k || k1 == k)
            throw NotFoundException.getNotFoundInstance();
        int l1 = j1 - ai[0];
        if(l1 == 0)
            throw NotFoundException.getNotFoundInstance();
        int i2;
        for(i2 = j - 1; i2 > j1 && !bitmatrix.get(i2, k1); i2--);
        if(i2 <= j1)
            throw NotFoundException.getNotFoundInstance();
        int j2 = i2 + 1;
        if((j2 - j1) % l1 != 0)
            throw NotFoundException.getNotFoundInstance();
        int k2 = 1 + (j2 - j1) / l1;
        int l2;
        int i3;
        int j3;
        if(l1 == 1)
            l2 = 1;
        else
            l2 = l1 >> 1;
        i3 = j1 - l2;
        j3 = k1 - l2;
        if(i3 + l1 * (k2 - 1) >= j || j3 + l1 * (k2 - 1) >= i)
            throw NotFoundException.getNotFoundInstance();
        BitMatrix bitmatrix1 = new BitMatrix(k2);
        for(int k3 = 0; k3 < k2; k3++)
        {
            int l3 = j3 + k3 * l1;
            for(int i4 = 0; i4 < k2; i4++)
                if(bitmatrix.get(i3 + i4 * l1, l3))
                    bitmatrix1.set(i4, k3);

        }

        return bitmatrix1;
    }

    public Result decode(BinaryBitmap binarybitmap)
        throws NotFoundException, ChecksumException, FormatException
    {
        return decode(binarybitmap, null);
    }

    public Result decode(BinaryBitmap binarybitmap, Hashtable hashtable)
        throws NotFoundException, ChecksumException, FormatException
    {
        DecoderResult decoderresult;
        ResultPoint aresultpoint[];
        Result result;
        if(hashtable != null && hashtable.containsKey(DecodeHintType.PURE_BARCODE))
        {
            BitMatrix bitmatrix = extractPureBits(binarybitmap.getBlackMatrix());
            DecoderResult decoderresult1 = decoder.decode(bitmatrix, hashtable);
            ResultPoint aresultpoint1[] = NO_POINTS;
            decoderresult = decoderresult1;
            aresultpoint = aresultpoint1;
        } else
        {
            DetectorResult detectorresult = (new Detector(binarybitmap.getBlackMatrix())).detect(hashtable);
            decoderresult = decoder.decode(detectorresult.getBits(), hashtable);
            aresultpoint = detectorresult.getPoints();
        }
        result = new Result(decoderresult.getText(), decoderresult.getRawBytes(), aresultpoint, BarcodeFormat.QR_CODE);
        if(decoderresult.getByteSegments() != null)
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, decoderresult.getByteSegments());
        if(decoderresult.getECLevel() != null)
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, decoderresult.getECLevel().toString());
        return result;
    }

    protected Decoder getDecoder()
    {
        return decoder;
    }

    public void reset()
    {
    }

    private static final ResultPoint NO_POINTS[] = new ResultPoint[0];
    private final Decoder decoder = new Decoder();

}
